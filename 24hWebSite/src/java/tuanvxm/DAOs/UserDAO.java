/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import tuanvxm.DTOs.UserDTO;
import tuanvxm.db.MyConnection;

/**
 *
 * @author fightback
 */
public class UserDAO {
    Connection cnn;
    PreparedStatement stm;
    ResultSet rs;
    
    private void closeConnection() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean createUser(UserDTO dto) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureInsertUser \n" +
                    "@Username = ?,\n" +
                    "@Password = ?, \n" +
                    "@Name = ?,\n" +
                    "@Birthday = ?,\n" +
                    "@Gender = ?,\n" +
                    "@Address = ?,\n" +
                    "@Phone = ?,\n" +
                    "@Email = ?,\n" +
                    "@PeopleIndentityCard = ?,\n" +
                    "@PressCard = ?,\n" +
                    "@RoleID = ?,\n" +
                    "@Status = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setString(1, dto.getUsername());
            stm.setString(2, dto.getPassword());
            stm.setString(3, dto.getName());
            stm.setTimestamp(4, dto.getBirthday());
            stm.setInt(5, dto.getGender());
            stm.setString(6, dto.getAddress());
            stm.setString(7, dto.getPhone());
            stm.setString(8, dto.getEmail());
            stm.setString(9, dto.getPeopleIndentityCard());
            stm.setString(10, dto.getPressCard());
            stm.setInt(11, dto.getRoleID());
            stm.setString(12, dto.getStatus());
            
            res = stm.executeUpdate() > 0;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public UserDTO checkLogin(String username, String password) {
        UserDTO res = null;
        try {
            String sql = "EXEC dbo.ProcedureLoadLoginInformation @Username = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setString(1, username);
            rs = stm.executeQuery();
            
            if (rs.next()) {
                if (rs.getString("Password").equals(password)) {
                    int userID = rs.getInt("UserID");
                    String userName = rs.getString("Username");
                    String name = rs.getString("Name");
                    int roleID = rs.getInt("RoleID");
                    String status = rs.getString("Status");
                    res = new UserDTO(userID, userName, name, roleID, status);
                } 
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public boolean updateUser(UserDTO dto) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureUpdatetUser \n" +
                    "@UserID = ?,\n" +
                    "@Name = ?,\n" +
                    "@Birthday = ?,\n" +
                    "@Gender = ?,\n" +
                    "@Address = ?,\n" +
                    "@Phone = ?,\n" +
                    "@Email = ?,\n" +
                    "@PeopleIndentityCard = ?,\n" +
                    "@PressCard = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, dto.getUserID());
            stm.setString(2, dto.getName());
            stm.setTimestamp(3, dto.getBirthday());
            stm.setInt(4, dto.getGender());
            stm.setString(5, dto.getAddress());
            stm.setString(6, dto.getPhone());
            stm.setString(7, dto.getEmail());
            stm.setString(8, dto.getPeopleIndentityCard());
            stm.setString(9, dto.getPressCard());
            
            res = stm.executeUpdate() > 0;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public UserDTO findByUserName(String username) {
        UserDTO res = null;
        try {
            String sql = "EXEC dbo.ProcedureFindUserByUsername @Username = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setString(1, username);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                res = new UserDTO();
                res.setUserID(rs.getInt("UserID"));
                res.setUsername(username);
                res.setName(rs.getString("Name"));
                res.setBirthday(rs.getTimestamp("Birthday"));
                res.setGender(rs.getInt("Gender"));
                res.setAddress(rs.getString("Address"));
                res.setPhone(rs.getString("Phone"));
                res.setEmail(rs.getString("Email"));
                res.setPeopleIndentityCard(rs.getString("PeopleIndentityCard"));
                res.setPressCard(rs.getString("PressCard"));
                res.setRoleID(rs.getInt("RoleID"));
                res.setStatus(rs.getString("Status"));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public List<UserDTO> findByRoleID(int RoleID) {
        List<UserDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindUserByRoleID @RoleID = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, RoleID);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                UserDTO dto = new UserDTO();
                dto.setUserID(rs.getInt("UserID"));
                dto.setUsername(rs.getString("Username"));
                dto.setName(rs.getString("Name"));
                dto.setBirthday(rs.getTimestamp("Birthday"));
                dto.setGender(rs.getInt("Gender"));
                dto.setAddress(rs.getString("Address"));
                dto.setPhone(rs.getString("Phone"));
                dto.setEmail(rs.getString("Email"));
                dto.setPeopleIndentityCard(rs.getString("PeopleIndentityCard"));
                dto.setPressCard(rs.getString("PressCard"));
                dto.setRoleID(rs.getInt("RoleID"));
                dto.setStatus(rs.getString("Status"));
                res.add(dto);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public boolean changeStatus(int userID, String newStatus) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureUpdateUserStatus @UserID = ?, @Status = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setString(2, newStatus);
            res = stm.executeUpdate() > 0;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public boolean changePassword(int userID, String oldPassword, String newPassword) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureUpdatePassword @UserID = ?, "
                    + "@OldPassword = ?, @NewPassword = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setString(2, oldPassword);
            stm.setString(3, newPassword);
            res = stm.executeUpdate() > 0;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public boolean changeRoleID(int userID, int newRoleID, String newPressCard) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureUpdateRoleID @UserID = ?, @RoleID = ?, @PressCard = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, userID);
            stm.setInt(2, newRoleID);
            stm.setString(3, newPressCard);
            res = stm.executeUpdate() > 0;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
     public List<UserDTO> findLikeName(String name) {
        List<UserDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindUserLikeName @Name = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setString(1, name);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                UserDTO dto = new UserDTO();
                dto.setUserID(rs.getInt("UserID"));
                dto.setUsername(rs.getString("Username"));
                dto.setName(rs.getString("Name"));
                dto.setBirthday(rs.getTimestamp("Birthday"));
                dto.setGender(rs.getInt("Gender"));
                dto.setAddress(rs.getString("Address"));
                dto.setPhone(rs.getString("Phone"));
                dto.setEmail(rs.getString("Email"));
                dto.setPeopleIndentityCard(rs.getString("PeopleIndentityCard"));
                dto.setPressCard(rs.getString("PressCard"));
                dto.setRoleID(rs.getInt("RoleID"));
                dto.setStatus(rs.getString("Status"));
                res.add(dto);
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
}