package org.truenewx.example.web.admin.controller.log;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.truenewx.core.util.DateUtil;
import org.truenewx.example.service.misc.ActionLogService;
import org.truenewx.support.log.web.annotation.LogExcluded;

/**
 * 操作日志控制器
 *
 * @author jianglei
 * @since JDK 1.8
 */
@Controller
@RequestMapping("/log/action")
public class ActionLogController {

    @Autowired
    private ActionLogService service;

    @RequestMapping
    @LogExcluded
    public ModelAndView execute(
            @RequestParam(value = "managerKeyword", required = false) final String managerKeyword,
            @RequestParam(value = "beginDate", required = false) final String beginDate,
            @RequestParam(value = "endDate", required = false) final String endDate,
            @RequestParam(value = "pageSize",
                    required = false,
                    defaultValue = "20") final int pageSize,
            @RequestParam(value = "pageNo",
                    required = false,
                    defaultValue = "1") final int pageNo) {
        final ModelAndView mav = new ModelAndView("/log/action");
        final Date beginTime = DateUtil.parseShort(beginDate);
        final Date endTime = DateUtil.parseShort(endDate);
        mav.addObject("result",
                this.service.query(managerKeyword, beginTime, endTime, pageSize, pageNo));
        return mav;
    }

}
