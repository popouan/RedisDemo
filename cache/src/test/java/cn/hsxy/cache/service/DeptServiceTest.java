package cn.hsxy.cache.service;

import cn.hsxy.cache.CacheApplication;
import cn.hsxy.cache.CacheApplicationTests;
import cn.hsxy.cache.entity.Dept;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class DeptServiceTest extends CacheApplicationTests {
    @Autowired
    DeptService1 deptService;
    @Test
    void findById(){
        deptService.findById("001");
        deptService.findById("001");
    }
    @Test
    void insert(){
        Dept dept = new Dept();
        dept.setId("100");
        dept.setName("测试部");
        dept.setLocation("苏州");
        deptService.insert(dept);
        Dept deptGet = deptService.findById("100");
        System.out.println(deptGet);
    }
    @Test
    void update(){
        Dept dept = new Dept();
        dept.setId("100");
        dept.setName("测试部");
        dept.setLocation("杭州");
        deptService.update(dept);
        Dept deptGet = deptService.findById("100");
        System.out.println(deptGet);
    }
    @Test
    void delete(){
        deptService.delete("100");
        Dept deptGet = deptService.findById("100");
        System.out.println(deptGet);
    }

}
