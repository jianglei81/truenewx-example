package org.truenewx.example.data.dao.manager;

import org.truenewx.data.orm.dao.UnityDao;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.manager.Manager;

/**
 * 管理员DAO
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface ManagerDao extends UnityDao<Manager, Integer> {

    int countByUsername(String username);

    Manager findByUsername(String username);

    QueryResult<Manager> findByKeywordAndTop(String keyword, Boolean top, int pageSize, int pageNo);

    int countByRoleId(int roleId);

}
