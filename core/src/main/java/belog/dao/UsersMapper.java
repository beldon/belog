package belog.dao;

import belog.pojo.Page;
import belog.pojo.po.Users;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectKey;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UsersMapper {
    @Delete({
        "delete from t_users",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int deleteByPrimaryKey(Long id);

    @Insert({
        "insert into t_users (activation_key, display_name, ",
        "email, login, nickname, ",
        "pass, registered, ",
        "status, url)",
        "values (#{activationKey,jdbcType=VARCHAR}, #{displayName,jdbcType=VARCHAR}, ",
        "#{email,jdbcType=VARCHAR}, #{login,jdbcType=VARCHAR}, #{nickname,jdbcType=VARCHAR}, ",
        "#{pass,jdbcType=VARCHAR}, #{registered,jdbcType=TIMESTAMP}, ",
        "#{status,jdbcType=INTEGER}, #{url,jdbcType=VARCHAR})"
    })
    @SelectKey(statement="SELECT LAST_INSERT_ID()", keyProperty="id", before=false, resultType=Long.class)
    int insert(Users record);

    int insertSelective(Users record);

    @Select({
        "select",
        "id, activation_key, display_name, email, login, nickname, pass, registered, ",
        "status, url",
        "from t_users",
        "where id = #{id,jdbcType=BIGINT}"
    })
    @ResultMap("BaseResultMap")
    Users selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Users record);

    @Update({
        "update t_users",
        "set activation_key = #{activationKey,jdbcType=VARCHAR},",
          "display_name = #{displayName,jdbcType=VARCHAR},",
          "email = #{email,jdbcType=VARCHAR},",
          "login = #{login,jdbcType=VARCHAR},",
          "nickname = #{nickname,jdbcType=VARCHAR},",
          "pass = #{pass,jdbcType=VARCHAR},",
          "registered = #{registered,jdbcType=TIMESTAMP},",
          "status = #{status,jdbcType=INTEGER},",
          "url = #{url,jdbcType=VARCHAR}",
        "where id = #{id,jdbcType=BIGINT}"
    })
    int updateByPrimaryKey(Users record);

    @Select({
            "select",
            "id, activation_key, display_name, email, login, nickname, pass, registered, ",
            "status, url",
            "from t_users"
    })
    @ResultMap("BaseResultMap")
    List<Users> findByPage(Page<Users> page);

    @Select({
            "select",
            "id, activation_key, display_name, email, login, nickname, pass, registered, ",
            "status, url",
            "from t_users",
            "where login = #{loginName} LIMIT 1"
    })
    @ResultMap("BaseResultMap")
    Users findByLoginName(String loginName);
}