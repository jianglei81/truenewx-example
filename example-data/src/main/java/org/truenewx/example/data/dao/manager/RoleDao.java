package org.truenewx.example.data.dao.manager;

import java.util.List;

import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.example.data.model.manager.Role;

/**
 * 角色DAO
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface RoleDao extends UnityDao<Role, Integer> {

    List<Role> findByName(String name);

    int countByNameExceptId(String name, Integer exceptedId);

    Role findClosestByMaxOrdinal(long maxOrdinal);

    Role findClosestByMinOrdinal(long minOrdinal);

}
