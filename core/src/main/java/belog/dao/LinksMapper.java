package belog.dao;

import belog.pojo.po.Links;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LinksMapper {
    @Delete({
        "delete from t_links",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_links (description, image, ",
        "name, notes, rel, ",
        "rss, sort, target, ",
        "updated, url, ",
        "visible, owner)",
        "values (#{description,jdbcType=VARCHAR}, #{image,jdbcType=VARCHAR}, ",
        "#{name,jdbcType=VARCHAR}, #{notes,jdbcType=VARCHAR}, #{rel,jdbcType=VARCHAR}, ",
        "#{rss,jdbcType=VARCHAR}, #{sort,jdbcType=INTEGER}, #{target,jdbcType=VARCHAR}, ",
        "#{updated,jdbcType=TIMESTAMP}, #{url,jdbcType=VARCHAR}, ",
        "#{visible,jdbcType=VARCHAR}, #{owner,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Links record);

    int insertSelective(Links record);

    @Select({
        "select",
        "id, description, image, name, notes, rel, rss, sort, target, updated, url, visible, ",
        "owner",
        "from t_links",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Links selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Links record);

    @Update({
        "update t_links",
        "set description = #{description,jdbcType=VARCHAR},",
          "image = #{image,jdbcType=VARCHAR},",
          "name = #{name,jdbcType=VARCHAR},",
          "notes = #{notes,jdbcType=VARCHAR},",
          "rel = #{rel,jdbcType=VARCHAR},",
          "rss = #{rss,jdbcType=VARCHAR},",
          "sort = #{sort,jdbcType=INTEGER},",
          "target = #{target,jdbcType=VARCHAR},",
          "updated = #{updated,jdbcType=TIMESTAMP},",
          "url = #{url,jdbcType=VARCHAR},",
          "visible = #{visible,jdbcType=VARCHAR},",
          "owner = #{owner,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Links record);

    @Select({
            "select",
            "id, description, image, name, notes, rel, rss, sort, target, updated, url, visible, ",
            "owner",
            "from t_links"
    })
    @ResultMap("BaseResultMap")
    List<Links> findAll();
}