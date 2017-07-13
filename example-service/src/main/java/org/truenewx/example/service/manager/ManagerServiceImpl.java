package org.truenewx.example.service.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.example.data.dao.manager.ManagerDao;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.service.unity.AbstractUnityService;

/**
 * 管理员服务实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Service
public class ManagerServiceImpl extends AbstractUnityService<Manager, Integer>
        implements ManagerService {

    @Autowired
    private ManagerDao dao;

    @Override
    protected UnityDao<Manager, Integer> getDao() {
        return this.dao;
    }

}
