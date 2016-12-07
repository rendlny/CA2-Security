package Utils;

import DAO.PermissionDao;
import DAO.UserDao;
import DTO.Permission;
import DTO.User;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 
 * @author Friedrich
 *
 */
public class SecurityUtils {
    PermissionDao permissionDao;
    private static SecurityUtils instance;
    
    /**
     * Instanciating a new SecurityUtils.
     */
    public SecurityUtils() {
        permissionDao =  new PermissionDao("library");
    }
    
    /**
     * Returns a singleton of SecurityUtils.
     * 
     * @return - SecurityUtils
     */
    public static SecurityUtils getInstance() {
        if(instance==null) {
            instance = new SecurityUtils();
        }
        return instance;
    }

    /**
     * Method for Admins to set new Permissions.
     * 
     * @param user - the (Admin-)user trying to set a Permission
     * @param roleType - type of the Role for wich the new Permission is allowing somethig.
     * @param PermissionName - Name of the New Permission (must be unique).
     * @throws SQLException - If something goes wrong at the DB.
     * @throws NotAuthorizedException - if u are no Admin.
     */
    public void setPermission(User user, int roleType, String PermissionName) throws SQLException, NotAuthorizedException {
        if (user.isIs_admin()) {
            Permission permission = new Permission(roleType, PermissionName);
            permissionDao.addPermission(permission);
        } else {
            throw new NotAuthorizedException("only an Admin can set new Permissions!");
        }
        
    }
    
    /**
     * Gets all Permissions and checks if the User, passed as a parameter has the given Permission.
     * 
     * @param user - logged User expected
     * @param PermissionName - Sting PermissionName
     * @return
     * @throws SQLException 
     */
    public boolean hasPermission(User user, String PermissionName) throws SQLException {
        //check role first.
        if (isActualRole(user)) {
            //convert is_Admin to int
            int roleType = user.isIs_admin() ? 0 : 1;

            //Get Permissions for roleType from PermissionTable
            ArrayList<Permission> permissions = permissionDao.getPermissionsByRoleType(roleType);

            //create new permission for user
            Permission permission = new Permission(roleType, PermissionName);

            //validate Permission
            if(permissions.contains(permission)) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Verify that the Users role didn't change.
     * 
     * @param user
     * @return boolean
     */
    private boolean isActualRole(User user) {
        UserDao udao = new UserDao("library");
        User newUser = udao.getUserById(user.getUser_id());
        if((user.isIs_admin()&&newUser.isIs_admin()) || (!user.isIs_admin()&&!newUser.isIs_admin())) {
            return true;
        }
        return false;
    }
}
