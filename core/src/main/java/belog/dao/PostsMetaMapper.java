package belog.dao;

import belog.pojo.po.PostsMeta;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
public interface PostsMetaMapper {
    @Delete({
        "delete from t_post_meta",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_post_meta (value, key, ",
        "post_id)",
        "values (#{value,jdbcType=VARCHAR}, #{key,jdbcType=VARCHAR}, ",
        "#{postId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(PostsMeta record);

    int insertSelective(PostsMeta record);

    @Select({
        "select",
        "id, value, key, post_id",
        "from t_post_meta",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    PostsMeta selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PostsMeta record);

    @Update({
        "update t_post_meta",
        "set value = #{value,jdbcType=VARCHAR},",
          "key = #{key,jdbcType=VARCHAR},",
          "post_id = #{postId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(PostsMeta record);

    @Select({
            "select",
            "id, value, key, post_id",
            "from t_post_meta",
            "where post_id = #{postId} and key = #{key} LIMIT 1"
    })
    @ResultMap("BaseResultMap")
    PostsMeta findOneByKey(@Param("postId") Long postId, @Param("key") String key);

    @Delete({
            "delete from t_post_meta",
            "where post_id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPostId(long id);

    @Delete({
            "delete from t_post_meta",
            "where post_id = #{postId,jdbcType=BIGINT} and key = #{key}"
    })
    int deleteByPostIdAndKey(@Param("postId") Long postId, @Param("key") String key);
}