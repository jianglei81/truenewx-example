package org.truenewx.example.data.dao.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.truenewx.core.annotation.Caption;
import org.truenewx.data.model.unity.UnityUtil;
import org.truenewx.data.query.QueryResult;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.data.model.manager.Role;
import org.truenewx.example.data.test.DataTestSupport;

/**
 * ManagerDaoTest
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class ManagerDaoTest extends DataTestSupport {

    @Autowired
    private ManagerDao dao;

    @Test
    @Caption("测试：分页查询不属于指定角色的管理员清单")
    public void testQueryExceptRoleIdByTop() {
        final int roleId = getFirstData(Role.class).getId();
        final QueryResult<Manager> qr = this.dao.queryExceptRoleIdByTop(roleId, false, 20, 1);
        final List<Manager> records = qr.getRecords();
        assertEquals(2, records.size());
        assertFalse(UnityUtil.containsId(records.get(0).getRoles(), roleId));
        assertFalse(UnityUtil.containsId(records.get(1).getRoles(), roleId));
    }

}
