package org.truenewx.example.data.dao.manager;

import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.example.data.model.manager.Manager;

/**
 * 管理员DAO
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface ManagerDao extends UnityDao<Manager, Integer> {

    Manager findByUsername(String username);

}
