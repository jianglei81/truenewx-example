package org.truenewx.example.data.dao.manager;

import org.springframework.stereotype.Repository;
import org.truenewx.data.orm.dao.support.hibernate.HibernateUnityDaoSupport;
import org.truenewx.example.data.model.manager.Manager;

/**
 * 管理员DAO实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Repository
public class ManagerDaoImpl extends HibernateUnityDaoSupport<Manager, Integer>
        implements ManagerDao {

    @Override
    public Manager findByUsername(final String username) {
        final String hql = "from Manager where username=:username";
        return getHibernateTemplate().first(hql, "username", username);
    }

}
