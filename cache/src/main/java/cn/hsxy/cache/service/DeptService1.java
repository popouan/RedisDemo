package cn.hsxy.cache.service;


import cn.hsxy.cache.entity.Dept;
import cn.hsxy.cache.mapper.DeptMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class DeptService1 {
    @Autowired
    DeptMapper deptMapper;
    @Autowired
    RedisTemplate redisTemplate;
    static String cacheNames="dept::";
   // @Cacheable(cacheNames = "depts",unless = "#result==null")
    public Dept findById(String id) {
        Object object=redisTemplate.opsForValue().get(cacheNames+id);
        if(object!=null)
            return (Dept) object;
        Dept dept= deptMapper.findById(id);
        if(dept!=null)
            redisTemplate.opsForValue().set(cacheNames+id,dept,1, TimeUnit.HOURS);
        return dept;
    }
  //  @CachePut(cacheNames = "depts",key="#result.id")//新增dept数据同时存放到缓存空间depts中
    public Dept insert(Dept dept) { //返回必须是缓存中value类型，这里为Dept
        int row=deptMapper.insert(dept);
        if(row>0){
            redisTemplate.opsForValue().set(cacheNames+dept.getId(),dept,1, TimeUnit.HOURS);
            return dept;
        }
        return null;
    }


  //  @CachePut(cacheNames = "depts",key = "#result.id")//修改dept数据同步到缓存depts中
    public Dept update(Dept dept) {
        int row=deptMapper.update(dept);
        if(row>0){
            redisTemplate.opsForValue().set(cacheNames+dept.getId(),dept);
            return dept;
        }
        return null;
    }
   //     @CacheEvict(cacheNames = "depts")
       public int delete(String id){ //从缓存空间depts中删除key(id值)对应value(Dept值)
        int row=deptMapper.delete(id);
        if(row>0){
            redisTemplate.delete(cacheNames+id);
        }
        return  row;
    }

}
