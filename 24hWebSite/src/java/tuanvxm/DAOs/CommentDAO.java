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
import tuanvxm.DTOs.CommentDTO;
import tuanvxm.db.MyConnection;

/**
 *
 * @author fightback
 */
public class CommentDAO {
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
    
    public boolean createComment(CommentDTO dto) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureCreateComment @ArticleID = ?, @CreatorID = ?, " +
                    "   @CreatedTime = ?, \n" +
                    "   @Content = ?, @Status = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, dto.getArticleID());
            stm.setInt(2, dto.getCreatorID());
            stm.setTimestamp(3, dto.getCreatedTime());
            stm.setString(4, dto.getContent());
            stm.setString(5, dto.getStatus());
            
            res = stm.executeUpdate() > 0;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public boolean changeStatus(CommentDTO dto) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureChangeCommentStatus @CommentId = ?, @Status = ?, \n" +
                    "   @LastStatusChangerID = ?, @LastStatusChangedTime = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, dto.getCommentID());
            stm.setString(2, dto.getStatus());
            stm.setInt(3, dto.getLastStatusChangerID());
            stm.setTimestamp(4, dto.getLastStatusChangedTime());
            
            res = stm.executeUpdate() > 0;
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public List<CommentDTO> findByArticleID(int articleID) {
        List<CommentDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindCommentByArticleID @ArticleID = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, articleID);
            
            rs = stm.executeQuery();
            while (rs.next()) {
                CommentDTO dto = new CommentDTO();
                dto.setCommentID(rs.getInt("CommentID"));
                dto.setArticleID(rs.getInt("ArticleID"));
                dto.setCreatorID(rs.getInt("CreatorID"));
                dto.setCreatedTime(rs.getTimestamp("CreatedTime"));
                dto.setContent(rs.getString("Content"));
                dto.setStatus(rs.getString("Status"));
                dto.setLastStatusChangerID(rs.getInt("LastStatusChangerID"));
                dto.setLastStatusChangedTime(rs.getTimestamp("LastStatusChangedTime"));
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