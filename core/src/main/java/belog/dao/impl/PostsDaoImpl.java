package belog.dao.impl;


import belog.dao.PostsDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.Posts;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository("PostsDao")
public class PostsDaoImpl extends CommonDaoImpl<Posts> implements PostsDao {
}
