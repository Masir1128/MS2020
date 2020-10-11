package com.southwind.layui.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.southwind.layui.vo.ProductBarVO;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
@SpringBootTest
class ProductMapperTest {

    @Autowired
    private ProductMapper mapper;

    @Test
    void test(){
        mapper.selectList(null).forEach(System.out::println);
    }

    @Test
    void test2(){
        List<ProductBarVO> list = mapper.findAllProductBarVO();
        int i = 0;
    }

}