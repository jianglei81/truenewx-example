package org.truenewx.example.service.manager;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.example.data.dao.manager.RoleDao;
import org.truenewx.example.data.model.manager.Role;
import org.truenewx.service.unity.AbstractUnityService;

/**
 * 角色服务
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Service
public class RoleServiceImpl extends AbstractUnityService<Role, Integer> implements RoleService {

    @Autowired
    private RoleDao dao;

    @Override
    protected UnityDao<Role, Integer> getDao() {
        return this.dao;
    }

    @Override
    public List<Role> findAll() {
        return this.dao.find((Map<String, ?>) null);
    }

}
