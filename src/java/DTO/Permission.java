package DTO;
/**
 * 
 * @author Friedrich
 *
 */
public class Permission {
	private int roleType; //maybe call it isAdmin()?
	private String permission;
	
	public Permission(int roleType, String permission) {
		this.roleType = roleType;
		this.permission = permission;
	}

	public int getRoleType() {
		return roleType;
	}

	public void setRoleType(int roleType) {
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
		String roleTypeName;
		switch (this.roleType) {
		case 0:
			roleTypeName = "admin";
			break;
		case 1:
			roleTypeName = "user";
			break;
		default:
			roleTypeName = "user/admin";
			break;
		}
		return roleTypeName + this.permission;
	}
}
