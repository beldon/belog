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
}
