
package connect;

import java.sql.Connection;
import java.sql.DriverManager;

public class Local {

    public static Connection getConnection()
    {
        Connection connection=null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/local","local","local");
        }catch(Exception e)
        {
            System.out.println(e);
        }
        return connection;
    }
}
