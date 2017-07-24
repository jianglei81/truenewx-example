package org.truenewx.example.service.manager;

/**
 * 管理员模块业务异常错误码集
 *
 * @author jianglei
 * @since JDK 1.8
 */
public class ManagerExceptionCodes {

    private ManagerExceptionCodes() {
    }

    /**
     * 用户名或密码错误
     */
    public static final String USERNAME_OR_PASSWORD_ERROR = "error.manager.username_or_password_error";

    /**
     * 管理员被禁用
     */
    public static final String DISABLED_MANAGER = "error.manager.disabled_manager";

}
