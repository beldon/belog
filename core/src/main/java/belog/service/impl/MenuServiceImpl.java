package belog.service.impl;


import belog.pojo.vo.MenuVo;
import belog.service.MenuService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Beldon
 */
@Service("MenuService")
public class MenuServiceImpl extends BaseService implements MenuService {


    public void addOrUpdate(MenuVo menuVo) {

    }

    public void addOrUpdateFront(MenuVo menuVo) {

    }

    public void deleteMenuById(Long id) {

    }

    public MenuVo findById(long id) {
        return null;
    }

    public List<MenuVo> findAll() {
        return null;
    }

    public List<MenuVo> findFrontMenu() {
        return null;
    }

    public List<MenuVo> findAuthMenu() {
        return null;
    }
}
