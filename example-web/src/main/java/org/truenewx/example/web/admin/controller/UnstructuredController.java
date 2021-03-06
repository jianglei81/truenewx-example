package org.truenewx.example.web.admin.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.truenewx.core.exception.BusinessException;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.model.UnstructuredAuthorizeType;
import org.truenewx.example.web.admin.util.ProjectWebUtil;
import org.truenewx.support.unstructured.core.model.UnstructuredUploadLimit;
import org.truenewx.support.unstructured.web.controller.UnstructuredControllerTemplate;
import org.truenewx.web.rpc.server.annotation.RpcController;
import org.truenewx.web.rpc.server.annotation.RpcMethod;
import org.truenewx.web.security.annotation.Accessibility;

/**
 * 非结构化存储控制器
 *
 * @author jianglei
 * @version 1.0.0 2016年2月1日
 * @since JDK 1.8
 */
@RpcController
@RequestMapping("/unstructured")
public class UnstructuredController
        extends UnstructuredControllerTemplate<UnstructuredAuthorizeType, Manager> {

    @Override
    protected Manager getUser() {
        return ProjectWebUtil.getManager();
    }

    @Override
    @RpcMethod
    @Accessibility(anonymous = true)
    public UnstructuredUploadLimit getUploadLimit(final UnstructuredAuthorizeType authorizeType)
            throws BusinessException {
        return super.getUploadLimit(authorizeType);
    }

}
