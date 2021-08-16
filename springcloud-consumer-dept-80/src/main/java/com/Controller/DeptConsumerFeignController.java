package com.Controller;

import com.feign.DeptFeign;
import com.pojo.Dept;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class DeptConsumerFeignController {

    @Resource
    private RestTemplate restTemplate;

    //    private static final String REST_URL_PREFIX = "http://localhost:8001";
//    private static final String REST_URL_PREFIX = "http://springcloud-proviter-dept";
    @Resource
    private DeptFeign deptFeign;

    @RequestMapping("/consumer/feign/dept/add")
    public boolean addDept(Dept dept){
        return this.deptFeign.addDept(dept);
    }

    @RequestMapping("/consumer/feign/dept/get/{id}")
    public Dept getDept(@PathVariable("id") Long id){
        return this.deptFeign.queryById(id);
    }

    @RequestMapping("/consumer/feign/dept/list")
    public List<Dept> DeptList(){
        return this.deptFeign.queryAll();
    }

}
