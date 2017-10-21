/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.other;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author fightback
 */
public class GenderList {
    public static final List<String> GENDER_LIST = Arrays.asList("Khác", "Nam", "Nữ"); //List of gender
    
    /*
    Convert gender from String type to int type
    @param string gender
    @return int gender
    */
    public static int toInt(String gender) {
        return GENDER_LIST.indexOf(gender);
    }
    
    /*
    Convert gender from int type to String type
    @param int gender
    @return String gender
    */
    public static String toStr(int gender) {
        return GENDER_LIST.get(gender);
    }
}