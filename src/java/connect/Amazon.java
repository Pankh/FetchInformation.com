/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package connect;

import java.sql.Connection;
import java.sql.DriverManager;
public class Amazon {
public static Connection getConnection()
{
    Connection connection=null;
    try{
        Class.forName("com.mysql.jdbc.Driver");
        connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/amazon","amazon","amazon");
    }catch(Exception e)
    {
        System.out.println(e);
    }
    return connection;
}
}
