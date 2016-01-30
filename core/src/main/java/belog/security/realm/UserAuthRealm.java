package belog.security.realm;


import belog.pojo.vo.UserVo;
import belog.security.token.Token;
import belog.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Beldon on 2015/9/27.
 */
public class UserAuthRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;


    @Override
//    @Cacheable(value="authorizationCache",key = "#principals.getPrimaryPrincipal()")
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//        Role role = new Role();
//        role.setName((String) principals.getPrimaryPrincipal());
//        authorizationInfo.addStringPermissions(securityService.getRolePermission(role));
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal(); //得到用户名
        String password = new String((char[]) token.getCredentials()); //得到密码


        UserVo userVo = userService.findUserByLoginName(username);
        if (userVo == null) {
            throw new UnknownAccountException(); //如果用户名不存在
        }

        String sha1Pass = new Sha256Hash(password, Token.PASSWORD_TOKEN).toString();
        if (!sha1Pass.endsWith(userVo.getPass())) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }

//        return new SimpleAuthenticationInfo(username, sha1Pass, getName());
        return new SimpleAuthenticationInfo(username, sha1Pass, ByteSource.Util.bytes(Token.PASSWORD_TOKEN), getName());
    }

    @Override
    public void clearCachedAuthorizationInfo(PrincipalCollection principals) {
        super.clearCachedAuthorizationInfo(principals);
    }

    @Override
    public void clearCachedAuthenticationInfo(PrincipalCollection principals) {
        super.clearCachedAuthenticationInfo(principals);
    }

    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }

    public void clearAllCachedAuthorizationInfo() {
        getAuthorizationCache().clear();
    }

    public void clearAllCachedAuthenticationInfo() {
        getAuthenticationCache().clear();
    }

    public void clearAllCache() {
        clearAllCachedAuthenticationInfo();
        clearAllCachedAuthorizationInfo();
    }

}
