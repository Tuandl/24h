/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.other;

/**
 *
 * @author fightback
 */
public class Role {
    private int RoleID;
    private String Name;

    public Role(int RoleID, String Name) {
        this.RoleID = RoleID;
        this.Name = Name;
    }

    public int getRoleID() {
        return RoleID;
    }

    public void setRoleID(int RoleID) {
        this.RoleID = RoleID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}