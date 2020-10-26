package com.atguigu.eduservice.controller;


import com.atguigu.commomutils.ResponseBean;
import com.atguigu.eduservice.entity.EduTeacher;
import com.atguigu.eduservice.entity.vo.TeacherQuery;
import com.atguigu.eduservice.service.EduTeacherService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author starshipz
 * @since 2020-10-24
 */
@Api(description = "讲师管理")
@RestController
@RequestMapping("/eduservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService teacherService;



    @ApiOperation(value = "查询所有讲师")
    @GetMapping("findAll")
    public ResponseBean findAllTeacher(){

        List<EduTeacher> list = teacherService.list(null);
        return ResponseBean.ok().data("items",list);

    }

    @ApiOperation(value = "逻辑删除讲师")
    @DeleteMapping("{id}")
    public ResponseBean removeTeacher(@ApiParam(name="id",value = "讲师ID",required = true) @PathVariable("id") String id){

        boolean result = teacherService.removeById(id);
        if (result){
            return ResponseBean.ok();
        }else{
            return ResponseBean.error();
        }

    }

    @ApiOperation("分页查询讲师")
    @GetMapping("pageTeacher/{current}/{limit}")
    public ResponseBean pageListTeacher(@PathVariable long current,@PathVariable long limit){

        Page<EduTeacher> eduTeacherPage = new Page<>(current,limit);
        teacherService.page(eduTeacherPage, null);
        long total =eduTeacherPage.getTotal();
        List<EduTeacher> records = eduTeacherPage.getRecords();
        Map map = new HashMap();
        map.put("total",total);
        map.put("rows",records);

        return ResponseBean.ok().data(map);
    }

    @ApiOperation("条件分页查询讲师")
    @PostMapping("pageTeacherCondition/{current}/{limit}")
    public ResponseBean pageTeacherCondition(@PathVariable long current,@PathVariable long limit,@RequestBody(required = false) TeacherQuery teacherQuery){

        Page<EduTeacher> page = new Page<>(current, limit);
        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        String name = teacherQuery.getName();
        Integer level = teacherQuery.getLevel();
        String begin = teacherQuery.getBegin();
        String end = teacherQuery.getEnd();
        int i =10/0;

        if(!StringUtils.isEmpty(name)){
            wrapper.like("name",name);
        }

        if(!StringUtils.isEmpty(level)){
            wrapper.eq("level",level);
        }

        if(!StringUtils.isEmpty(begin)){
            wrapper.ge("gmt_create",begin);
        }

        if(!StringUtils.isEmpty(end)){
            wrapper.le("gmt_create",end);
        }

        teacherService.page(page,wrapper);
        return ResponseBean.ok().data("rows",page.getRecords()).data("total",page.getTotal());

    }

    @ApiOperation("添加讲师")
    @PostMapping("addTeacher")
    public ResponseBean addTeacher(@RequestBody EduTeacher eduTeacher){

        boolean result = teacherService.save(eduTeacher);
        if(result){

            return ResponseBean.ok();

        }else{
            return ResponseBean.error();
        }


    }

    @ApiOperation("根据ID查询讲师")
    @GetMapping("getTeacher/{id}")
    public ResponseBean getTeacherById(@PathVariable String id){

        EduTeacher teacher = teacherService.getById(id);
        return ResponseBean.ok().data("teacher",teacher);


    }

    @ApiOperation("更新讲师")
    @PostMapping("updateTeacher")
    public ResponseBean updateTeacher(@RequestBody EduTeacher eduTeacher){

        boolean flag = teacherService.updateById(eduTeacher);

        if(flag){

            return ResponseBean.ok();

        }else{
            return ResponseBean.error();
        }
    }







}

