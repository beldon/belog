package belog.dao;


import belog.dao.common.CommonDao;
import belog.pojo.po.Terms;

/**
 * @author Beldon
 */
public interface TermsDao extends CommonDao<Terms> {

    /**
     * 通过name和类型获取Term
     *
     * @param name     名称
     * @param taxonomy 类型
     * @return
     */
    Terms findOneByNameAndTaxonomy(String name, String taxonomy);
}
