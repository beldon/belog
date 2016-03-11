package belog.dao;

import belog.pojo.po.CommentsMeta;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentsMetaMapper {
    @Delete({
        "delete from t_comment_meta",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_comment_meta (key, value, ",
        "comment_id)",
        "values (#{key,jdbcType=VARCHAR}, #{value,jdbcType=VARCHAR}, ",
        "#{commentId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(CommentsMeta record);

    int insertSelective(CommentsMeta record);

    @Select({
        "select",
        "id, key, value, comment_id",
        "from t_comment_meta",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    CommentsMeta selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CommentsMeta record);

    @Update({
        "update t_comment_meta",
        "set key = #{key,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR},",
          "comment_id = #{commentId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(CommentsMeta record);
}