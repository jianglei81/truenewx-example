package org.truenewx.example.data.dao.manager;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.SQLQuery;
import org.springframework.stereotype.Repository;
import org.truenewx.core.Strings;
import org.truenewx.data.orm.dao.support.hibernate.HibernateUnityDaoSupport;
import org.truenewx.data.query.QueryParameterImpl;
import org.truenewx.data.query.QueryResult;
import org.truenewx.data.query.SingleQueryOrder;
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
    public int countByUsername(final String username) {
        final String hql = "from Manager where username=:username";
        return getHibernateTemplate().count(hql, "username", username);
    }

    @Override
    public Manager findByUsername(final String username) {
        final String hql = "from Manager where username=:username";
        return getHibernateTemplate().first(hql, "username", username);
    }

    @Override
    public QueryResult<Manager> queryByKeywordAndTop(final String keyword, final Boolean top,
            final int pageSize, final int pageNo) {
        final String entityName = getEntityName();
        final StringBuffer hql = new StringBuffer("from ").append(entityName).append(" where 1=1");
        final Map<String, Object> params = new HashMap<>();
        if (StringUtils.isNotBlank(keyword)) {
            hql.append(" and (username like :keyword or fullname like :keyword)");
            params.put("keyword", Strings.PERCENT + keyword + Strings.PERCENT);
        }
        if (top != null) {
            hql.append(" and top=:top");
            params.put("top", top);
        }
        final QueryParameterImpl parameter = new QueryParameterImpl(pageSize, pageNo);
        parameter.setOrder("username", Boolean.FALSE);
        return query(entityName, hql, params, parameter);
    }

    @Override
    public int countByRoleId(final int roleId) {
        final String sql = "select count(*) from T_MANAGER_R_ROLE where role_id = :roleId";
        final SQLQuery query = getHibernateTemplate().getSession().createSQLQuery(sql);
        query.setInteger("roleId", roleId);
        final Number count = (Number) query.uniqueResult();
        return count.intValue();
    }

    @Override
    public QueryResult<Manager> queryExceptRoleIdByTop(final int exceptedRoleId, final Boolean top,
            final int pageSize, final int pageNo) {
        final StringBuffer hql = new StringBuffer("from Manager m where m.id not in ").append(
                "(select r.manager.id from ManagerRoleRelation r where r.role.id=:exceptedRoleId)");
        final Map<String, Object> params = new HashMap<>();
        params.put("exceptedRoleId", exceptedRoleId);
        if (top != null) {
            hql.append(" and top=:top");
            params.put("top", top);
        }
        return query(getEntityName(), hql, params, pageSize, pageNo,
                new SingleQueryOrder("m.username", Boolean.FALSE));
    }

}
