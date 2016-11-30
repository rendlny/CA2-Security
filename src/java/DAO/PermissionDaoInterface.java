package DAO;

import java.sql.SQLException;
import java.util.ArrayList;

import DTO.Permission;

/**
 * 
 * @author Friedrich
 */
public interface PermissionDaoInterface {
	public ArrayList<Permission> getPermissionsByRoleType(int roleType) throws SQLException;

	public boolean addPermission(Permission permission) throws SQLException;

	public boolean removePermission(Permission permission) throws SQLException;
}
