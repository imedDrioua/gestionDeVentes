package Bdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BddConnection
{


    private static final String SCONN = "jdbc:sqlite:magasin";

    public static Connection getConnection()
            throws SQLException
    {
        try
        {
            Class.forName("org.sqlite.JDBC");

            return DriverManager.getConnection(SCONN);
        }
        catch (ClassNotFoundException|SQLException ex)
        {
            ex.printStackTrace();
        }
        return null;
    }
}