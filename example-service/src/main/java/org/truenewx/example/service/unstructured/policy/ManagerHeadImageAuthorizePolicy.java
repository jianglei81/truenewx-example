package org.truenewx.example.service.unstructured.policy;

import org.springframework.stereotype.Service;
import org.truenewx.core.Strings;
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
    public UnstructuredUploadLimit getUploadLimit(Manager user) {
        return new UnstructuredUploadLimit(1, 150 * 1024, "jpg", "png");
    }

    @Override
    public String getBucket() {
        return "headImage";
    }

    @Override
    public boolean isMd5AsFilename() {
        return true;
    }

    @Override
    public String getPath(String token, Manager manager, String filename) {
        return "manager/" + manager.getId() + Strings.SLASH + filename;
    }

    @Override
    public boolean isReadable(Manager manager, String path) {
        return manager != null && path.startsWith("manager/");
    }

    @Override
    public boolean isWritable(Manager manager, String path) {
        return isReadable(manager, path); // 读写权限判断一致
    }

}
