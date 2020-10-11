package com.southwind.layui.controller;

import com.southwind.layui.entity.User;
import com.southwind.layui.entity.bussinessMessage;
import com.southwind.layui.entity.bussinessName;
import com.southwind.layui.mapper.BussinessMapper;
import com.southwind.layui.mapper.BussinessNameMapper;
import com.southwind.layui.service.ProductService;
import com.southwind.layui.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private BussinessMapper mapper;

    @Autowired
    private BussinessNameMapper mapperName;

    @RequestMapping("/List")
    @ResponseBody
    public DataVO List(Integer page,Integer limit){
        return productService.findData(page, limit);
    }

    @GetMapping("/{url}")
    public String redirect(@PathVariable("url") String url){
        return url;
    }

    @RequestMapping("/barVO")
    @ResponseBody
    public BarVO getBarVO(){
        return productService.getBarVO();
    }

    /**
     * @20200915 反馈总览数据
     * @Masir
     */
    @RequestMapping("/List1")
    @ResponseBody
    public DataVO List1(){
        return productService.findData1();
    }

    /**
     * @20200925 这个接口是用来测试根据销售人员来查询数据库
     * @Masir
     */
    @RequestMapping("/List3")
    @ResponseBody
    public DataVO List3(){
        return productService.findData3("陈爱");
    }

    //销售成员名称接口
    @RequestMapping("/List2")
    @ResponseBody
    public DataVO List2(){
        return productService.findData2();
    }

    //测试项目
    @RequestMapping(value = "/List4",method  = RequestMethod.GET,params = {"opt"})
    @ResponseBody
    public String index(String name){
        System.out.println(name);
        System.out.println("测试通过");
        return "index";
    }

    //测试数据保存的功能
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public String save(User user){
        System.out.println(user);
        return "index";

    }

    //add功能

    /**
     *
     * @20200921  新增数据
     * @Masir
     */
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public String save(bussinessMessage bussinessMessage1){
        System.out.println(bussinessMessage1);
        bussinessMessage mes = new bussinessMessage();
        mes.setID(bussinessMessage1.getID());
        mes.setProjectName(bussinessMessage1.getProjectName());
        mes.setCustomName(bussinessMessage1.getCustomName());
        mes.setCustomAddress(bussinessMessage1.getCustomAddress());
        mes.setBussiness(bussinessMessage1.getBussiness());
        mes.setManager(bussinessMessage1.getManager());
        mes.setIndustry(bussinessMessage1.getIndustry());
        mes.setTime(bussinessMessage1.getTime());
        mes.setRem(bussinessMessage1.getRem());
        mes.setAssessor(bussinessMessage1.getAssessor());
        mes.setModel(bussinessMessage1.getModel());
        mes.setNature(bussinessMessage1.getNature());
        mes.setPingtime(bussinessMessage1.getPingtime());
        mes.setStatus(bussinessMessage1.getStatus());
        mapper.insert(mes);
        System.out.println("插入成功");
        return "index";

    }
    //add功能

    /**
     *
     * @20200921  新增销售人员信息
     * @Masir
     */
    @RequestMapping(value = "/addmanager",method = RequestMethod.POST)
    public String save(bussinessName busName){
        System.out.println(busName);
        bussinessName mes = new bussinessName();
        mes.setID(busName.getID());
        mes.setName(busName.getName());
        mes.setBumen(busName.getBumen());
        mes.setGonghao(busName.getGonghao());
        mapperName.insert(mes);
        System.out.println("插入成功");
        return "index";

    }

    //Delete功能
    /**
     *
     * @20200922  删除销售评估数据
     * @Masir
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST,params = {"id"})
    public String delete(String id){
        System.out.println(id);
        int row = mapper.deleteById(id);
        System.out.println("删除条数" + row);
        return "index1";
    }

    //Delete功能
    /**
     *
     * @20200922  删除销售成员数据
     * @Masir
     */
    @RequestMapping(value = "/deleteManager",method = RequestMethod.POST,params = {"id"})
    public String deleteManager(String id){
        System.out.println(id);
        int row = mapperName.deleteById(id);
        System.out.println("删除条数" + row);
        return "index1";
    }


    //find查询功能
    /**
     *
     * @20200925  查询功能（人员）
     * @Masir
     * @return
     */
    @RequestMapping(value = "/find",method ={RequestMethod.GET,RequestMethod.POST},params = {"id"})
    @ResponseBody
    public DataVO<bussinessVO> find(String id){
        System.out.println(id);
        return productService.findData3(id);

    }

    //find查询功能（项目性质）
    /**
     *
     * @20200928 查询功能
     * @Masir
     * @return
     */
    @RequestMapping(value = "/findstatus",method ={RequestMethod.GET,RequestMethod.POST},params = {"stu"})
    @ResponseBody
    public DataVO<bussinessVO> findstatus(String stu){
        System.out.println(stu);
        return productService.SalesStatus(stu);

    }

    /**
     *
     * @20200928 联合查询
     * @Masir
     * @return
     */
    @RequestMapping(value = "/comfind",method ={RequestMethod.GET,RequestMethod.POST},params = {"com","name"})
    @ResponseBody
    public DataVO<bussinessVO> comfind(String com,String name){
        System.out.println(com);
        return productService.CombineFind(com,name);

    }

    //edit功能
    /**
     *
     * @20200923  编辑和修改数据
     * @Masir
     */
    @RequestMapping(value = "/edit",method = RequestMethod.POST)
    public String edit(bussinessMessage bussinessMessage2){
        System.out.println(bussinessMessage2);
        bussinessMessage mes = mapper.selectById(bussinessMessage2.getID());
        mes.setID(bussinessMessage2.getID());
        mes.setProjectName(bussinessMessage2.getProjectName());
        mes.setCustomName(bussinessMessage2.getCustomName());
        mes.setCustomAddress(bussinessMessage2.getCustomAddress());
        mes.setBussiness(bussinessMessage2.getBussiness());
        mes.setManager(bussinessMessage2.getManager());
        mes.setIndustry(bussinessMessage2.getIndustry());
        mes.setTime(bussinessMessage2.getTime());
        mes.setRem(bussinessMessage2.getRem());
        mes.setAssessor(bussinessMessage2.getAssessor());
        mes.setModel(bussinessMessage2.getModel());
        mes.setNature(bussinessMessage2.getNature());
        mes.setPingtime(bussinessMessage2.getPingtime());
        mes.setStatus(bussinessMessage2.getStatus());
        int rows = mapper.updateById(mes);
        System.out.println("影响记录数:" +rows);
        return "index1";

    }

    //edit功能
    /**
     *
     * @20200923  编辑和修改数据
     * @Masir
     */
    @RequestMapping(value = "/editmanager",method = RequestMethod.POST)
    public String editmanager(bussinessName busNameedit){
        System.out.println(busNameedit);
        bussinessName mes = mapperName.selectById(busNameedit.getID());
        mes.setID(busNameedit.getID());
        mes.setName(busNameedit.getName());
        mes.setBumen(busNameedit.getBumen());
        mes.setGonghao(busNameedit.getGonghao());
        int rows = mapperName.updateById(mes);
        System.out.println("影响记录数:" +rows);
        return "index1";

    }

    /**
     * Desprition:这是负责反馈销售人员工时消耗的逻辑
     * @return
     */
    @RequestMapping("/BusVO")
    @ResponseBody
    public BusTVO getBusVO(){ return productService.getBusVO();}

    /**
     * Desprition:这是负责反馈销售人员工时消耗的逻辑，按照姓名进行查询
     * @return
     */
    @RequestMapping(value = "/BusVOByManager",method ={RequestMethod.GET,RequestMethod.POST},params = {"name"})
    @ResponseBody
    public BusTVO getBusVOByManager(String name){
        return productService.getBusVOByManager(name);
    }

    /**
     * 测试函数，可删除
     * @return
     */
    @RequestMapping(value ="/pieVO")
    @ResponseBody
    public List<BusVO> getPieVO(){return mapper.findAllBusVO();}


    /**
     * Desprition:这是用来负责反馈总成单率的逻辑
     * @return
     */
    @RequestMapping(value ="/pieVO1")
    @ResponseBody
    public List<PieVO> getPieVO1(){return mapper.findAllStatusVO();}


    @RequestMapping(value ="/pieVO2",method ={RequestMethod.GET,RequestMethod.POST},params = {"name"})
    @ResponseBody
    public List<PieVO> getPieVO2(String name){return mapper.findAllStatusVOByManager(name);}

}

