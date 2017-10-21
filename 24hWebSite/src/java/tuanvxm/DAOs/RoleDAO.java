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
import tuanvxm.db.MyConnection;
import tuanvxm.other.Role;

/**
 *
 * @author fightback
 */
public class RoleDAO {
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
    
    public List<Role> loadRoleList() {
        List<Role> res = new ArrayList<>();
        try {
            String sql = "SELECT [RoleID], [Name] FROM [Role]";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                res.add(new Role(rs.getInt("RoleID"), rs.getString("Name")));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
}