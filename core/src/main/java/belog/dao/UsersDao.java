package belog.dao;


import belog.dao.common.CommonDao;
import belog.pojo.po.Users;

/**
 * @author Beldon 2015/11/7
 */
public interface UsersDao extends CommonDao<Users> {
    /**
     * 根据用户登陆名查找用户
     *
     * @param loginName
     * @return
     */
    Users findByLoginName(String loginName);
}
