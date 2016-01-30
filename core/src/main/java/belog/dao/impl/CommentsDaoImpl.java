package belog.dao.impl;


import belog.dao.CommentsDao;
import belog.dao.common.impl.CommonDaoImpl;
import belog.pojo.po.Comments;
import org.springframework.stereotype.Repository;

/**
 * @author Beldon
 */
@Repository
public class CommentsDaoImpl extends CommonDaoImpl<Comments> implements CommentsDao {
}
