package belog.dao.impl;


import belog.dao.TermTaxonomyDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.TermTaxonomy;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository("TermTaxonomyDao")
public class TermTaxonomyDaoImpl extends CommonDaoImpl<TermTaxonomy> implements TermTaxonomyDao {
}
