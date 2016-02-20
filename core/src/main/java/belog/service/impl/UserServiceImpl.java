package belog.service.impl;


import belog.dao.RoleDao;
import belog.dao.UsersDao;
import belog.pojo.Msg;
import belog.pojo.PageModel;
import belog.pojo.po.Role;
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
    private UsersDao usersDao;

    @Autowired
    private RoleDao roleDao;

    public void saveOrUpdate(UserVo user) {
        Users users = new Users();
        BeanUtils.copyProperties(user, users);

        Role role = roleDao.findById(user.getRoleId());
        if (role != null) {
            Set<Role> set = new HashSet<Role>();
            set.add(role);
            users.setRoles(set);
        }
        if (user.getId() == 0) {
            usersDao.saveEntity(users);
        } else {
            users.setRegistered(new Date());
            Users u = usersDao.findById(user.getId());
            u.setEmail(users.getEmail());
            if (!StringUtils.isEmpty(user.getPass())) {
                String sha1Pass = new Sha256Hash(users.getPass(), Token.PASSWORD_TOKEN).toString();
                u.setPass(sha1Pass);
            }
            if (users.getRoles() == null) {
                u.setRoles(new HashSet<Role>());
            } else {
                u.setRoles(users.getRoles());
            }
            usersDao.saveEntity(u);
        }
    }

    public void delete(long id) {
        Users u = usersDao.findById(id);
        if (u != null) {
            u.setStatus(1);
            usersDao.updateEntity(u);
        }
    }

    public PageModel findByPage(PageModel pageModel) {
        Users u = new Users();
        u.setStatus(0);
        PageModel pm = this.usersDao.findPageByExample(u, pageModel);

        List<UserVo> userVos = new ArrayList<UserVo>();
        List<Users> usersList = pm.getList();
        if (usersList.size() > 0) {
            for (Users users : usersList) {
                UserVo userVo = new UserVo();
                BeanUtils.copyProperties(users, userVo);
                userVo.setPostCount(users.getPostses().size());
                userVos.add(userVo);
            }
        }

        pm.setList(userVos);

        return pm;
    }

    public UserVo findById(long id) {
        UserVo userVo = new UserVo();
        Users users = usersDao.findById(id);
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
        Users users = usersDao.findByLoginName(loginName);
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
