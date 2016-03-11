package belog.dao;

import belog.pojo.po.Options;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface OptionsMapper {
    @Delete({
        "delete from t_options",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_options (auto_load, name, ",
        "type, value)",
        "values (#{autoLoad,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{value,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Options record);

    int insertSelective(Options record);

    @Select({
        "select",
        "id, auto_load, name, type, value",
        "from t_options",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("ResultMapWithBLOBs")
    Options selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Options record);

    @Update({
        "update t_options",
        "set auto_load = #{autoLoad,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(Options record);

    @Update({
        "update t_options",
        "set auto_load = #{autoLoad,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Options record);

    @Select({
            "select",
            "id, auto_load, name, type, value",
            "from t_options",
            "where name = #{name} LIMIT 1"
    })
    @ResultMap("ResultMapWithBLOBs")
    Options selectByName(String name);

    @Select({
            "select",
            "id, auto_load, name, type, value",
            "from t_options",
            "where type = #{type}"
    })
    List<Options> selectByType(String type);
}