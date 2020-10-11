package com.southwind.layui.service;

import com.southwind.layui.entity.Product;
import com.southwind.layui.vo.*;

public interface ProductService {
    public DataVO<ProductVO> findData(Integer page,Integer limit);
    public BarVO getBarVO();
    // 反馈总表
    public DataVO<bussinessVO> findData1();
    // 测试按照某个销售进行查询
    public DataVO<bussinessVO> findData3(String name);
    // 测试按照某个项目性质进行查询
    public DataVO<bussinessVO> SalesStatus(String status);
    //
    public DataVO<bussinessVO>  CombineFind(String comfind,String name);
    // 反馈销售人员列表
    public DataVO<bussinessNameVO> findData2();
    // 查询Echarts 的数据
    public BusTVO getBusVO();
    public BusTVO getBusVOByManager(String name);

    public BusTVO getPieVO1();
}
