/*
 * Copyright (c) 2018. paascloud.net All Rights Reserved.
 * 项目名称：paascloud快速搭建企业级分布式微服务平台
 * 类名称：TpcMqMessageServiceImpl.java
 * 创建人：刘兆明
 * 联系方式：paascloud.net@gmail.com
 * 开源地址: https://github.com/paascloud
 * 博客地址: http://blog.paascloud.net
 * 项目官网: http://paascloud.net
 */

package com.bdxh.task.service.impl;

import com.bdxh.common.base.dto.MessageQueryDto;
import com.bdxh.common.base.dto.MqMessageVo;
import com.bdxh.common.base.enums.ErrorCodeEnum;
import com.bdxh.common.core.support.BaseService;
import com.bdxh.common.utils.PublicUtil;
import com.bdxh.common.utils.wrapper.WrapMapper;
import com.bdxh.common.utils.wrapper.Wrapper;
import com.bdxh.common.zk.generator.UniqueIdGenerator;
import com.bdxh.task.api.exceptions.TaskBizException;
import com.bdxh.task.api.model.dto.TpcMqMessageDto;
import com.bdxh.task.mapper.TpcMqConfirmMapper;
import com.bdxh.task.mapper.TpcMqMessageMapper;
import com.bdxh.task.model.domain.TpcMqConfirm;
import com.bdxh.task.model.domain.TpcMqMessage;
import com.bdxh.task.model.dto.MessageTaskQueryDto;
import com.bdxh.task.model.enums.MqSendStatusEnum;
import com.bdxh.task.model.vo.TpcMessageVo;
import com.bdxh.task.mq.RocketMqProducer;
import com.bdxh.task.service.TpcMqConsumerService;
import com.bdxh.task.service.TpcMqMessageService;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

import static com.bdxh.common.utils.SnowflakeIdWorker.generateId;

/**
 * The class Tpc mq message service.
 *
 * @author paascloud.net @gmail.com
 */
@Service
@Slf4j
@Transactional(rollbackFor = Throwable.class)
public class TpcMqMessageServiceImpl extends BaseService<TpcMqMessage> implements TpcMqMessageService {
	@Resource
	private TpcMqMessageMapper tpcMqMessageMapper;
	@Resource
	private TpcMqConfirmMapper tpcMqConfirmMapper;
	@Resource
	private TpcMqConsumerService tpcMqConsumerService;

	@Override
	public void saveMessageWaitingConfirm(TpcMqMessageDto messageDto) {

		if (StringUtils.isEmpty(messageDto.getMessageTopic())) {
			throw new TaskBizException(ErrorCodeEnum.TPC10050001);
		}

		Date now = new Date();
		TpcMqMessage message = new ModelMapper().map(messageDto, TpcMqMessage.class);
		message.setMessageStatus(MqSendStatusEnum.WAIT_SEND.sendStatus());
		message.setUpdateTime(now);
		message.setCreatedTime(now);
		tpcMqMessageMapper.insertSelective(message);
	}

	@Override
	public void confirmAndSendMessage(String messageKey) {
		final TpcMqMessage message = tpcMqMessageMapper.getByMessageKey(messageKey);
		if (message == null) {
			throw new TaskBizException(ErrorCodeEnum.TPC10050002);
		}

		TpcMqMessage update = new TpcMqMessage();
		update.setMessageStatus(MqSendStatusEnum.SENDING.sendStatus());
		update.setId(message.getId());
		update.setUpdateTime(new Date());
		tpcMqMessageMapper.updateByPrimaryKeySelective(update);
		// 创建消费待确认列表
		this.createMqConfirmListByTopic(message.getMessageTopic(), message.getId(), message.getMessageKey());
		// 直接发送消息
		this.directSendMessage(message.getMessageBody(), message.getMessageTopic(), message.getMessageTag(), message.getMessageKey(), message.getProducerGroup(), message.getDelayLevel());
	}

	@Override
	public void saveAndSendMessage(TpcMqMessageDto tpcMqMessageDto) {
		if (StringUtils.isEmpty(tpcMqMessageDto.getMessageTopic())) {
			throw new TaskBizException(ErrorCodeEnum.TPC10050001);
		}

		Date now = new Date();
		TpcMqMessage message = new ModelMapper().map(tpcMqMessageDto, TpcMqMessage.class);
		message.setMessageStatus(MqSendStatusEnum.SENDING.sendStatus());
		message.setId(generateId());
		message.setUpdateTime(now);
		message.setCreatedTime(now);

		tpcMqMessageMapper.insertSelective(message);
		// 创建消费待确认列表
		this.createMqConfirmListByTopic(message.getMessageTopic(), message.getId(), message.getMessageKey());
		this.directSendMessage(tpcMqMessageDto.getMessageBody(), tpcMqMessageDto.getMessageTopic(), tpcMqMessageDto.getMessageTag(), tpcMqMessageDto.getMessageKey(), tpcMqMessageDto.getProducerGroup(), tpcMqMessageDto.getDelayLevel());
	}

	@Override
	public void directSendMessage(String body, String topic, String tag, String key, String pid, Integer delayLevel) {
		RocketMqProducer.sendSimpleMessage(body, topic, tag, key, pid, delayLevel);
	}

	@Override
	public void resendMessageByMessageId(Long messageId) {
		final TpcMqMessage message = tpcMqMessageMapper.selectByPrimaryKey(messageId);
		if (message == null) {
			throw new TaskBizException(ErrorCodeEnum.TPC10050006);
		}
		this.resendMessage(message);
	}

	@Override
	public void resendMessageByMessageKey(String messageKey) {
		final TpcMqMessage task = tpcMqMessageMapper.getByMessageKey(messageKey);
		this.resendMessage(task);
	}

	@Override
	public void setMessageToAlreadyDead(Long messageId) {
		final TpcMqMessage task = tpcMqMessageMapper.selectByPrimaryKey(messageId);
		if (task == null) {
			throw new TaskBizException(ErrorCodeEnum.TPC10050006);
		}

		tpcMqMessageMapper.updateAlreadyDeadByMessageId(messageId);
	}

	@Override
	public void deleteMessageByMessageKey(String messageKey) {

		int result = tpcMqMessageMapper.deleteMessageByMessageKey(messageKey);
		if (result < 1) {
			throw new TaskBizException(ErrorCodeEnum.TPC10050003, messageKey);
		}
	}

	@Override
	public void resendAllDeadMessageByTopicName(String topicName, int batchSize) {
		// 1.查询该topic下所有死亡的消息
		// 2.分页
	}

	@Override
	public List<TpcMqMessage> listMessageForWaitingProcess(MessageTaskQueryDto query) {
		return tpcMqMessageMapper.listMessageForWaitingProcess(query);
	}

	@Override
	public void confirmReceiveMessage(final String cid, final String messageKey) {
		// 1. 校验cid
		// 2. 校验messageKey
		// 3. 校验cid 和 messageKey
		Long confirmId = tpcMqConfirmMapper.getIdMqConfirm(cid, messageKey);
		// 3. 更新消费信息的状态
		tpcMqConfirmMapper.confirmReceiveMessage(confirmId);
	}

	@Override
	public void confirmConsumedMessage(final String cid, final String messageKey) {
		Long confirmId = tpcMqConfirmMapper.getIdMqConfirm(cid, messageKey);
		tpcMqConfirmMapper.confirmConsumedMessage(confirmId);
	}

	@Override
	public int updateMqConfirmStatus(final String cid, final String messageKey) {
		return 0;
	}

	@Override
	public void createMqConfirmListByTopic(final String topic, final Long messageId, final String messageKey) {
		List<TpcMqConfirm> list = Lists.newArrayList();
		TpcMqConfirm tpcMqConfirm;
		List<String> consumerGroupList = tpcMqConsumerService.listConsumerGroupByTopic(topic);
		if (PublicUtil.isEmpty(consumerGroupList)) {
			throw new TaskBizException(ErrorCodeEnum.TPC100500010, topic);
		}
		for (final String cid : consumerGroupList) {
			tpcMqConfirm = new TpcMqConfirm(UniqueIdGenerator.generateId(), messageId, messageKey, cid);
			list.add(tpcMqConfirm);
		}

		tpcMqConfirmMapper.batchCreateMqConfirm(list);
	}

	@Override
	public List<String> queryWaitingConfirmMessageKeyList(final MessageTaskQueryDto query) {
		return tpcMqMessageMapper.queryWaitingConfirmMessageKeyList(query);
	}

	@Override
	public void handleWaitingConfirmMessage(final List<String> deleteKeyList, final List<String> resendKeyList) {
		tpcMqMessageMapper.batchDeleteMessage(deleteKeyList);
		for (final String messageKey : resendKeyList) {
			this.confirmAndSendMessage(messageKey);
		}
	}

	@Override
	public int updateMqMessageTaskStatus(final TpcMqMessage message) {
		return tpcMqMessageMapper.updateMqMessageTaskStatus(message);
	}

	@Override
	public int updateMqMessageStatus(final TpcMqMessage update) {
		return tpcMqMessageMapper.updateByPrimaryKeySelective(update);
	}

	@Override
	public Wrapper queryRecordListWithPage(final MessageQueryDto messageQueryDto) {
		return null;
	}

	@Override
	public List<TpcMessageVo> listReliableMessageVo(final MessageQueryDto messageQueryDto) {
		return tpcMqMessageMapper.listReliableMessageVoWithPage(messageQueryDto);
	}

	@Override
	public List<TpcMessageVo> listReliableMessageVo(final List<Long> messageIdList) {
		return tpcMqMessageMapper.listReliableMessageVo(messageIdList);
	}

	private void resendMessage(TpcMqMessage message) {
		if (message == null) {
			throw new TaskBizException(ErrorCodeEnum.TPC10050002);
		}
		tpcMqMessageMapper.addTaskExeCountById(message.getId());
		//TODO 记录重发日志 1.系统自动重发 2.人工重发
		this.directSendMessage(message.getMessageBody(), message.getMessageTopic(), message.getMessageTag(), message.getMessageKey(), message.getProducerGroup(), message.getDelayLevel());
	}
}
