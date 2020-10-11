package com.southwind.layui.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.southwind.layui.entity.bussinessMessage;
import com.southwind.layui.vo.BusVO;
import com.southwind.layui.vo.PieVO;
import com.southwind.layui.vo.ProductBarVO;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface BussinessMapper extends BaseMapper<bussinessMessage> {
    // 用于查询销售对应的工时
    @Select("SELECT project_name,pingtime,rem from bussiness_message WHERE pingtime>0 ")
    public List<BusVO> findAllBusVO();

    // 用于查询当前需要指定销售人员的工时
    @Select("SELECT project_name,pingtime from bussiness_message WHERE pingtime>0 and manager =#{name} ")
    public List<BusVO> findAllBusVOByManager(String name);

    //查询数据库中项目性质的比例
    @Select("SELECT STATUS,COUNT(*) AS 'CON' from bussiness_message GROUP BY STATUS")
    public List<PieVO> findAllStatusVO();

    // 用于查询当前需要指定销售人员的单独的成单率
    @Select("SELECT STATUS,COUNT(*) AS 'CON' from bussiness_message WHERE manager=#{name} GROUP BY STATUS")
    public List<PieVO> findAllStatusVOByManager(String name);

}
