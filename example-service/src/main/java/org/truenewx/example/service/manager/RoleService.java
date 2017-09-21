package org.truenewx.example.service.manager;

import java.util.List;

import org.truenewx.core.exception.BusinessException;
import org.truenewx.example.data.model.manager.Role;
import org.truenewx.service.unity.ModelUnityService;

/**
 * 角色服务
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface RoleService extends ModelUnityService<Role, Integer> {

    List<Role> findAll();

    List<Role> findByName(String name);

    void validateName(String name, Integer id) throws BusinessException;

    Role move(int id, boolean down);

}
