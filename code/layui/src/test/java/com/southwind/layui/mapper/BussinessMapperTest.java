package com.southwind.layui.mapper;

import com.southwind.layui.entity.bussinessMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BussinessMapperTest {
    @Autowired
    private BussinessMapper mapper;

    @Test
    void test(){
        mapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void add(){
        bussinessMessage mes = new bussinessMessage();
        //mes.setID(41);
        mes.setProjectName("安驰-赢合|隆合SCARA分拣项目");
        mes.setCustomName("未提供");
        mes.setCustomAddress("东莞市");
        mes.setBussiness("未提供");
        mes.setManager("陈爱");
        mes.setIndustry("3C");
        mes.setTime("2020-09-20");
        mes.setRem("#36276");
        mes.setAssessor("马扬");
        mes.setModel("AH6520");
        mes.setNature("PA");
        mes.setStatus("评估中");
        mapper.insert(mes);
    }


}