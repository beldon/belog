package belog.service.impl;


import belog.pojo.vo.RoleVo;
import belog.service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Beldon
 */
@Service("RoleService")
public class RoleServiceImpl extends BaseService implements RoleService {


    public void saveOrUpdate(RoleVo roleVo) {

    }

    public void delete(long id) {

    }

    public RoleVo getRoleById(long id) {
        return null;
    }

    public RoleVo getRoleByName(String name) {
        return null;
    }

    public List<RoleVo> getAll() {
        return null;
    }
}
