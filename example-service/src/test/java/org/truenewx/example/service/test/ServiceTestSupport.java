package org.truenewx.example.service.test;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.truenewx.test.spring.TransactionalJUnit4SpringContextTest;

/**
 * 带事务控制的基础测试支持
 *
 * @author jianglei
 * @since JDK 1.8
 */
@ContextConfiguration({ "/META-INF/spring/truenewx-service.xml", "/META-INF/spring/core.xml",
        "/META-INF/spring/placeholder.xml", "/META-INF/spring/data-orm.xml",
        "/META-INF/spring/data-tx.xml", "/META-INF/spring/datasource-embedded.xml",
        "/META-INF/spring/service-core.xml", "/META-INF/spring/service-tx.xml" })
@ActiveProfiles("junit")
public abstract class ServiceTestSupport extends TransactionalJUnit4SpringContextTest {
}
