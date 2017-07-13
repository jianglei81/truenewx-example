package org.truenewx.example.data.dao.user;

import org.springframework.stereotype.Repository;
import org.truenewx.data.orm.dao.support.hibernate.HibernateUnityDaoSupport;
import org.truenewx.example.data.model.user.User;

/**
 * 用户DAO实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Repository
public class UserDaoImpl extends HibernateUnityDaoSupport<User, Long> implements UserDao {

}
