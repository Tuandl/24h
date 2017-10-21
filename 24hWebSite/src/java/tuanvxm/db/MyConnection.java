/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tuanvxm.db;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author fightback
 */
public class MyConnection {
    public static Connection getConnection() {
        Connection res = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            res = DriverManager.getConnection("jdbc:sqlserver://localhost:1433;"
                    + "instanceName=MSSQLSERVER01;"
                    + "database = Web24H;"
                    + "user = sa;"
                    + "password = 123");
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return res;
    }
}