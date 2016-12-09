package DTO;
/**
 * 
 * @author Friedrich
 *
 */
public class Permission {
	private String roleType; //maybe call it isAdmin()?
	private String permission;
	
	public Permission(String roleType, String permission) {
		this.roleType = roleType;
		this.permission = permission;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getPermission() {
		return permission;
	}

	public void setPermission(String permission) {
		this.permission = permission;
	}
	
	@Override
	public String toString() {
		return this.roleType + " is allowed to " +  this.permission;
	}
}
