/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.DTOs;

import java.sql.Timestamp;
import java.util.List;
import tuanvxm.DAOs.ArticleDAO;

/**
 *
 * @author fightback
 */
public class ArticleDTO {
    
    public static final String STATUS_NEW= "New"; //Default status
    public static final String STATUS_AVAILABLE = "Available";
    public static final String STATUS_HIDED = "Hided";
    
    private String title, headline, content, thumbnail, status,creator;
    private int articleID, categoryID, creatorID, lastModifierID, lastStatusChangerID, viewCount; 
    private Timestamp createdTime, lastModifiedTime, lastStatusChangedTime;

    public ArticleDTO() {
    }

    /*
    This constructor is designed for loading article from database
    */
    public ArticleDTO(int articleID, String title, String headline, String content, String thumbnail, int categoryID, int creatorID, Timestamp createdTime, int lastModifierID, Timestamp lastModifiedTime, String status, int lastStatusChangerID, Timestamp lastStatusChangedTime, int viewCount) {
        this.title = title;
        this.headline = headline;
        this.content = content;
        this.thumbnail = thumbnail;
        this.status = status;
        this.articleID = articleID;
        this.categoryID = categoryID;
        this.creatorID = creatorID;
        this.lastModifierID = lastModifierID;
        this.lastStatusChangerID = lastStatusChangerID;
        this.viewCount = viewCount;
        this.createdTime = createdTime;
        this.lastModifiedTime = lastModifiedTime;
        this.lastStatusChangedTime = lastStatusChangedTime;
    }
    
    /*
    This constructor is designed for creating new article
    */
    public ArticleDTO(String title, String headline, String content, String thumbnail, int categoryID, int creatorID, Timestamp createdTime, String status) {
        this.title = title;
        this.headline = headline;
        this.content = content;
        this.thumbnail = thumbnail;
        this.status = status;
        this.categoryID = categoryID;
        this.creatorID = creatorID;
        this.createdTime = createdTime;
        this.viewCount = 0;
    }

    /*
    This constructor for change status
    */
    public ArticleDTO(int articleID, String status, int lastStatusChangerID, Timestamp lastStatusChangedTime) {
        this.status = status;
        this.articleID = articleID;
        this.lastStatusChangerID = lastStatusChangerID;
        this.lastStatusChangedTime = lastStatusChangedTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public int getLastModifierID() {
        return lastModifierID;
    }

    public void setLastModifierID(int lastModifierID) {
        this.lastModifierID = lastModifierID;
    }

    public int getLastStatusChangerID() {
        return lastStatusChangerID;
    }

    public void setLastStatusChangerID(int lastStatusChangerID) {
        this.lastStatusChangerID = lastStatusChangerID;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Timestamp lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }

    public Timestamp getLastStatusChangedTime() {
        return lastStatusChangedTime;
    }

    public void setLastStatusChangedTime(Timestamp lastStatusChangedTime) {
        this.lastStatusChangedTime = lastStatusChangedTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    
    /*
    Save this DTO to database as a new article
    Must provide CreatorID and CreatedTime
    @return true if save successful
    @return false if save fail
    */
    public boolean createArticle() {
        return new ArticleDAO().createArticle(this);
    }
    
    /*
    Update this article to database using articleID as searching key
    Must provide ModifierID and ModifiedTime
    articleID, creatorID, createdTime, status and viewCount will not be changed
    @return true if update successful
    @return false if update fail
    */
    public boolean updateArticle() {
        return new ArticleDAO().updateArticle(this);
    }
    
    /*
    Change status of article with articleID to newStatus
    provide articleID, newStatus, Changer, ChangedTime before call this function
    @param this
    @return true if success
    @return false if fail
    */
    public boolean changeStatus() {
        return new ArticleDAO().changeStatus(this);
    }
    
    /*
    Increase ViewCount of article by 1
    Searching article by ArticleID
    @return true if successful
    @return false if not success
    */
    public boolean increaseViewCount() {
        return new ArticleDAO().increaseViewCount(articleID);
    }
    
    /*
    Find and return list of articles which title contain searching title
    @param String title
    @return empty list if there isn't any article matching search condition
    */
    public List<ArticleDTO> findByTitle(String title) {
        return new ArticleDAO().findByTitle(title);
    }
    
    /*
    Find and return list of articles which creatorID is the same with searching creatorID
    @param int creatorID
    @return empty list if there isn't any article matching search condition
    */
    public List<ArticleDTO> findByCreatorID(int creatorID) {
        return new ArticleDAO().findByCreatorID(creatorID);
    }
    
    /*
    Find and return list of articles which status is the same searching status
    @param String status
    @return empty list if there isn't any article matching search condition
    */
    public List<ArticleDTO> findByStatus(String status) {
        return new ArticleDAO().findByStatus(status);
    }
    
    /*
    Find and return list of articles which title contain searching title and status is the same with searching status
    @param String title, String status
    @return empty list if there isn't any article matching search condition
    */
    public List<ArticleDTO> findByTitleAndStatus(String title, String status) {
        return new ArticleDAO().findByTitleAndStatus(title, status);
    }
    
    /*
    Find and return list of articles which categoryID is the same searching categoryID 
            and status is the same with searching status
    @param String categoryID
    @param status
    @return empty list if there isn't any article matching search condition
    */
    public List<ArticleDTO> findByCategoryIDAndStatus(int categoryID, String status) {
        return new ArticleDAO().findByCategoryIDAndStatus(categoryID, status);
    }
    
    /*
    Return 'top' article with created after 'time' ordered desc by viewcount
    */
    public List<ArticleDTO> findTopViewCountCreatedAfterTime(int top, Timestamp time) {
        return new ArticleDAO().findTopViewCountCreatedAfterTime(top, time);
    }
}