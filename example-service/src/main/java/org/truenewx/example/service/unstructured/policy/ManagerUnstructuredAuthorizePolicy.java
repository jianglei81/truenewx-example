package org.truenewx.example.service.unstructured.policy;

import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.model.UnstructuredAuthorizeType;
import org.truenewx.support.unstructured.core.UnstructuredAuthorizePolicy;
import org.truenewx.support.unstructured.core.model.UnstructuredProvider;

/**
 * 管理员的非结构化授权方针
 *
 * @author jianglei
 * @since JDK 1.8
 */
public abstract class ManagerUnstructuredAuthorizePolicy
        implements UnstructuredAuthorizePolicy<UnstructuredAuthorizeType, Manager> {

    @Override
    public UnstructuredProvider getProvider() {
        return UnstructuredProvider.OWN;
    }

    @Override
    public boolean isPublicReadable() {
        return false;
    }

}
