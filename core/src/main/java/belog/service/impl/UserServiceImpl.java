package belog.service.impl;


import belog.dao.UsersMapper;
import belog.pojo.Msg;
import belog.pojo.Page;
import belog.pojo.po.Users;
import belog.pojo.vo.UserVo;
import belog.security.token.Token;
import belog.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author Beldon
 */
@Service("UserService")
public class UserServiceImpl extends BaseService implements UserService {

    @Autowired
    private UsersMapper usersMapper;

    public void saveOrUpdate(UserVo user) {
        Users users = new Users();
        BeanUtils.copyProperties(user, users);

        if (user.getId() == 0) {//添加用户
            users.setRegistered(new Date());
            usersMapper.updateByPrimaryKeySelective(users);
        } else {//更新
            users.setRegistered(new Date());
            if (!StringUtils.isEmpty(user.getPass())) {
                String sha1Pass = new Sha256Hash(users.getPass(), Token.PASSWORD_TOKEN).toString();
                users.setPass(sha1Pass);
            }
            usersMapper.updateByPrimaryKeySelective(users);
        }
    }

    public void delete(long id) {
        Users users = new Users();
        users.setId(id);
        users.setStatus(1);
        usersMapper.updateByPrimaryKeySelective(users);
    }

    public Page<UserVo> findByPage(Page<UserVo> page) {
        Page<Users> usersPage = new Page<Users>();
        usersPage.setPageNo(page.getPageNo());
        usersPage.setPageSize(page.getPageSize());
        List<Users> usersList = usersMapper.findByPage(usersPage);

        List<UserVo> userVos = new ArrayList<UserVo>();
        for (Users users : usersList) {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(users, userVo);
            userVos.add(userVo);
        }

        page.setTotalPage(usersPage.getTotalPage());
        page.setTotalRecord(usersPage.getTotalRecord());
        page.setResults(userVos);

        return page;
    }

    public UserVo findById(long id) {
        UserVo userVo = new UserVo();
        Users users = usersMapper.selectByPrimaryKey(id);
        BeanUtils.copyProperties(users, userVo);
        return userVo;
    }

    public Msg login(String username, String password) {
        Msg msg = new Msg();

        Subject subject = SecurityUtils.getSubject();

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        try {
            subject.login(token);
            msg.setStatus(true);
            msg.setErrCode(0);
            msg.setErrMsg("success");
        } catch (AuthenticationException exception) {
            exception.printStackTrace();
            msg.setStatus(false);
            msg.setErrMsg("登陆失败," + exception.getMessage());
            msg.setErrCode(-1);
        }

        return msg;
    }

    public UserVo findUserByLoginName(String loginName) {
        Users users = usersMapper.findByLoginName(loginName);
        if (users == null) {
            return new UserVo();
        } else {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(users, userVo);
            return userVo;
        }
    }

    public String getCurrentUserName() {
        String loginName = "";
        Subject subject = SecurityUtils.getSubject();
        if (subject.isRemembered() || subject.isAuthenticated()) {
            loginName = subject.getPrincipal().toString();
        }
        return loginName;
    }

    public UserVo getCurrentUser() {
        String currentUserName = getCurrentUserName();
        if (org.springframework.util.StringUtils.hasText(currentUserName)) {
            return findUserByLoginName(getCurrentUserName());
        }
        return new UserVo();
    }

}
