package org.truenewx.example.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.example.data.dao.user.UserDao;
import org.truenewx.example.data.model.user.User;
import org.truenewx.service.unity.AbstractUnityService;

/**
 * 用户服务实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Service
public class UserServiceImpl extends AbstractUnityService<User, Long> implements UserService {

    @Autowired
    private UserDao dao;

    @Override
    protected UnityDao<User, Long> getDao() {
        return this.dao;
    }

}
