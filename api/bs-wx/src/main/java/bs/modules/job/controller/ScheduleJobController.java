/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package bs.modules.job.controller;

import bs.common.Global.R;
import bs.common.annotation.LogOperation;
import bs.common.utils.Constant;
import bs.common.utils.PageData;
import bs.modules.job.dto.ScheduleJobDTO;
import bs.modules.job.service.ScheduleJobService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Map;

/*
 * @Description: 定时任务
 * @Author: Dear lin
 * @Date: 2022/7/27 14:57
 * @Param:
 * @Return:
 */
@RestController
@RequestMapping("/sys/schedule")
@Api(tags="定时任务")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;

	@GetMapping("page")
	@ApiOperation("分页")
	@ApiImplicitParams({
		@ApiImplicitParam(name = Constant.PAGE, value = "当前页码，从1开始", paramType = "query", required = true, dataType="int") ,
		@ApiImplicitParam(name = Constant.LIMIT, value = "每页显示记录数", paramType = "query",required = true, dataType="int") ,
		@ApiImplicitParam(name = Constant.ORDER_FIELD, value = "排序字段", paramType = "query", dataType="String") ,
		@ApiImplicitParam(name = Constant.ORDER, value = "排序方式，可选值(asc、desc)", paramType = "query", dataType="String") ,
		@ApiImplicitParam(name = "beanName", value = "beanName", paramType = "query", dataType="String")
	})
	@RequiresPermissions("sys:schedule:page")
	public R page(@ApiIgnore @RequestParam Map<String, Object> params){
		PageData<ScheduleJobDTO> page = scheduleJobService.page(params);

		return R.ok().data("data",page);
	}

	@GetMapping("{id}")
	@ApiOperation("信息")
	@RequiresPermissions("sys:schedule:info")
	public R info(@PathVariable("id") Long id){
		ScheduleJobDTO schedule = scheduleJobService.get(id);
		return R.ok().data("info",schedule);
	}

	@PostMapping
	@ApiOperation("保存")
	//@LogOperation("保存")
	@RequiresPermissions("sys:schedule:save")
	public R save(@RequestBody ScheduleJobDTO dto){
		//ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);
		
		scheduleJobService.save(dto);
		
		return R.ok();
	}

	@PutMapping
	@ApiOperation("修改")
	//@LogOperation("修改")
	@RequiresPermissions("sys:schedule:update")
	public R update(@RequestBody ScheduleJobDTO dto){
		//ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);
				
		scheduleJobService.update(dto);
		
		return R.ok();
	}

	@DeleteMapping
	@ApiOperation("删除")
	@LogOperation("删除")
	@RequiresPermissions("sys:schedule:delete")
	public R delete(@RequestBody Long[] ids){
		scheduleJobService.deleteBatch(ids);
		
		return R.ok();
	}

	@PutMapping("/run")
	@ApiOperation("立即执行")
	//@LogOperation("立即执行")
	@RequiresPermissions("sys:schedule:run")
	public R run(@RequestBody Long[] ids){
		scheduleJobService.run(ids);
		
		return R.ok();
	}

	@PutMapping("/pause")
	@ApiOperation("暂停")
	//@LogOperation("暂停")
	@RequiresPermissions("sys:schedule:pause")
	public R pause(@RequestBody Long[] ids){
		scheduleJobService.pause(ids);
		
		return R.ok();
	}

	@PutMapping("/resume")
	@ApiOperation("恢复")
	//@LogOperation("恢复")
	@RequiresPermissions("sys:schedule:resume")
	public R resume(@RequestBody Long[] ids){
		scheduleJobService.resume(ids);
		
		return R.ok();
	}

}