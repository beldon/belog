package belog.service;


import belog.pojo.vo.RoleVo;

import java.util.List;

/**
 * Created by Beldon
 */
public interface RoleService {

    void saveOrUpdate(RoleVo roleVo);

    void delete(long id);

    RoleVo getRoleById(long id);

    RoleVo getRoleByName(String name);

    List<RoleVo> getAll();
}
