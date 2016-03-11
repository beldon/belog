package belog.dao;

import belog.pojo.Page;
import belog.pojo.po.Posts;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PostsMapper {
    @Delete({
        "delete from t_posts",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_posts (comment_count, comment_status, ",
        "content_filtered, cre_date, ",
        "excerpt, menu_order, ",
        "mime_type, modified, ",
        "name, parent, password, ",
        "ping_status, pinged, ",
        "status, title, to_ping, ",
        "type, user_id, content)",
        "values (#{commentCount,jdbcType=BIGINT}, #{commentStatus,jdbcType=VARCHAR}, ",
        "#{contentFiltered,jdbcType=VARCHAR}, #{creDate,jdbcType=TIMESTAMP}, ",
        "#{excerpt,jdbcType=VARCHAR}, #{menuOrder,jdbcType=INTEGER}, ",
        "#{mimeType,jdbcType=VARCHAR}, #{modified,jdbcType=TIMESTAMP}, ",
        "#{name,jdbcType=VARCHAR}, #{parent,jdbcType=BIGINT}, #{password,jdbcType=VARCHAR}, ",
        "#{pingStatus,jdbcType=VARCHAR}, #{pinged,jdbcType=VARCHAR}, ",
        "#{status,jdbcType=VARCHAR}, #{title,jdbcType=VARCHAR}, #{toPing,jdbcType=VARCHAR}, ",
        "#{type,jdbcType=VARCHAR}, #{userId,jdbcType=BIGINT}, #{content,jdbcType=LONGVARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Posts record);

    int insertSelective(Posts record);

    @Select({
        "select",
        "id, comment_count, comment_status, content_filtered, cre_date, excerpt, menu_order, ",
        "mime_type, modified, name, parent, password, ping_status, pinged, status, title, ",
        "to_ping, type, user_id, content",
        "from t_posts",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("ResultMapWithBLOBs")
    Posts selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Posts record);

    @Update({
        "update t_posts",
        "set comment_count = #{commentCount,jdbcType=BIGINT},",
          "comment_status = #{commentStatus,jdbcType=VARCHAR},",
          "content_filtered = #{contentFiltered,jdbcType=VARCHAR},",
          "cre_date = #{creDate,jdbcType=TIMESTAMP},",
          "excerpt = #{excerpt,jdbcType=VARCHAR},",
          "menu_order = #{menuOrder,jdbcType=INTEGER},",
          "mime_type = #{mimeType,jdbcType=VARCHAR},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "name = #{name,jdbcType=VARCHAR},",
          "parent = #{parent,jdbcType=BIGINT},",
          "password = #{password,jdbcType=VARCHAR},",
          "ping_status = #{pingStatus,jdbcType=VARCHAR},",
          "pinged = #{pinged,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "to_ping = #{toPing,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT},",
          "content = #{content,jdbcType=LONGVARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKeyWithBLOBs(Posts record);

    @Update({
        "update t_posts",
        "set comment_count = #{commentCount,jdbcType=BIGINT},",
          "comment_status = #{commentStatus,jdbcType=VARCHAR},",
          "content_filtered = #{contentFiltered,jdbcType=VARCHAR},",
          "cre_date = #{creDate,jdbcType=TIMESTAMP},",
          "excerpt = #{excerpt,jdbcType=VARCHAR},",
          "menu_order = #{menuOrder,jdbcType=INTEGER},",
          "mime_type = #{mimeType,jdbcType=VARCHAR},",
          "modified = #{modified,jdbcType=TIMESTAMP},",
          "name = #{name,jdbcType=VARCHAR},",
          "parent = #{parent,jdbcType=BIGINT},",
          "password = #{password,jdbcType=VARCHAR},",
          "ping_status = #{pingStatus,jdbcType=VARCHAR},",
          "pinged = #{pinged,jdbcType=VARCHAR},",
          "status = #{status,jdbcType=VARCHAR},",
          "title = #{title,jdbcType=VARCHAR},",
          "to_ping = #{toPing,jdbcType=VARCHAR},",
          "type = #{type,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Posts record);

    @Select({
            "select",
            "id, comment_count, comment_status, content_filtered, cre_date, excerpt, menu_order, ",
            "mime_type, modified, name, parent, password, ping_status, pinged, status, title, ",
            "to_ping, type, user_id, content",
            "from t_posts",
            "order by cre_date desc"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<Posts> findPage(Page<Posts> page);

    @Select({
            "select",
            "post.id, post.comment_count, post.comment_status, post.content_filtered, post.cre_date, post.excerpt, post.menu_order, ",
            "post.mime_type, post.modified, post.name, post.parent, post.password, post.ping_status, post.pinged, post.status, post.title, ",
            "post.to_ping, post.type, post.user_id, post.content",
            "from t_posts post, t_taxonomy_relationships relation",
            "where post.id = relation.object_id and relation.type = 'post_cat' and relation.taxonomy_id = #{catId}",
            "order by post.cre_date desc"
    })
    @ResultMap("ResultMapWithBLOBs")
    List<Posts> findPageCat(@Param("catId") Long catId, Page<Posts> page);
}