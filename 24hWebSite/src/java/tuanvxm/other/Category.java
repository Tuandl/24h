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
public class Category {
    private int CategoryID;
    private String Name;

    public Category(int CategoryID, String Name) {
        this.CategoryID = CategoryID;
        this.Name = Name;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }
}