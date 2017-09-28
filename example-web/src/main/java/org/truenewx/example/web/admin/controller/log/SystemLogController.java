package org.truenewx.example.web.admin.controller.log;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.core.spring.core.io.WebContextResource;
import org.truenewx.core.util.DateUtil;
import org.truenewx.support.log.data.model.SystemLogLine;
import org.truenewx.support.log.service.SystemLogReader;
import org.truenewx.support.log.web.annotation.LogExcluded;
import org.truenewx.web.rpc.server.annotation.RpcController;
import org.truenewx.web.rpc.server.annotation.RpcMethod;

/**
 * 系统日志控制器
 *
 * @author jianglei
 * @since JDK 1.8
 */
@RpcController
@RequestMapping("/log/system")
public class SystemLogController {

    private Logger logger = Logger.getRootLogger();
    @Autowired
    private SystemLogReader reader;

    @RequestMapping
    public ModelAndView execute() {
        return execute(null);
    }

    @RequestMapping("/{appender}")
    public ModelAndView execute(@PathVariable("appender")
    String appender) {
        final ModelAndView mav = new ModelAndView("/log/system");
        final List<String> appenders = getValidAppenderNames();
        mav.addObject("appenders", appenders);
        if (appender == null) {
            appender = appenders.get(0); // 已知系统至少有一个有效Appender
        }
        mav.addObject("appender", appender);
        return mav;
    }

    private List<String> getValidAppenderNames() {
        final List<String> names = new ArrayList<>();
        @SuppressWarnings("unchecked")
        final Enumeration<Appender> appenders = this.logger.getAllAppenders();
        while (appenders.hasMoreElements()) {
            final Appender appender = appenders.nextElement();
            if (appender instanceof ConsoleAppender) {
                final File file = getConsoleFile();
                if (file != null) {
                    names.add(appender.getName());
                }
            } else if (appender instanceof FileAppender) {
                names.add(appender.getName());
            }
        }
        return names;
    }

    private File getConsoleFile() {
        try {
            final Resource resource = new WebContextResource(".");
            final File root = resource.getFile().getParentFile().getParentFile();
            final File dir = new File(root, "/logs");

            // 先检查Linux版Tomcat控制台文件
            File file = new File(dir, "/catalina.out");
            if (file.exists()) {
                return file;
            }
            // 再检查Windows版Tomcat控制台文件
            file = new File(dir, "/catalina." + DateUtil.formatShort(new Date()) + ".log");
            if (file.exists()) {
                return file;
            }
        } catch (final IOException e) {
            // 忽略IO异常
        }
        return null;
    }

    @RpcMethod
    @LogExcluded
    public List<SystemLogLine> getLast(final String appenderName, final int size) {
        final Appender appender = this.logger.getAppender(appenderName);
        if (appender instanceof ConsoleAppender) {
            final File file = getConsoleFile();
            if (file != null) {
                return this.reader.readLast(file, size);
            }
        } else if (appender instanceof FileAppender) {
            final FileAppender fa = (FileAppender) appender;
            return this.reader.readLast(new File(fa.getFile()), size);
        }
        return Collections.emptyList();
    }

    @RpcMethod
    @LogExcluded
    public List<SystemLogLine> getAfter(final String appenderName, final long minPostion) {
        final Appender appender = this.logger.getAppender(appenderName);
        if (appender instanceof ConsoleAppender) {
            final File file = getConsoleFile();
            if (file != null) {
                return this.reader.readAfter(file, minPostion);
            }
        } else if (appender instanceof FileAppender) {
            final FileAppender fa = (FileAppender) appender;
            return this.reader.readAfter(new File(fa.getFile()), minPostion);
        }
        return Collections.emptyList();
    }

    @RpcMethod
    @LogExcluded
    public List<SystemLogLine> getBefore(final String appenderName, final long maxPosition,
            final int size) {
        final Appender appender = this.logger.getAppender(appenderName);
        if (appender instanceof ConsoleAppender) {
            final File file = getConsoleFile();
            if (file != null) {
                return this.reader.readBefore(file, maxPosition, size);
            }
        } else if (appender instanceof FileAppender) {
            final FileAppender fa = (FileAppender) appender;
            return this.reader.readBefore(new File(fa.getFile()), maxPosition, size);
        }
        return Collections.emptyList();
    }

}
