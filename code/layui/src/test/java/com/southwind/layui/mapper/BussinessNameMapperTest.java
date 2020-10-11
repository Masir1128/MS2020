package com.southwind.layui.mapper;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.southwind.layui.entity.bussinessName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.management.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BussinessNameMapperTest {
    @Autowired
    private BussinessNameMapper mapper;

    @Test
    void test(){

        mapper.selectList(null).forEach(System.out::println);
    }

    // 数据库删除测试
    @Test
    void test1(){
        QueryWrapper<bussinessName> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ID",5);
        mapper.delete(queryWrapper);
        System.out.println("已经删除");


    }
    @Test
    void Delete(){
        int row = mapper.deleteById(4);
        System.out.println("删除条数" + row);
    }

    @Test
    void DeleteMap(){
        int row = mapper.deleteById(4);
        System.out.println("删除条数" + row);
    }


    @Test
    void add(){
        bussinessName newperson = new bussinessName();
        newperson.setGonghao("222");
        newperson.setBumen("技术部");
        newperson.setName("小爱");
        mapper.insert(newperson);
    }

    @Test
    void update(){
        bussinessName bussinessName = mapper.selectById(8);
        bussinessName.setBumen("技术销售");
        int rows = mapper.updateById(bussinessName);
        System.out.println("影响记录数:" +rows);
    }

    @Test
    void update1(){
        QueryWrapper<bussinessName> updateWrapper = new QueryWrapper<>();
        updateWrapper.eq("name","小东").eq("bumen","销售部");
        bussinessName newperson = new bussinessName();
        newperson.setGonghao("1232");
        int rows = mapper.update(newperson,updateWrapper);
        System.out.println("影响记录数:" +rows);
    }

    @Test
    void select(){
        List<bussinessName> list = mapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    void selectByWrapper(){
        QueryWrapper<bussinessName> queryWrapper = new QueryWrapper<bussinessName>();
        queryWrapper.like("name","花").lt("gonghao",50);
        List<bussinessName> list = mapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /*任务二：
    选择出包含“花”并且工号大于等于20小于27并且部门不能为空
    语法：
    name like '%花%' and gonghao between 20 and 27 bumen is not null*/
    @Test
    void selectByWrapper2(){
        QueryWrapper<bussinessName> queryWrapper = new QueryWrapper<bussinessName>();
        queryWrapper.like("name","花").between("gonghao",20,27).isNotNull("bumen");
        List<bussinessName> list = mapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }

    /*任务三：
    选择出包含“花”并且工号大于等于20小于27并且部门不能为空
    语法：
    name like '%花%' and gonghao between 20 and 27 bumen is not null*/
    @Test
    void selectByWrapper3(){
        QueryWrapper<bussinessName> queryWrapper = new QueryWrapper<bussinessName>();
        queryWrapper.select("ID","name").like("name","花").lt("gonghao",50);
        List<bussinessName> list = mapper.selectList(queryWrapper);
        list.forEach(System.out::println);

    }
}