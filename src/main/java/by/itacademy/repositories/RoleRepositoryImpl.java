//package by.itacademy.repositories;
//
//import by.itacademy.model.users.Role;
//import org.springframework.stereotype.Repository;
//
//import javax.persistence.EntityManagerFactory;
//
//@Repository
//public class RoleRepositoryImpl extends AbstractCrudRepository<Role> {
//
//
//    public RoleRepositoryImpl(EntityManagerFactory entityManagerFactory) {
//        super(entityManagerFactory);
//    }
//
//
//
//    public Long getIdByRole(Role role) {
//        long roleId = 0;
//        try (Connection con = getConnector().getConnection();
//            PreparedStatement prStmt = con.prepareStatement("select ID from ROLES where ROLE = ?")){
//            prStmt.setString(1, role.toString());
//            try(ResultSet rs = prStmt.executeQuery()) {
//                if (rs.next()) {
//                    roleId = rs.getLong("ID");
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new ApplicationBaseException("Error process getIdByRole entity method: " + e.getMessage());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
//        }
//        return roleId;
//    }
//
//    public Role getRoleByUserId(long id) {
//        Role role = null;
//        try (Connection con = getConnector().getConnection();
//             PreparedStatement prStmt = con.prepareStatement("select r.ROLE " +
//                     "from ROLES r, USERS_ROLES ur where ur.USER_ID = ? and ur.ROLE_ID = r.ID")){
//            prStmt.setLong(1, id);
//            try(ResultSet rs = prStmt.executeQuery()) {
//                if (rs.next()) {
//                    String result = rs.getString("role");
//                    for (Role r : Role.values()) {
//                        if (result.toUpperCase().equals(String.valueOf(r))) {
//                            role = r;
//                            break;
//                        }
//                    }
//                }
//
//            } catch (SQLException e) {
//                e.printStackTrace();
//                throw new ApplicationBaseException("Error process getIdByRole entity method: " + e.getMessage());
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            throw new ApplicationBaseException("Error receive database by.itacademy.connection: " + e.getMessage());
//        }
//        return role;
//    }
//}