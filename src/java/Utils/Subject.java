package Utils;

import java.util.ArrayList;

import DTO.Permission;
import DTO.User;

/**
 * Subject Class for RBAC, contains a User and a List of Permissions for this User.
 * 
 * @author Friedrich
 */
public class Subject {
	private User user;
	private ArrayList<Permission> permissions;
	
	/**
	 * creates a Subject Object with a Given User and his Permissions.
	 * 
	 * @param user - DTO.User, current User
	 * @param permissions - permissions for the given user.
	 */
	public Subject(User user, ArrayList<Permission> permissions) {
		this.user = user;
		this.permissions = permissions;
	}
	
	/**
	 * Method to check if a User is allowed to do a specific action.
	 * 
	 * @param permissionName
	 * @return
	 */
	public boolean hasPermission(String permissionName) {
		for (Permission permission : permissions) {
			if(permission.getPermission().equals(permissionName)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Method to compare a given role to this subjects users role. 
	 * 
	 * @param role
	 * @return
	 */
	public boolean hasRole(String role) {
		if(user.getRole_type_name().equals(role)) {
			return true;
		}
		return false;
	}
}
