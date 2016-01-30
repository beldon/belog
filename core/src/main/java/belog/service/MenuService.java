package belog.service;


import belog.pojo.vo.MenuVo;

import java.util.List;

/**
 * Created by Beldon
 */
public interface MenuService {

    /**
     * 添加或更新菜单
     *
     * @param menuVo
     */
    void addOrUpdate(MenuVo menuVo);

    /**
     * 添加或更新前台菜单
     *
     * @param menuVo
     */
    void addOrUpdateFront(MenuVo menuVo);


    /**
     * 根据ID查找菜单
     *
     * @param id
     */
    void deleteMenuById(Long id);

    MenuVo findById(long id);

    /**
     * 查找所有菜单
     *
     * @return
     */
    List<MenuVo> findAll();

    /**
     * 查找前台菜单
     *
     * @return
     */
    List<MenuVo> findFrontMenu();

    /**
     * 查找后台菜单
     *
     * @return
     */
    List<MenuVo> findAuthMenu();
}
