package cn.hsxy.cache.mapper;

import cn.hsxy.cache.entity.Dept;
import org.apache.ibatis.annotations.*;

@Mapper
public interface DeptMapper {
    @Select("select id,name from t_dept where id=#{id}")
    Dept findById(String id);
    @Insert("insert into t_dept(id,name,location) values (#{id},#{name},#{location})")
    int insert(Dept dept);
    @Delete("delete from t_dept where id=#{id}")
    int delete(String id);
    @Update("update t_dept set name=#{name} where id=#{id}")
    int update(Dept dept);
}
