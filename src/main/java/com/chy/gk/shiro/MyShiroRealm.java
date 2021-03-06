package com.chy.gk.shiro;

import com.chy.gk.model.uesr.Permission;
import com.chy.gk.model.uesr.Role;
import com.chy.gk.model.uesr.User;
import com.chy.gk.service.UserService;
import com.chy.gk.service.VerificationCodeService;
import com.chy.gk.util.PhoneNumCheckUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    @Lazy
    private UserService userService;

    @Resource
    @Lazy
    private VerificationCodeService verificationCodeService;


    @Autowired(required = true)
    private RedisSessionDAO redisSessionDAO;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("权限配置-->MyShiroRealm.doGetAuthorizationInfo()");
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        User user  = (User)principals.getPrimaryPrincipal();
        for(Role role:user.getRoleSet()){
            authorizationInfo.addRole(role.getRoleName());
            for(Permission p:role.getPermissionSet()){
                authorizationInfo.addStringPermission(p.getPermissionName());
            }
        }
        return authorizationInfo;
    }

    /*主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。*/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token)
            throws AuthenticationException {
        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
//        //获取用户的输入的账号.
        String principal = (String) token.getPrincipal();
        User user = null;
        SimpleAuthenticationInfo authenticationInfo = null;
        //传进来的token是手机号,就用验证码替代密码进行校验
        if(PhoneNumCheckUtil.checkPhoneNum(principal)){
            user = userService.getUserByPhoneNum(principal);
            if(user == null){
                return null;
            }
            //从缓存里取出验证码
            String code = verificationCodeService.getVerificationCode(principal);
            if(null == code){
                return null;
            }
            authenticationInfo = new SimpleAuthenticationInfo(
                    user, //user对象
                    code, //已加密验证码
                    ByteSource.Util.bytes(PhoneNumCheckUtil.CODEKEY),//salt
                    getName()  //realm name
            );
        } else {
            user = userService.getUserByName(principal);
            if(user == null){
                return null;
            }
            authenticationInfo = new SimpleAuthenticationInfo(
                    user, //user对象，存在redis session里，可自己定义存储内容，如用户名等
                    user.getPassword(), //数据库密码
                    ByteSource.Util.bytes(user.getSalt()),//salt
                    getName()  //realm name
            );
        }
        //通过username从数据库中查找 User对象，如果找到，没找到.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法


        return authenticationInfo;
    }


    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     * @param userIds 已经修改了权限的userId
     */
    public void clearUserAuthByUserId(List<Integer> userIds){
        if(null == userIds || userIds.size() == 0)	return ;
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session:sessions){
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if(null != obj && obj instanceof SimplePrincipalCollection){
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection)obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if(null != obj && obj instanceof User){
                    User user = (User) obj;
                    System.out.println("user:"+user);
                    //比较用户ID，符合即加入集合
                    if(null != user && userIds.contains(user.getId())){
                        list.add(spc);
                    }
                }
            }
        }
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (MyShiroRealm)securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }


    public RedisSessionDAO getRedisSessionDAO() {
        return redisSessionDAO;
    }

    public void setRedisSessionDAO(RedisSessionDAO redisSessionDAO) {
        this.redisSessionDAO = redisSessionDAO;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public VerificationCodeService getVerificationCodeService() {
        return verificationCodeService;
    }

    public void setVerificationCodeService(VerificationCodeService verificationCodeService) {
        this.verificationCodeService = verificationCodeService;
    }
}


