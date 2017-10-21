/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.other;

import java.util.List;
import tuanvxm.DAOs.RoleDAO;

/**
 *
 * @author fightback
 */
public class RoleList {
    public static final List<Role> ROLE_LIST = load();
    
    /*
    Load role list from database
    */
    private static List<Role> load() {
        return new RoleDAO().loadRoleList();
    }
    
    /*
    Find role by name and return roleID
    @param String roleName
    @return roleID
    @return -1 if not found
    */
    public static int getID(String roleName) {
        for (Role r : ROLE_LIST) {
            if (r.getName().compareTo(roleName) == 0) 
                return r.getRoleID();
        }
        return -1;
    }
    
    /*
    Find role by roleID and return name
    @param String reoleID
    @return role's name
    @return null if not found
    */
    public static String getName(int roleID) {
        for (Role r : ROLE_LIST) {
            if (r.getRoleID() == roleID)
                return r.getName();
        }
        return null;
    }
}