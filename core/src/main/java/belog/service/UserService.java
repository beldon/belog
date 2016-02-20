package belog.service;


import belog.pojo.Msg;
import belog.pojo.PageModel;
import belog.pojo.vo.UserVo;

/**
 * @author Beldon
 */
public interface UserService {

    /**
     * 添加或更新一个用户
     *
     * @param user
     */
    void saveOrUpdate(UserVo user);

    /**
     * 禁用用户，家删除
     *
     * @param id
     */
    void delete(long id);

    /**
     * 分页查询
     *
     * @param pageModel
     * @return
     */
    PageModel findByPage(PageModel pageModel);


    /**
     * 根据ID查找用户
     *
     * @param id
     * @return
     */
    UserVo findById(long id);

    /**
     * 用户登陆
     *
     * @param username 登陆用户名
     * @param password 密码
     * @return
     */
    Msg login(String username, String password);

    /**
     * 根据用户登陆名查找用户
     *
     * @param loginName
     * @return
     */
    UserVo findUserByLoginName(String loginName);

    /**
     * 获取当前登录的用户名
     * @return
     */
    String getCurrentUserName();

    /**
     * 获取当前用户信息
     * @return
     */
    UserVo getCurrentUser();
}
