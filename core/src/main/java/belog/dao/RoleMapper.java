package belog.dao;

import belog.pojo.po.Role;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    @Delete({
        "delete from t_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_role (description, role_name)",
        "values (#{description,jdbcType=VARCHAR}, #{roleName,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Role record);

    int insertSelective(Role record);

    @Select({
        "select",
        "id, description, role_name",
        "from t_role",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    @Update({
        "update t_role",
        "set description = #{description,jdbcType=VARCHAR},",
          "role_name = #{roleName,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Role record);
}