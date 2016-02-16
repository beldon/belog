package belog.service.impl;


import belog.dao.RoleDao;
import belog.pojo.po.Role;
import belog.pojo.vo.RoleVo;
import belog.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beldon
 */
@Service("RoleService")
public class RoleServiceImpl extends BaseService implements RoleService {

    @Autowired
    private RoleDao roleDao;

    public void saveOrUpdate(RoleVo roleVo) {
        Role role = roleDao.findByName(roleVo.getName());
        if (role != null) {
            role.setDescription(roleVo.getDescription());
            roleDao.updateEntity(role);
        }else {
            role = new Role();
            BeanUtils.copyProperties(roleVo, role);
            roleDao.saveEntity(role);
        }
    }

    public void delete(long id) {
        roleDao.delete(id);
    }

    public RoleVo getRoleById(long id) {
        RoleVo roleVo = new RoleVo();
        Role role = roleDao.findById(id);
        if (role != null) {
            BeanUtils.copyProperties(role, roleVo);
        }
        return roleVo;
    }

    public RoleVo getRoleByName(String name) {
        RoleVo roleVo = new RoleVo();
        Role role = roleDao.findByName(name);
        if (role != null) {
            BeanUtils.copyProperties(role, roleVo);
        }
        return roleVo;
    }

    public List<RoleVo> getAll() {
        List<RoleVo> list = new ArrayList<RoleVo>();
        List<Role> roleList = roleDao.findAll();
        for (Role role : roleList) {
            RoleVo roleVo = new RoleVo();
            BeanUtils.copyProperties(role, roleVo);
            list.add(roleVo);
        }
        return list;
    }
}
