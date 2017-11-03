package org.truenewx.example.service.unstructured;

import org.springframework.stereotype.Service;
import org.truenewx.example.data.model.manager.Manager;
import org.truenewx.example.service.model.UnstructuredAuthorizeType;
import org.truenewx.support.unstructured.core.UnstructuredServiceTemplateImpl;

/**
 * 非结构化存储服务实现
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Service
public class UnstructuredServiceImpl
        extends UnstructuredServiceTemplateImpl<UnstructuredAuthorizeType, Manager>
        implements UnstructuredService {

}
