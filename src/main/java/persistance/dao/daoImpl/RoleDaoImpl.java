package persistance.dao.daoImpl;

import exception.ApplicationBaseException;
import model.users.Role;
import persistance.dao.AbstractJdbcDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDaoImpl extends AbstractJdbcDao {


    public void create(long userId, Role role) {

        long roleId = getIdByRole(role);
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("insert into USERS_ROLES (USER_ID, ROLE_ID) " +
                     "VALUES ( ?, ? )")){
            prStmt.setLong(1, userId);
            prStmt.setLong(2, roleId);

            prStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
    }

    public Long getIdByRole(Role role) {
        long roleId = 0;
        try (Connection con = getConnector().getConnection();
            PreparedStatement prStmt = con.prepareStatement("select ID from ROLES where ROLE = ?")){
            prStmt.setString(1, role.toString());
            try(ResultSet rs = prStmt.executeQuery()) {
                if (rs.next()) {
                    roleId = rs.getLong("ID");
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getIdByRole entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
        return roleId;
    }

    public Role getRoleByUserId(long id) {
        Role role = null;
        try (Connection con = getConnector().getConnection();
             PreparedStatement prStmt = con.prepareStatement("select r.ROLE " +
                     "from ROLES r, USERS_ROLES ur where ur.USER_ID = ? and ur.ROLE_ID = r.ID")){
            prStmt.setLong(1, id);
            try(ResultSet rs = prStmt.executeQuery()) {
                if (rs.next()) {
                    String result = rs.getString("role");
                    for (Role r : Role.values()) {
                        if (result.toUpperCase().equals(String.valueOf(r))) {
                            role = r;
                            break;
                        }
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new ApplicationBaseException("Error process getIdByRole entity method: " + e.getMessage());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ApplicationBaseException("Error receive database connection: " + e.getMessage());
        }
        return role;
    }
}