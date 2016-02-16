package belog.dao.impl;


import belog.dao.TermRelationshipsDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.TermRelationships;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository("TermRelationshipsDao")
public class TermRelationshipsDaoImpl extends CommonDaoImpl<TermRelationships> implements TermRelationshipsDao {
}
