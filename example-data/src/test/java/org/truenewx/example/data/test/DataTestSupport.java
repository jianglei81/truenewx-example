package org.truenewx.example.data.test;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.truenewx.test.spring.TransactionalJUnit4SpringContextTest;

/**
 * 数据访问层带事务控制的基础测试支持
 *
 * @author jianglei
 * @version 1.0.0 2014-1-12
 * @since JDK 1.8
 */
@ContextConfiguration({ "/META-INF/spring/core.xml", "/META-INF/spring/placeholder.xml",
        "/META-INF/spring/data-orm.xml", "/META-INF/spring/data-tx.xml",
        "/META-INF/spring/datasource-embedded.xml" })
@ActiveProfiles("junit")
public abstract class DataTestSupport extends TransactionalJUnit4SpringContextTest {
}
