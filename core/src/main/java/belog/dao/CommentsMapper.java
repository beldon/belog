package belog.dao;

import belog.pojo.po.Comments;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsMapper {
    @Delete({
        "delete from t_comments",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_comments (agent, approved, ",
        "author, author_email, ",
        "author_ip, author_url, ",
        "content, date, ",
        "karma, parent, type, ",
        "post_id, user_id)",
        "values (#{agent,jdbcType=VARCHAR}, #{approved,jdbcType=VARCHAR}, ",
        "#{author,jdbcType=VARCHAR}, #{authorEmail,jdbcType=VARCHAR}, ",
        "#{authorIp,jdbcType=VARCHAR}, #{authorUrl,jdbcType=VARCHAR}, ",
        "#{content,jdbcType=VARCHAR}, #{date,jdbcType=TIMESTAMP}, ",
        "#{karma,jdbcType=INTEGER}, #{parent,jdbcType=BIGINT}, #{type,jdbcType=VARCHAR}, ",
        "#{postId,jdbcType=BIGINT}, #{userId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Comments record);

    int insertSelective(Comments record);

    @Select({
        "select",
        "id, agent, approved, author, author_email, author_ip, author_url, content, date, ",
        "karma, parent, type, post_id, user_id",
        "from t_comments",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Comments selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Comments record);

    @Update({
        "update t_comments",
        "set agent = #{agent,jdbcType=VARCHAR},",
          "approved = #{approved,jdbcType=VARCHAR},",
          "author = #{author,jdbcType=VARCHAR},",
          "author_email = #{authorEmail,jdbcType=VARCHAR},",
          "author_ip = #{authorIp,jdbcType=VARCHAR},",
          "author_url = #{authorUrl,jdbcType=VARCHAR},",
          "content = #{content,jdbcType=VARCHAR},",
          "date = #{date,jdbcType=TIMESTAMP},",
          "karma = #{karma,jdbcType=INTEGER},",
          "parent = #{parent,jdbcType=BIGINT},",
          "type = #{type,jdbcType=VARCHAR},",
          "post_id = #{postId,jdbcType=BIGINT},",
          "user_id = #{userId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Comments record);
}