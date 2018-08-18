package com.adpanshi.cashloan.dispatch.controller;

import com.adpanshi.cashloan.dispatch.run.bo.DispatchRunResponseBo;
import com.adpanshi.cashloan.dispatch.run.domain.DispatchRunDomain;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Created by zsw on 2018/8/5 0005.
 */
@Api(value = "task-repair", description = "调度中心任务修复")
@RestController
@RequestMapping("/adpanshi/dispatch")
public class DispatchTaskRepairController {
    private static final Logger logger = LoggerFactory.getLogger(DispatchTaskRepairController.class);

    @Resource
    private DispatchRunDomain dispatchRunDomain;


    @ApiOperation("修复任务")
    @RequestMapping(value = "/repair_task", method = RequestMethod.POST)
    public DispatchRunResponseBo runNode(@ApiParam("任务实例ID 字段名:task_instance_id") Integer task_instance_id) {
        return dispatchRunDomain.runTask(task_instance_id);
    }
}
