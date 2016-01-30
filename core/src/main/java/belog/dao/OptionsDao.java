package belog.dao;

import belog.dao.common.CommonDao;
import belog.pojo.po.Options;

/**
 * @author Beldon
 */
public interface OptionsDao extends CommonDao<Options> {

    /**
     * 根据名字查找
     *
     * @param name
     * @return
     */
    Options findByName(String name);
}
