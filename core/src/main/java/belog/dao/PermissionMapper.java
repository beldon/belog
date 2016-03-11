package belog.dao;

import belog.pojo.po.Permission;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper {
    @Delete({
        "delete from t_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_permission (description, name, ",
        "pid, value)",
        "values (#{description,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{pid,jdbcType=INTEGER}, #{value,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Permission record);

    int insertSelective(Permission record);

    @Select({
        "select",
        "id, description, name, pid, value",
        "from t_permission",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Permission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Permission record);

    @Update({
        "update t_permission",
        "set description = #{description,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=INTEGER},",
          "value = #{value,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Permission record);
}