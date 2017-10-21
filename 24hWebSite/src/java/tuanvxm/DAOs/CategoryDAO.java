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
import tuanvxm.other.Category;

/**
 *
 * @author fightback
 */
public class CategoryDAO {
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
    
    public List<Category> loadCategoryList() {
        List<Category> res = new ArrayList<>();
        try {
            String sql = "SELECT [CategoryID], [Name] FROM [Category]";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            rs = stm.executeQuery();
            while (rs.next()) {
                res.add(new Category(rs.getInt("CategoryID"), rs.getString("Name")));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
}