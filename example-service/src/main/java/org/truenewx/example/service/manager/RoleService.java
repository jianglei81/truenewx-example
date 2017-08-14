package org.truenewx.example.service.manager;

import java.util.List;

import org.truenewx.example.data.model.manager.Role;
import org.truenewx.service.unity.SimpleUnityService;

/**
 * 角色服务
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface RoleService extends SimpleUnityService<Role, Integer> {

    List<Role> findAll();

}
