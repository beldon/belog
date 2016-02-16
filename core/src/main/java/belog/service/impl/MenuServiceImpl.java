package belog.service.impl;


import belog.dao.MenuDao;
import belog.pojo.po.Menu;
import belog.pojo.vo.MenuVo;
import belog.service.MenuService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beldon
 */
@Service("MenuService")
public class MenuServiceImpl extends BaseService implements MenuService {

    @Autowired
    private MenuDao menuDao;

    public void addOrUpdate(MenuVo menuVo) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuVo, menu);
        if (menu.getId() != null && menu.getId() != 0) {//更新操作
            menuDao.updateEntity(menu);
        } else {//添加操作
            menuDao.saveEntity(menu);
        }
    }

    public void addOrUpdateFront(MenuVo menuVo) {
        menuVo.setType("front-nav");
        addOrUpdate(menuVo);
    }

    public void deleteMenuById(Long id) {
        menuDao.delete(id);
    }

    public MenuVo findById(long id){
        MenuVo menuVo = new MenuVo();
        Menu menu = menuDao.findById(id);
        BeanUtils.copyProperties(menu, menuVo);
        return menuVo;
    }

    public List<MenuVo> findAll() {
        List<Menu> menus = menuDao.findAll();
        return copyList(menus);
    }

    public List<MenuVo> findFrontMenu() {
        Menu menu = new Menu();
        menu.setType("front-nav");
        List<Menu> menus = menuDao.findByExample(menu);
        return copyList(menus);
    }

    public List<MenuVo> findAuthMenu() {
        Menu menu = new Menu();
        menu.setType("auth-nav");
        List<Menu> menus = menuDao.findByExample(menu);
        return copyList(menus);
    }

    private List<MenuVo> copyList(List<Menu> menus) {
        List<MenuVo> menuVos = new ArrayList<MenuVo>();
        for (int i = 0; i < menus.size(); i++) {
            MenuVo menuVo = new MenuVo();
            BeanUtils.copyProperties(menus.get(i), menuVo);
            menuVos.add(menuVo);
        }
        return menuVos;
    }
}
