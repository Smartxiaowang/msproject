package bs.common.Global;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @ClassName MyRealm
 * @Description Shiro权限
 * @Author Dear lin
 * @Date 20:02 2022/7/15
 * @Version 1.0
 **/
public class MyRealm extends AuthorizingRealm {
   /* @Resource
    private UserDao userDao;
    @Resource
    private RoleDao roleDao;
    @Resource
    private PermissionDao permissionDao;
*/


    @Override
    public String getName() {
        return "myRealm";
    }

    /**
     * 获取授权数据(角色权限信息)
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取用户的用户名
        String username = (String) principalCollection.iterator().next();
        //根据用户名查询用户角色
        //Set<String> roles = roleDao.getRoleNamesByUsername(username);
        //根据用户名查询用户权限
        ///Set<String> permissions = permissionDao.getPermissionByUsername(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //info.setRoles(roles);
        //info.setStringPermissions(permissions);
        return info;
    }

    /**
     * 获取认证的安全数据（从数据库查询到的用户正确数据）
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //参数authenticationToken就是传递的 subject.login(token)
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        //从token中获取用户名
        String username = token.getUsername();
        //根据用户名从数据库查询用户安全数据
       // User user = userDao.getUserByUsername(username);
       // AuthenticationInfo info = new SimpleAuthenticationInfo(username, user.getPassword(), getName());
        return null;
    }
}
