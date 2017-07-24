package org.truenewx.example.service.manager;

import org.truenewx.core.exception.BusinessException;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.service.unity.ModelUnityService;

/**
 * 管理员服务
 *
 * @author jianglei
 * @since JDK 1.8
 */
public interface ManagerService extends ModelUnityService<Manager, Integer> {

    Manager validateLogin(String username, String md5Password) throws BusinessException;

}
