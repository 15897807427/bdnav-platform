package com.bdxh.wallet.feign;

import com.bdxh.common.utils.wrapper.Wrapper;
import com.bdxh.demo.entity.SysUser;
import com.bdxh.wallet.fallback.SysUserControllerFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @description: feign客户端
 * @author: xuyuan
 * @create: 2018-12-27 17:18
 **/
@Service
@FeignClient(value = "demo",fallback= SysUserControllerFallback.class)
public interface SysUserControllerApi {

    @RequestMapping(value = "/sysUser/getByName")
    Wrapper<SysUser> getSysUser(String name);

}
