package belog.dao;

import belog.pojo.po.Menu;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuMapper {
    @Delete({
        "delete from t_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_menu (icon, name, ",
        "pid, sort, type, ",
        "url)",
        "values (#{icon,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, ",
        "#{pid,jdbcType=INTEGER}, #{sort,jdbcType=INTEGER}, #{type,jdbcType=VARCHAR}, ",
        "#{url,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Menu record);

    int insertSelective(Menu record);

    @Select({
        "select",
        "id, icon, name, pid, sort, type, url",
        "from t_menu",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Menu selectByPrimaryKey(Long id);


    @Select({
            "select",
            "id, icon, name, pid, sort, type, url",
            "from t_menu order by sort"
    })
    @ResultMap("BaseResultMap")
    List<Menu> selectAll();

    @Select({
            "select",
            "id, icon, name, pid, sort, type, url",
            "from t_menu",
            "where type = #{type} order by sort"
    })
    @ResultMap("BaseResultMap")
    List<Menu> selectAllByType(String type);

    int updateByPrimaryKeySelective(Menu record);

    @Update({
        "update t_menu",
        "set icon = #{icon,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "pid = #{pid,jdbcType=INTEGER},",
          "sort = #{sort,jdbcType=INTEGER},",
          "type = #{type,jdbcType=VARCHAR},",
          "url = #{url,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Menu record);
}