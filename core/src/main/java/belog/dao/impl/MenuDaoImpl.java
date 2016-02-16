package belog.dao.impl;


import belog.dao.MenuDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.Menu;
import org.springframework.stereotype.Repository;

/**
 * Created by Beldon
 */
@Repository("MenuDao")
public class MenuDaoImpl extends CommonDaoImpl<Menu> implements MenuDao {
}
