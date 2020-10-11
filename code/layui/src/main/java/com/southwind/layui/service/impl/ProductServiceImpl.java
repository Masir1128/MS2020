package com.southwind.layui.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.southwind.layui.entity.Product;
import com.southwind.layui.entity.ProductCategory;
import com.southwind.layui.entity.bussinessMessage;
import com.southwind.layui.entity.bussinessName;
import com.southwind.layui.mapper.BussinessMapper;
import com.southwind.layui.mapper.BussinessNameMapper;
import com.southwind.layui.mapper.ProductCategoryMapper;
import com.southwind.layui.mapper.ProductMapper;
import com.southwind.layui.service.ProductService;
import com.southwind.layui.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.SplittableRandom;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private ProductCategoryMapper ProductCategoryMapper;
    @Autowired
    private BussinessMapper bussinessMapper;
    @Autowired
    private BussinessNameMapper bussinessNameMapper;


    @Override
    public DataVO<ProductVO> findData(Integer page,Integer limit) {
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");

        IPage<Product> productIPage = new Page<>(page,limit);
        IPage<Product> result = productMapper.selectPage(productIPage,null);
        dataVO.setCount(result.getTotal());

        List<Product> productList = result.getRecords();
        List<ProductVO> productVOList = new ArrayList<>();
        for (Product product : productList) {
            ProductVO productVO = new ProductVO();
            BeanUtils.copyProperties(product,productVO);
            QueryWrapper wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategoryleveloneId());
            ProductCategory productCategory = ProductCategoryMapper.selectOne(wrapper);

            if(productCategory!=null){
                productVO.setCategorylevelone(productCategory.getName());
            }

            wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategoryleveltwoId());

            productCategory = ProductCategoryMapper.selectOne(wrapper);
            if(productCategory!=null) {
                productVO.setCategoryleveltwo(productCategory.getName());
            }

            wrapper = new QueryWrapper();
            wrapper.eq("id",product.getCategorylevelthreeId());
            productCategory = ProductCategoryMapper.selectOne(wrapper);
            if(productCategory!=null) {
                productVO.setCategorylevelthree(productCategory.getName());
            }
            productVOList.add(productVO);
        }

        dataVO.setData(productVOList);

        return dataVO;
    }

    @Override
    public BarVO getBarVO() {
        List<ProductBarVO> list = productMapper.findAllProductBarVO();
        List<String> names = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        for (ProductBarVO productBarVO : list) {
            names.add(productBarVO.getName());
            values.add(productBarVO.getCount());
        }
        BarVO barVO = new BarVO();
        barVO.setNames(names);
        barVO.setValues(values);
        return barVO;
    }

    /**
     * 获取所有评估项目列表
     * @Masir
     */
    @Override
    public DataVO<bussinessVO> findData1(){
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        //获取数据库字段的长度
        bussinessMapper.selectCount(null);
        //将长度设置到DataVO中
        dataVO.setCount(Long.valueOf(bussinessMapper.selectCount(null)));
        //查询数据库，将所有字段进行返回
        List<bussinessMessage> bussinesslist = bussinessMapper.selectList(null);
        dataVO.setData(bussinesslist);
        return dataVO;

    }
    /**
     * @Masir
     * 根据销售人员进行数据查询（人员）
     */
    @Override
    public DataVO<bussinessVO> findData3(String name){
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper<bussinessMessage> queryWrapper = new QueryWrapper<bussinessMessage>();
        queryWrapper.like("manager",name);
        List<bussinessMessage> bussinesslist = bussinessMapper.selectList(queryWrapper);
        bussinesslist.forEach(System.out::println);
        dataVO.setData(bussinesslist);
        return dataVO;
    }

    /**
     * @Masir
     * 查询项目性质进行数据查询（项目状态）
     */
    @Override
    public DataVO<bussinessVO> SalesStatus(String status){
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper<bussinessMessage> queryWrapper = new QueryWrapper<bussinessMessage>();
        queryWrapper.eq("status",status);
        List<bussinessMessage> bussinesslist = bussinessMapper.selectList(queryWrapper);
        bussinesslist.forEach(System.out::println);
        dataVO.setData(bussinesslist);
        return dataVO;
    }

    /**
     * @Masir
     * 联合查询
     */
    @Override
    public DataVO<bussinessVO> CombineFind(String comfind,String name){
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        QueryWrapper<bussinessMessage> queryWrapper = new QueryWrapper<bussinessMessage>();
        queryWrapper.eq("status",comfind).eq("manager",name);
        List<bussinessMessage> bussinesslist = bussinessMapper.selectList(queryWrapper);
        bussinesslist.forEach(System.out::println);
        dataVO.setData(bussinesslist);
        return dataVO;
    }


    /**
     * 获取销售人员列表
     * @Masir
     */
    @Override
    public DataVO<bussinessNameVO> findData2(){
        DataVO dataVO = new DataVO();
        dataVO.setCode(0);
        dataVO.setMsg("");
        bussinessNameMapper.selectCount(null);
        dataVO.setCount(Long.valueOf(bussinessNameMapper.selectCount(null)));
        List<bussinessName> bussinesslist = bussinessNameMapper.selectList(null);
        dataVO.setData(bussinesslist);
        return dataVO;

    }

    /**
     * 描述：获取销售信息总工时记录
     * @Masir
     */
    @Override
    public BusTVO getBusVO(){
        List<BusVO> list = bussinessMapper.findAllBusVO();
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        List<String> rem = new ArrayList<>();
        for(BusVO busVO :list){
            names.add(busVO.getProjectName());
            values.add(busVO.getPingtime());
            rem.add(busVO.getRem());
        }
        BusTVO busTVO = new BusTVO();
        busTVO.setNames(names);
        busTVO.setValues(values);
        busTVO.setRem(rem);
        return busTVO;
    }
    /**
     * 描述：获取销售信息总工时记录,本函数是通过人员获取
     * @Masir
     */
    @Override
    public BusTVO getBusVOByManager(String name){
        List<BusVO> list = bussinessMapper.findAllBusVOByManager(name);
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for(BusVO busVO :list){
            names.add(busVO.getProjectName());
            values.add(busVO.getPingtime());
        }
        BusTVO busTVO = new BusTVO();
        busTVO.setNames(names);
        busTVO.setValues(values);
        return busTVO;
    }

    /**
     * 描述：获取销售信息总工时记录,本函数是通过人员获取成单率
     * @Masir
     */

    @Override
    public BusTVO getPieVO1(){
        List<PieVO> list = bussinessMapper.findAllStatusVO();
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for(PieVO pieVO :list){
            names.add(pieVO.getSTATUS());
            values.add(pieVO.getCON());
        }
        BusTVO busTVO = new BusTVO();
        busTVO.setNames(names);
        busTVO.setValues(values);
        return busTVO;
    }

    /**
     * 描述：获取销售成单率,本函数是通过人员获取成单率
     * @Masir
     */
    public BusTVO getPieVO2(String name){
        List<PieVO> list = bussinessMapper.findAllStatusVOByManager(name);
        List<String> names = new ArrayList<>();
        List<Double> values = new ArrayList<>();
        for(PieVO pieVO :list){
            names.add(pieVO.getSTATUS());
            values.add(pieVO.getCON());
        }
        BusTVO busTVO = new BusTVO();
        busTVO.setNames(names);
        busTVO.setValues(values);
        return busTVO;
    }


}
