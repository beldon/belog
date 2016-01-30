package belog.dao;


import belog.dao.common.CommonDao;
import belog.pojo.po.Role;

/**
 * @author Beldon
 */
public interface RoleDao extends CommonDao<Role> {
    Role findByName(String name);
}
