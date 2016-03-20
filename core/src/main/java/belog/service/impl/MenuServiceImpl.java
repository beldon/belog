package belog.service.impl;


import belog.dao.MenuMapper;
import belog.pojo.po.Menu;
import belog.service.MenuService;
import belog.service.ThemeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.context.Theme;

import java.util.List;

/**
 * Created by Beldon
 */
@Service("MenuService")
public class MenuServiceImpl extends BaseService implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Autowired
    private ThemeService themeService;

    public void addOrUpdate(Menu menu) {
        if (menu.getId() == null || menu.getId() == 0) {//新增
            menuMapper.insertSelective(menu);
        } else {//更新
            menuMapper.updateByPrimaryKeySelective(menu);
        }
    }

    public void deleteMenuById(Long id) {
        menuMapper.deleteByPrimaryKey(id);
    }

    public Menu findById(long id) {
        return menuMapper.selectByPrimaryKey(id);
    }

    public List<Menu> findAll() {
        return menuMapper.selectAll();
    }

    public List<Menu> findCurrentThemeMenu() {
        return menuMapper.selectAllByType(getCurrentMenuType());
    }


    public String getCurrentMenuType() {
        return themeService.getConfigType();
    }
}
