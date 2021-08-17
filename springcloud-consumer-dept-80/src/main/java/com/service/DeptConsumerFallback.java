package com.service;


import com.feign.DeptFeign;
import com.pojo.Dept;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

//降级
@Component
public class DeptConsumerFallback implements FallbackFactory {
    @Override
    public Object create(Throwable throwable) {
        return new DeptFeign() {
            @Override
            public Dept queryById(Long id) {
                Dept dept = new Dept();
                dept.setDeptno(id);
                dept.setDname("id=>"+id+"没有对应的信息，客户端提供了降级服务，这个服务现在已经被关闭");
                dept.setDb_source("没有数据~");
                return dept;
            }

            @Override
            public List<Dept> queryAll() {
                return null;
            }

            @Override
            public boolean addDept(Dept dept) {
                return false;
            }
        };
    }
}
