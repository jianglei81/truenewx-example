package org.truenewx.example.service.unstructured.policy;

import org.springframework.stereotype.Service;
import org.truenewx.core.util.StringUtil;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.model.UnstructuredAuthorizeType;
import org.truenewx.support.unstructured.core.model.UnstructuredUploadLimit;

/**
 * 管理员头像的授权方针
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Service
public class ManagerHeadImageAuthorizePolicy extends ManagerUnstructuredAuthorizePolicy {

    @Override
    public UnstructuredAuthorizeType getType() {
        return UnstructuredAuthorizeType.MANAGER_HEAD_IMAGE;
    }

    @Override
    public UnstructuredUploadLimit getUploadLimit(final Manager user) {
        return new UnstructuredUploadLimit(1024 * 1024, "jpg", "png", "gif");
    }

    @Override
    public String getBucket() {
        return "headImage";
    }

    @Override
    public String getPath(final Manager manager, final String filename) {
        return "/manager/" + manager.getId() + "/" + StringUtil.uuid32() + "-" + filename;
    }

    @Override
    public boolean isReadable(final Manager manager, final String path) {
        return manager != null && path.startsWith("/manager/" + manager.getId() + "/");
    }

    @Override
    public boolean isWritable(final Manager manager, final String path) {
        return isReadable(manager, path); // 读写权限判断一致
    }

}
