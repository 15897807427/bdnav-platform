package com.bdxh.wallet.configration.idgenerator;

import lombok.Data;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @description:
 * @author: xuyuan
 * @create: 2018-12-30 20:19
 **/
@Data
@ConfigurationProperties(prefix = "id-generator")
@ConditionalOnProperty(prefix = "id-generator",value = {"workerId","datacenterId"})
public class IdGeneratorProperties {

    private long workerId;

    private long datacenterId;
}
