package belog.dao.impl;

import belog.dao.TermsDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.Terms;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository("TermsDao")
public class TermsDaoImpl extends CommonDaoImpl<Terms> implements TermsDao {
    public Terms findOneByNameAndTaxonomy(String name, String taxonomy) {
        StringBuilder hql = new StringBuilder();
        hql.append("SELECT term FROM belog.pojo.po.Terms term JOIN term.termTaxonomy tax");
        hql.append(" WHERE term.name = '" + name + "'");
        hql.append(" AND tax.taxonomy = '" + taxonomy + "'");
        return findOneByHql(hql.toString());
    }
}
