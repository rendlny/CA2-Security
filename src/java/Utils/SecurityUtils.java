package Utils;

import java.sql.SQLException;

import DAO.PermissionDao;
import DTO.Permission;
import DTO.User;

/**
 * Security Util Class to handle RBAC.
 * 
 * @author Friedrich
 *
 */
public class SecurityUtils {
    PermissionDao permissionDao;
    private static SecurityUtils instance;
    
    /**
     * Instantiating a new SecurityUtils.
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
    public void setPermission(User user, String roleType, String PermissionName) throws SQLException, NotAuthorizedException {
        if (user.getRole_type_name().equals("admin")) {
            Permission permission = new Permission(roleType, PermissionName);
            permissionDao.addPermission(permission);
        } else {
            throw new NotAuthorizedException("only an Admin can set new Permissions!");
        }
    }

    /**
     * Creates a Subject Object for a specific user.
     * 
     * @param user - DTO.User
     * @return - Subject
     */
    public Subject getSubject(User user) {
    	try {
			return new Subject(user, permissionDao.getPermissionsByRoleType(user.getRole_type_name()));
		} catch (SQLException e) {
			e.printStackTrace(System.out);
			return null;
		}
    }
}
