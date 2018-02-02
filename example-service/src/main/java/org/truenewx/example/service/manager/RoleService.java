package org.truenewx.example.service.manager;

import java.util.List;

import org.truenewx.example.data.model.manager.Role;
import org.truenewx.example.service.model.SubmitRole;
import org.truenewx.service.unity.ModelUnityService;
import org.truenewx.service.unity.SubmitModelBusinessValidator;

/**
 * 角色服务
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface RoleService extends ModelUnityService<Role, Integer>,
        SubmitModelBusinessValidator<SubmitRole, Role, Integer> {

    List<Role> findAll();

    List<Role> findByName(String name);

    Role move(int id, boolean down);

}
