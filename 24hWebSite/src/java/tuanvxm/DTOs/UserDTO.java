/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuanvxm.DTOs;

import java.sql.Timestamp;
import java.util.List;
import tuanvxm.DAOs.UserDAO;

/**
 *
 * @author fightback
 */
public class UserDTO {

    public static final String STATUS_AVAILABLE = "Available"; //Default Status
    public static final String STATUS_COMMENTLOCKED = "CommentLocked";
    public static final String STATUS_BANNED = "Banned";

    private String username, password, name, address, phone, email, PeopleIndentityCard, PressCard, status;
    private int userID, gender, roleID;
    private Timestamp birthday;

    public UserDTO() {
    }

    /*
    This constructor is designed for loading user's information from database
     */
    public UserDTO(int userID, String username, String name, Timestamp birthday, int gender, String address, String phone, String email, String PeopleIndentityCard, String PressCard, int roleID, String status) {
        this.username = username;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.PeopleIndentityCard = PeopleIndentityCard;
        this.PressCard = PressCard;
        this.status = status;
        this.userID = userID;
        this.gender = gender;
        this.roleID = roleID;
        this.birthday = birthday;
    }

    /*
    This constructor is designed for creating new user
     */
    public UserDTO(String username, String password, String name, Timestamp birthday, int gender, String address, String phone, String email, String PeopleIndentityCard, int roleID, String status) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.PeopleIndentityCard = PeopleIndentityCard;
        this.status = status;
        this.gender = gender;
        this.roleID = roleID;
        this.birthday = birthday;
    }

    /*
    This constructor is designed for checking log in
     */
    public UserDTO(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /*
    This constructor is used for DAO actions
     */
    public UserDTO(int userID, String username, String name, int roleID, String status) {
        this.username = username;
        this.name = name;
        this.status = status;
        this.userID = userID;
        this.roleID = roleID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPeopleIndentityCard() {
        return PeopleIndentityCard;
    }

    public void setPeopleIndentityCard(String PeopleIndentityCard) {
        this.PeopleIndentityCard = PeopleIndentityCard;
    }

    public String getPressCard() {
        return PressCard;
    }

    public void setPressCard(String PressCard) {
        this.PressCard = PressCard;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }

    public int getRoleID() {
        return roleID;
    }

    public void setRoleID(int roleID) {
        this.roleID = roleID;
    }

    public Timestamp getBirthday() {
        return birthday;
    }

    public void setBirthday(Timestamp birthday) {
        this.birthday = birthday;
    }

    /*
    Save this DTO as new user to database
    @return true if save successfully, opposite return fail
     */
    public boolean createUser() {
        return new UserDAO().createUser(this);
    }

    /*
    Check login using this DTO's username and password
    Load password, name, role, id, status by username
    If password is correct, then update information
    @return true and load user's name, roleID, status and ID attributes automatically if successful
    @return false if opposite
     */
    public boolean login() {
        UserDTO dto = new UserDAO().checkLogin(username, password);
        if (dto == null) {
            return false;
        }
        userID = dto.getUserID();
        password = "";
        name = dto.getName();
        roleID = dto.getRoleID();
        status = dto.getStatus();
        return true;
    }

    /*
    Update user's information using userID as searching key
    Username, password, role and status will not be changed
    @return true if update successfully, opposite return fail
     */
    public boolean updateUser() {
        return new UserDAO().updateUser(this);
    }

    /*
    Search user in database using username
    @param string username
    @return UserDTO if user is exist, opposite return null
     */
    public UserDTO findByUserName(String username) {
        return new UserDAO().findByUserName(username);
    }

    /*
    Search users in database using roleID
    @param int roleID
    @return list of UserDTO if one or more users carry the searching role. opposite return empty list
     */
    public List<UserDTO> findByRoleID(int roleID) {
        return new UserDAO().findByRoleID(roleID);
    }

    /*
    Change status of user with userID to newStatus
    @param int articleID, String newStatus
    @return true if success
    @return false if fail
     */
    public boolean changeStatus(int userID, String newStatus) {
        return new UserDAO().changeStatus(userID, newStatus);
    }

    /*
    Change password of user
    Find user by user id
    Return false if old password or ID isn't correct
     */
    public boolean changePassword(int userID, String oldPassword, String newPassword) {
        return new UserDAO().changePassword(userID, oldPassword, newPassword);
    }

    /*
    Change role of user
    Provide newPressCard if new role is Journalist, otherwise provide null
    Return false if userID isn't correct
     */
    public boolean changeRoleID(int userID, int newRoleID, String newPressCard) {
        return new UserDAO().changeRoleID(userID, newRoleID, newPressCard);
    }
    
    /*
    Search users in database using a part of fullname
    @param String name
    @return list of UserDTO if one or more users carry the searching role. opposite return empty list
     */
    public List<UserDTO> findLikeFullName(String name) {
        return new UserDAO().findLikeName(name);
    }
}
