/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.other;

import java.util.Calendar;
import java.util.Date;

public class test {
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.DAY_OF_MONTH, -23);
        System.out.println(""+calendar.getTime());
    }
}
