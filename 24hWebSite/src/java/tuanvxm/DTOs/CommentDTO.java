/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.DTOs;

import java.sql.Timestamp;
import java.util.List;
import tuanvxm.DAOs.CommentDAO;

/**
 *
 * @author fightback
 */
public class CommentDTO {
    
    public static final String STATUS_AVAILABLE = "Available"; //Default status
    public static final String STATUS_HIDE_BY_READER = "HideByReader";
    public static final String STATUS_HIDE_BY_EDITOR = "HideByEditor";
    
    private String content, status,creator;
    private int commentID, articleID, creatorID, LastStatusChangerID;
    private Timestamp createdTime, lastStatusChangedTime;

    public CommentDTO() {
    }
    
    /*
    This constructor is designed for loading data from database
    */
    public CommentDTO(int commentID, int articleID, int creatorID, Timestamp createdTime, String content, String status, int LastStatusChangerID, Timestamp lastStatusChangedTime) {
        this.content = content;
        this.status = status;
        this.commentID = commentID;
        this.articleID = articleID;
        this.creatorID = creatorID;
        this.LastStatusChangerID = LastStatusChangerID;
        this.createdTime = createdTime;
        this.lastStatusChangedTime = lastStatusChangedTime;
    }
    
    /*
    This costructor is designed for creating new comment
    */
    public CommentDTO(int articleID, int creatorID, Timestamp createdTime, String content, String status) {
        this.content = content;
        this.status = status;
        this.articleID = articleID;
        this.creatorID = creatorID;
        this.createdTime = createdTime;
    }

    public CommentDTO(int commentID, String status, int LastStatusChangerID, Timestamp lastStatusChangedTime) {
        this.status = status;
        this.commentID = commentID;
        this.LastStatusChangerID = LastStatusChangerID;
        this.lastStatusChangedTime = lastStatusChangedTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
    
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getCommentID() {
        return commentID;
    }

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }

    public int getArticleID() {
        return articleID;
    }

    public void setArticleID(int articleID) {
        this.articleID = articleID;
    }

    public int getCreatorID() {
        return creatorID;
    }

    public void setCreatorID(int creatorID) {
        this.creatorID = creatorID;
    }

    public int getLastStatusChangerID() {
        return LastStatusChangerID;
    }

    public void setLastStatusChangerID(int LastStatusChangerID) {
        this.LastStatusChangerID = LastStatusChangerID;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    public Timestamp getLastStatusChangedTime() {
        return lastStatusChangedTime;
    }

    public void setLastStatusChangedTime(Timestamp lastStatusChangedTime) {
        this.lastStatusChangedTime = lastStatusChangedTime;
    }
    
    /*
    Save this DTO to database as a new comment
    @return true if save successful
    @return false if save fail
    */
    public boolean createComment() {
        return new CommentDAO().createComment(this);
    }
    
    /*
    Change status of comment with commentID to newStatus
    int commentID, String newStatus, changerID, changedTime must be provide before call this function
    @return true if success
    @return false if fail
    */
    public boolean changeStatus() {
        return new CommentDAO().changeStatus(this);
    }
    
    /*
    Search all comment in article with articleID from database
    @param int articleID
    @return list of comment sastify search condition
    @return empty list if there isn't any comment sastify
    */
    public List<CommentDTO> findByArticleID(int articleID) {
        return new CommentDAO().findByArticleID(articleID);
    }
}