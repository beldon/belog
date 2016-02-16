package belog.dao.impl;


import belog.dao.UserMetaDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.UserMeta;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository("UserMetaDao")
public class UserMetaDaoImpl extends CommonDaoImpl<UserMeta> implements UserMetaDao {
}
