package belog.service;


import belog.pojo.po.Menu;

import java.util.List;

/**
 * Created by Beldon
 */
public interface MenuService {

    /**
     * 添加或更新菜单
     *
     * @param menu
     */
    void addOrUpdate(Menu menu);


    /**
     * 根据ID查找菜单
     *
     * @param id
     */
    void deleteMenuById(Long id);

    Menu findById(long id);

    /**
     * 查找所有菜单
     *
     * @return
     */
    List<Menu> findAll();

    List<Menu> findCurrentThemeMenu();

    /**
     * 获取当前菜单类型
     * @return
     */
    String getCurrentMenuType();
}
