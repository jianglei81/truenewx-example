package org.truenewx.example.service.manager;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.truenewx.core.annotation.Caption;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.test.ServiceTestSupport;
import org.truenewx.test.annotation.TestBusinessException;

/**
 * ManagerServiceTest
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class ManagerServiceTest extends ServiceTestSupport {
    @Autowired
    private ManagerService service;

    @Test
    @Caption("测试：校验管理员登录")
    @TestBusinessException(ManagerExceptionCodes.USERNAME_OR_PASSWORD_ERROR)
    public void testValidateLogin() throws BusinessException {
        final String username = "normal1";
        final Manager manager = this.service.validateLogin(username,
                "e10adc3949ba59abbe56e057f20f883e");
        assertEquals(username, manager.getUsername());

        this.service.validateLogin(username + "2", "e10adc3949ba59abbe56e057f20f883e");
        fail();
    }

}
