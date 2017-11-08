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
import tuanvxm.db.MyConnection;

/**
 *
 * @author fightback
 */
public class ArticleDAO {

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
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean createArticle(ArticleDTO dto) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureCreateArticle\n"
                    + "	@Title = ?,\n"
                    + "	@Headline = ?,\n"
                    + "	@Content = ?,\n"
                    + "	@Thumbnail = ?,\n"
                    + "	@CategoryID = ?,\n"
                    + "	@CreatorID = ?,\n"
                    + "	@CreatedTime = ?,\n"
                    + "	@Status = ?,\n"
                    + "	@ViewCount = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setString(1, dto.getTitle());
            stm.setString(2, dto.getHeadline());
            stm.setString(3, dto.getContent());
            stm.setString(4, dto.getThumbnail());
            stm.setInt(5, dto.getCategoryID());
            stm.setInt(6, dto.getCreatorID());
            stm.setTimestamp(7, dto.getCreatedTime());
            stm.setString(8, dto.getStatus());
            stm.setInt(9, dto.getViewCount());

            res = stm.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }

    public boolean updateArticle(ArticleDTO dto) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureUpdateArticle\n"
                    + "	@ArticleID = ?,\n"
                    + "	@Title = ?,\n"
                    + "	@Headline = ?,\n"
                    + "	@Content = ?,\n"
                    + "	@Thumbnail = ?,\n"
                    + "	@CategoryID = ?,\n"
                    + "	@LastModifierID = ?,\n"
                    + "	@LastModifiedTime = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, dto.getArticleID());
            stm.setString(2, dto.getTitle());
            stm.setString(3, dto.getHeadline());
            stm.setString(4, dto.getContent());
            stm.setString(5, dto.getThumbnail());
            stm.setInt(6, dto.getCategoryID());
            stm.setInt(7, dto.getLastModifierID());
            stm.setTimestamp(8, dto.getLastModifiedTime());

            res = stm.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }

    public boolean changeStatus(ArticleDTO dto) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureUpdateArticleStatus \n"
                    + "	@ArticleID = ?,\n"
                    + "	@Status = ?,\n"
                    + "	@LastStatusChangerID = ?,\n"
                    + "	@LastStatusChangedTime = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);

            stm.setInt(1, dto.getArticleID());
            stm.setString(2, dto.getStatus());
            stm.setInt(3, dto.getLastStatusChangerID());
            stm.setTimestamp(4, dto.getLastStatusChangedTime());

            res = stm.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }

    public boolean increaseViewCount(int articleID) {
        boolean res = false;
        try {
            String sql = "EXEC dbo.ProcedureIncreaseViewCount @ArticleID = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);

            stm.setInt(1, articleID);

            res = stm.executeUpdate() > 0;
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }

    public List<ArticleDTO> findByTitle(String title) {
        List<ArticleDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindArticleByTitle @Title = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setString(1, title);

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
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public List<ArticleDTO> findByCreatorID(int creatorID) {
        List<ArticleDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindArticleByCreatorID @CreatorID = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, creatorID);
            
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
    
    public List<ArticleDTO> findByStatus(String status) {
        List<ArticleDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindArticleByStatus @Status = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setString(1, status);
            
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
    
    public List<ArticleDTO> findByTitleAndStatus(String title, String status) {
        List<ArticleDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindArticleByTitleAndStatus @Title = ?, @Status = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setString(1, title);
            stm.setString(2, status);
            
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
    
    public List<ArticleDTO> findByCategoryIDAndStatus(int categoryID, String status) {
        List<ArticleDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindArticleByCategoryIDAndStatus @CategoryID = ?, @Status = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, categoryID);
            stm.setString(2, status);
            
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
    
    public List<ArticleDTO> findTopViewCountCreatedAfterTime(int top, Timestamp time) {
        List<ArticleDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.FindTopViewCountCreatedAfterDate @Top = ?, @Time = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, top);
            stm.setTimestamp(2, time);
            
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
    
    public ArticleDTO findByArticleID(int articleID) {
        ArticleDTO res = new ArticleDTO();
        try {
            String sql = "SELECT \n" +
"		ArticleID,\n" +
"		Title,\n" +
"		Headline,\n" +
"		Content,\n" +
"		Thumbnail,\n" +
"		CategoryID,\n" +
"		CreatorID,\n" +
"		CreatedTime,\n" +
"		LastModifierID,\n" +
"		LastModifiedTime,\n" +
"		[Status],\n" +
"		LastStatusChangerID,\n" +
"		LastStatusChangedTime,\n" +
"		ViewCount\n" +
"	FROM Article\n" +
"	WHERE ArticleID = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            stm.setInt(1, articleID);
            
            rs = stm.executeQuery();
            
            if (rs.next()) {
                res.setArticleID(rs.getInt("ArticleID"));
                res.setTitle(rs.getString("Title"));
                res.setHeadline(rs.getString("Headline"));
                res.setContent(rs.getString("Content"));
                res.setThumbnail(rs.getString("Thumbnail"));
                res.setCategoryID(rs.getInt("CategoryID"));
                res.setCreatorID(rs.getInt("CreatorID"));
                res.setCreatedTime(rs.getTimestamp("CreatedTime"));
                res.setLastModifierID(rs.getInt("LastModifierID"));
                res.setLastModifiedTime(rs.getTimestamp("LastModifiedTime"));
                res.setStatus(rs.getString("Status"));
                res.setLastStatusChangerID(rs.getInt("LastStatusChangerID"));
                res.setLastStatusChangedTime(rs.getTimestamp("LastStatusChangedTime"));
                res.setViewCount(rs.getInt("ViewCount"));
            }
        } catch(Exception ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return res;
    }
    
    /*
    Find articles which be created by User with UserID equal to 'creatorID' and CategoryID = 'categoryID'
    This function is called when Journalist access Journalist Page to load articles created by himself.
    @Param int creatorID.
    @Return List<ArticleDTO>: List of articles sastify searching condition.
    */
    public List<ArticleDTO> findByCreatorIDAndCategoryID(int creatorID, int categoryID) {
        List<ArticleDTO> res = new ArrayList<>();
        try {
            String sql = "EXEC dbo.ProcedureFindArticleByCategoryIDAndCreatorID @CategoryID = ?, @CreatorID = ?";
            cnn = MyConnection.getConnection();
            stm = cnn.prepareStatement(sql);
            
            stm.setInt(1, categoryID);
            stm.setInt(2, creatorID);
            
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
}
