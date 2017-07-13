package org.truenewx.example.data.dao.manager;

import org.springframework.stereotype.Repository;
import org.truenewx.data.orm.dao.support.hibernate.HibernateUnityDaoSupport;
import org.truenewx.example.data.model.manager.Role;

/**
 * 角色DAO实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Repository
public class RoleDaoImpl extends HibernateUnityDaoSupport<Role, Integer> implements RoleDao {

}
