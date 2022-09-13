/**
 * Copyright (c) 2018 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package bs.modules.sys.controller;

import bs.common.annotation.LogOperation;
import bs.common.utils.Result;
import bs.modules.job.utils.ValidatorUtils;
import bs.modules.job.utils.group.AddGroup;
import bs.modules.job.utils.group.DefaultGroup;
import bs.modules.job.utils.group.UpdateGroup;
import bs.modules.sys.dto.SysDeptDTO;
import bs.modules.sys.service.SysDeptService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

/**
 * 部门管理
 *
 */
@RestController
@RequestMapping("/sys/dept")
public class SysDeptController {

	@Autowired
	private SysDeptService sysDeptService;

	@GetMapping("list")
	@RequiresPermissions("sys:dept:list")
	public Result<List<SysDeptDTO>> list(){
		List<SysDeptDTO> list = sysDeptService.list(new HashMap<>(1));

		return new Result<List<SysDeptDTO>>().ok(list);
	}

	@GetMapping("{id}")
	@RequiresPermissions("sys:dept:info")
	public Result<SysDeptDTO> get(@PathVariable("id") Long id){
		SysDeptDTO data = sysDeptService.get(id);

		return new Result<SysDeptDTO>().ok(data);
	}

	@PostMapping
	@LogOperation("保存")
	@RequiresPermissions("sys:dept:save")
	public Result save(@RequestBody SysDeptDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, AddGroup.class, DefaultGroup.class);

		sysDeptService.save(dto);

		return new Result();
	}

	@PutMapping
	@LogOperation("修改")
	@RequiresPermissions("sys:dept:update")
	public Result update(@RequestBody SysDeptDTO dto){
		//效验数据
		ValidatorUtils.validateEntity(dto, UpdateGroup.class, DefaultGroup.class);

		sysDeptService.update(dto);

		return new Result();
	}

	@DeleteMapping("{id}")
	@LogOperation("删除")
	@RequiresPermissions("sys:dept:delete")
	public Result delete(@PathVariable("id") Long id){
		//效验数据
		//AssertUtils.isNull(id, "id");

		sysDeptService.delete(id);

		return new Result();
	}
	
}