/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import tuanvxm.DTOs.ArticleDTO;
import tuanvxm.DTOs.CommentDTO;
import tuanvxm.db.MyConnection;

/**
 *
 * @author fightback
 */
public class ReportDAO {
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
    
    
    /*
    Save new comment to database using attribute of 'dto'.
        This function is called when User comment on any Article.
    @Param CommentDTO dto: ArticleID, CreatorID, CreatedTime, Content, Status.
    @Return boolean:
        - True if save successfully.
        - False if opposite.
    */
    public List<ArticleDTO> findTopViewCountArticle(int Top, Timestamp startTime, Timestamp endTime) {
        List<ArticleDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.FindTopViewCountArticle "
                    + "@Top = " + Top + ", @StartTime = ?, @EndTime = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            
            stm.setTimestamp(1, startTime);
            stm.setTimestamp(2, endTime);
            
            rs = stm.executeQuery();
            
            while (rs.next()) {
                ArticleDTO dto = new ArticleDTO();
                dto.setArticleID(rs.getInt("ArticleID"));
                dto.setTitle(rs.getString("Title"));
                dto.setHeadline(rs.getString("Headline"));
                dto.setContent(rs.getString("Content"));
                dto.setThumbnail(rs.getString("Thumbnail"));
                dto.setCategoryID(rs.getInt("CategoryID"));
                dto.setCreatorID(rs.getInt("CreatorID"));
                dto.setCreatedTime(rs.getTimestamp("CreatedTime"));
                dto.setLastModifierID(rs.getInt("LastModifierID"));
                dto.setLastModifiedTime(rs.getTimestamp("LastModifiedTime"));
                dto.setStatus(rs.getString("Status"));
                dto.setLastStatusChangerID(rs.getInt("LastStatusChangerID"));
                dto.setLastStatusChangedTime(rs.getTimestamp("LastStatusChangedTime"));
                dto.setViewCount(rs.getInt("ViewCount"));
                res.add(dto);                
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    /*
    Save new comment to database using attribute of 'dto'.
        This function is called when User comment on any Article.
    @Param CommentDTO dto: ArticleID, CreatorID, CreatedTime, Content, Status.
    @Return boolean:
        - True if save successfully.
        - False if opposite.
    */
    public List reportArticleByCreatorID(int creatorID) {
        List res = new ArrayList();
        try {
            String sql = "EXEC dbo.ReportArticleByCreatorID @CreatorID = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, creatorID);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                res.add(rs.getInt("AcceptedArticles"));
                res.add(rs.getInt("TotalArticles"));
            }            
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
}