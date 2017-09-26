package org.truenewx.example.data.sql;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.truenewx.core.Strings;
import org.truenewx.core.annotation.Caption;
import org.truenewx.core.io.FileContentConverter;
import org.truenewx.test.spring.JUnit4SpringContextTest;

/**
 * SqlFileContentConverterTest
 *
 * @author jianglei
 * @since JDK 1.8.0
 */
@ContextConfiguration({ "/META-INF/spring/data-test.xml" })
@ActiveProfiles("junit")
public class SqlFileContentConverterTest extends JUnit4SpringContextTest {
    @Autowired
    private FileContentConverter converter;

    @Test
    public void testEmpty() {
        // 空的测试方法，用于占位，以免本类在单元测试时报错，下面的测试方法只是利用单元测试环境的工具方法
    }

    @Caption("工具：转换自动生成的sql文件中不合适的部分语句为合适的语句")
    public void testConvert() {
        final String dir = "file://E:/workspace/example/example-data";
        this.converter.convert(dir + "/src/main/resources/sql/junit/create/*.sql",
                Strings.DEFAULT_ENCODING);
    }

}
