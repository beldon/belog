package belog.dao.impl;


import belog.dao.UsersDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.Users;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository("UsersDao")
public class UsersDaoImpl extends CommonDaoImpl<Users> implements UsersDao {
    public Users findByLoginName(String loginName) {
        Users users = new Users();
        users.setLogin(loginName);
        return findOneByExample(users);
    }
}
