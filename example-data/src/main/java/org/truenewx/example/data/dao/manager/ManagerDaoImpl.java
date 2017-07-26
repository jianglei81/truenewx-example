package org.truenewx.example.data.dao.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;
import org.truenewx.data.orm.dao.support.hibernate.HibernateUnityDaoSupport;
import org.truenewx.data.query.QueryParameterImpl;
import org.truenewx.data.query.QueryResult;
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

    @Override
    public QueryResult<Manager> findByKeyword(final String keyword, final int pageSize,
            final int pageNo) {
        final String entityName = getEntityName();
        final StringBuffer hql = new StringBuffer("from ").append(entityName);
        final Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(keyword)) {
            hql.append(" where username like :keyword and fullname like :keyword");
            params.put("keyword", keyword);
        }
        final QueryParameterImpl parameter = new QueryParameterImpl(pageSize, pageNo);
        parameter.setOrder("username", Boolean.FALSE);
        return pagingQuery(entityName, hql, params, parameter);
    }

}
