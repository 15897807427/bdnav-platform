package com.bdxh.common.zk.registry;

import com.bdxh.common.config.properties.AliyunProperties;
import com.bdxh.common.config.properties.GlobalProperties;
import com.bdxh.common.config.properties.ZookeeperProperties;
import com.bdxh.common.zk.generator.IncrementIdGenerator;
import com.bdxh.common.zk.registry.base.CoordinatorRegistryCenter;
import com.bdxh.common.zk.registry.base.RegisterDto;
import com.bdxh.common.zk.registry.zookeeper.ZookeeperRegistryCenter;
import com.google.common.base.Charsets;
import com.google.common.hash.HashCode;
import com.google.common.hash.Hasher;
import com.google.common.hash.Hashing;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 注册中心工厂.
 *
 * @author zhangliang
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RegistryCenterFactory {

	private static final ConcurrentHashMap<HashCode, CoordinatorRegistryCenter> REG_CENTER_REGISTRY = new ConcurrentHashMap<>();

	/**
	 * 创建注册中心.
	 *
	 * @param zookeeperProperties the zookeeper properties
	 *
	 * @return 注册中心对象 coordinator registry center
	 */
	public static CoordinatorRegistryCenter createCoordinatorRegistryCenter(ZookeeperProperties zookeeperProperties) {
		Hasher hasher = Hashing.md5().newHasher().putString(zookeeperProperties.getZkAddressList(), Charsets.UTF_8);
		HashCode hashCode = hasher.hash();
		CoordinatorRegistryCenter result = REG_CENTER_REGISTRY.get(hashCode);
		if (null != result) {
			return result;
		}
		result = new ZookeeperRegistryCenter(zookeeperProperties);
		result.init();
		REG_CENTER_REGISTRY.put(hashCode, result);
		return result;
	}

	public static void startup(GlobalProperties globalProperties, String host, String app) {
		CoordinatorRegistryCenter coordinatorRegistryCenter = createCoordinatorRegistryCenter(globalProperties.getZk());
		RegisterDto dto = new RegisterDto(app, host, coordinatorRegistryCenter);
		Long serviceId = new IncrementIdGenerator(dto).nextId();
		IncrementIdGenerator.setServiceId(serviceId);
		registerMq(globalProperties, host, app);
	}

	private static void registerMq(GlobalProperties globalProperties, String host, String app) {
		CoordinatorRegistryCenter coordinatorRegistryCenter = createCoordinatorRegistryCenter(globalProperties.getZk());
		AliyunProperties.RocketMqProperties rocketMq = globalProperties.getAliyun().getRocketMq();
		String consumerGroup = rocketMq.isReliableMessageConsumer() ? rocketMq.getConsumerGroup() : null;
		String namesrvAddr = rocketMq.getNamesrvAddr();
		String producerGroup = rocketMq.isReliableMessageProducer() ? rocketMq.getProducerGroup() : null;
		coordinatorRegistryCenter.registerMq(app, host, producerGroup, consumerGroup, namesrvAddr);
	}
}
