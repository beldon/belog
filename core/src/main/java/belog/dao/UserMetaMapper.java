package belog.dao;

import belog.pojo.po.UserMeta;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMetaMapper {
    @Delete({
        "delete from t_user_meta",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_user_meta (key, value, ",
        "user_id)",
        "values (#{key,jdbcType=VARCHAR}, #{value,jdbcType=VARCHAR}, ",
        "#{userId,jdbcType=BIGINT})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(UserMeta record);

    int insertSelective(UserMeta record);

    @Select({
        "select",
        "id, key, value, user_id",
        "from t_user_meta",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    UserMeta selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserMeta record);

    @Update({
        "update t_user_meta",
        "set key = #{key,jdbcType=VARCHAR},",
          "value = #{value,jdbcType=VARCHAR},",
          "user_id = #{userId,jdbcType=BIGINT}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(UserMeta record);
}