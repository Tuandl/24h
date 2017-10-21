/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.other;

import java.util.List;
import tuanvxm.DAOs.CategoryDAO;

/**
 *
 * @author fightback
 */
public class CategoryList {
    public static final List<Category> CATEGORY_LIST = load();
    
    /*
    Load category list from database
    */
    private static List<Category> load() {
        return new CategoryDAO().loadCategoryList();
    }
    
    /*
    Find category by name and return categoryID
    @param String category Nname
    @return categoryID
    @return -1 if not found
    */
    public static int getID(String categoryName) {
        for (Category r : CATEGORY_LIST) {
            if (r.getName().compareTo(categoryName) == 0) 
                return r.getCategoryID();
        }
        return -1;
    }
    
    /*
    Find category by categoryID and return name
    @param String reoleID
    @return category's name
    @return null if not found
    */
    public static String getName(int categoryID) {
        for (Category r : CATEGORY_LIST) {
            if (r.getCategoryID()== categoryID)
                return r.getName();
        }
        return null;
    }
}