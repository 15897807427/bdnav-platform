package com.bdxh.common.base.constant;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @description: topic常量
 * @author: xuyuan
 * @create: 2018-12-17 14:25
 **/
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RocketmqConstants {

	public enum RocketmqTopicEnum {
		/**
		 * 发送短信
		 */
		SEND_SMS_TOPIC("SEND_SMS_TOPIC", "发送短信"),
		/**
		 * 消息推送
		 */
		CONTROL_PUSH_TOPIC("CONTROL_PUSH_TOPIC", "管控推送");

		RocketmqTopicEnum(String topic, String topicName) {
			this.topic = topic;
			this.topicName = topicName;
		}

		String topic;

		String topicName;

		public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public String getTopicName() {
			return topicName;
		}

		public void setTopicName(String topicName) {
			this.topicName = topicName;
		}
	}

	public enum RocketmqTagEnum {

		/**
		 * 获取身份认证验证码
		 */
		SELF_AUTH_CODE_TAG("SELF_AUTH_CODE_TAG", RocketmqTopicEnum.SEND_SMS_TOPIC.getTopic(), "身份认证验证码短信发送"),
		/**
		 * 获取博学派验证码
		 */
		LIBERAL_SCHOOL_CODE_TAG("LIBERAL_SCHOOL_CODE_TAG", RocketmqTopicEnum.SEND_SMS_TOPIC.getTopic(), "博学派验证码短信发送");

		String tag;

		String topic;

		String tagName;

		RocketmqTagEnum(String tag, String topic, String tagName) {
			this.tag = tag;
			this.topic = topic;
			this.tagName = tagName;
		}

		public String getTag() {
			return tag;
		}

		public void setTag(String tag) {
			this.tag = tag;
		}

		public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public String getTagName() {
			return tagName;
		}

		public void setTagName(String tagName) {
			this.tagName = tagName;
		}
	}

	@NoArgsConstructor(access = AccessLevel.PRIVATE)
	public static final class ConsumerTopics {

		public static final String OPT = buildOpcConsumerTopics();

	}

	private static String buildOpcConsumerTopics() {
		List<TopicObj> topicObjList = new ArrayList<>();
		Set<String> sendSmsTagList = new HashSet<>();
		sendSmsTagList.add(RocketmqTagEnum.SELF_AUTH_CODE_TAG.getTag());
		sendSmsTagList.add(RocketmqTagEnum.LIBERAL_SCHOOL_CODE_TAG.getTag());
		topicObjList.add(new TopicObj(RocketmqTopicEnum.SEND_SMS_TOPIC.getTopic(), sendSmsTagList));
		return buildOpcConsumerTopics(topicObjList);
	}

	private static String buildOpcConsumerTopics(List<TopicObj> topicList) {
		StringBuilder result = new StringBuilder();
		if (!CollectionUtils.isEmpty(topicList)) {
			for (TopicObj topicObj : topicList) {
				String topic = topicObj.getTopic();
				Set<String> tagList = topicObj.getTagList();
				if (StringUtils.isEmpty(topic)) {
					continue;
				}
				StringBuilder tagInfo = new StringBuilder();
				for (String tag : tagList) {
					tagInfo.append(tag).append(GlobalConstant.Symbol.PIPE);
				}
				trimEnd(tagInfo, GlobalConstant.Symbol.PIPE);
				result.append(topic).append(GlobalConstant.Symbol.AT).append(tagInfo).append(GlobalConstant.Symbol.COMMA);
			}
		}
		trimEnd(result, GlobalConstant.Symbol.COMMA);
		return result.toString();
	}

	static class TopicObj {

		private String topic;

		private Set<String> tagList;

		TopicObj(String topic, Set<String> tagList) {
			this.topic = topic;
			this.tagList = tagList;
		}

		public String getTopic() {
			return topic;
		}

		public void setTopic(String topic) {
			this.topic = topic;
		}

		public Set<String> getTagList() {
			return tagList;
		}

		public void setTagList(Set<String> tagList) {
			this.tagList = tagList;
		}
	}

	private static void trimEnd(StringBuilder stringBuilder, String suffix) {
		if (null == stringBuilder||stringBuilder.length()==0) {
			return;
		}
		if (StringUtils.isEmpty(suffix)){
			return;
		}
		String str = stringBuilder.toString();
		if (!str.endsWith(suffix)) {
			return;
		}
		stringBuilder.delete(str.length() - suffix.length(), str.length());
	}

	public static void main(String[] args) {
		System.out.println(ConsumerTopics.OPT);
	}
}
