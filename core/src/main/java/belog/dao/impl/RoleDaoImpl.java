package belog.dao.impl;

import belog.dao.RoleDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.Role;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository("RoleDao")
public class RoleDaoImpl extends CommonDaoImpl<Role> implements RoleDao {
    public Role findByName(String name) {
        Role role = new Role();
        role.setName(name);
        role = this.findOneByExample(role);
        return role;
    }
}
