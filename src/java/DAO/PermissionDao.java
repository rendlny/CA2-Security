package DAO;

import DTO.Permission;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PermissionDao extends Dao implements PermissionDaoInterface {

	public PermissionDao(String database) {
		super(database);
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns all permissions for a Specific User Role.
	 * 
	 * @param roleType
	 *            of the User
	 * @return {@code ArrayList} of {@code Permission}
	 */
	@Override
	public ArrayList<Permission> getPermissionsByRoleType(int roleType) throws SQLException {
		Permission p;
		ArrayList<Permission> permissions = new ArrayList<>();

		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		con = getConnection();
		String query = "SELECT * FROM permissions WHERE roleType = ?";
		ps = con.prepareStatement(query);
		ps.setString(1, "" + roleType);
		rs = ps.executeQuery();
		while (rs.next()) {
			p = new Permission(rs.getInt("role_type"), rs.getString("permission"));
			permissions.add(p);
		}

		if (rs != null) {
			rs.close();
		}
		if (ps != null) {
			ps.close();
		}
		if (con != null) {
			freeConnection(con);
		}

		return permissions;
	}

	@Override
	public boolean addPermission(Permission permission) throws SQLException {
		Connection con = null;
		PreparedStatement ps = null;

		con = getConnection();
		String query = "INSERT INTO permissions (role_type, permission) VALUES (?, ?)";
		ps = con.prepareStatement(query);
		ps.setInt(1, permission.getRoleType());
		ps.setString(2, permission.getPermission());

		int result = ps.executeUpdate();

		if (ps != null) {
			ps.close();
		}
		if (con != null) {
			freeConnection(con);
		}
		
		return result == 1;
	}

	@Override
	public boolean removePermission(Permission permission) throws SQLException {

		Connection con = getConnection();
		String query = "DELETE FROM permissions WHERE role_type = ? and permission = ?";
		
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1, permission.getRoleType());
		ps.setString(2, permission.getPermission());
		int deletedRows = ps.executeUpdate();
		
		if (ps != null) {
			ps.close();
		}
		if (con != null) {
			freeConnection(con);
		}
		
		return 0<deletedRows;
	}

}
