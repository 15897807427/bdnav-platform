package com.bdxh.message.api.autoconfigure;

import com.bdxh.message.api.aspect.MqConsumerStoreAspect;
import com.bdxh.message.api.aspect.MqProducerStoreAspect;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The class Elastic job auto configuration.
 *
 * @author paascloud.net @gmail.com
 */
@Configuration
public class MessageBeanConfiguration {
	@Bean
	@ConditionalOnExpression("${paascloud.aliyun.rocketMq.reliableMessageConsumer:true}")
	public MqConsumerStoreAspect mqConsumerStoreAspect() {
		return new MqConsumerStoreAspect();
	}

	@Bean
	@ConditionalOnExpression("${paascloud.aliyun.rocketMq.reliableMessageProducer:true}")
	public MqProducerStoreAspect mqProducerStoreAspect() {
		return new MqProducerStoreAspect();
	}
}