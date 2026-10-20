package cn.hsxy.cache.service;

import cn.hsxy.cache.entity.Dept;
import cn.hsxy.cache.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
public class DeptService {
    @Autowired
    DeptMapper deptMapper;
    @Cacheable(cacheNames = "depts",unless = "#result==null")
    public Dept findById(String id){
        return deptMapper.findById(id);
    }
    @CachePut(cacheNames = "depts",key ="#result.id" )
    public Dept insert(Dept dept){
         int row = deptMapper.insert(dept);
         if (row > 0){
             return dept;
         }
         return null;
    }
    @CacheEvict(cacheNames = "depts")
    public int delete(String id){
        return deptMapper.delete(id);
    }
    @CachePut(cacheNames = "depts" )
    public Dept update(Dept dept){
        int row =  deptMapper.update(dept);
        if (row > 0){
            return dept;
        }
        return null;
    }
}
