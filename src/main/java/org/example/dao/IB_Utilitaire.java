package org.example.dao;

import java.awt.event.ComponentListener;
import java.sql.*;

public class IB_Utilitaire {
    public static PreparedStatement initPS(Connection CNX , String SQL , boolean generateKey, Object... Colums)throws SQLException
    {
        PreparedStatement PS = null;
        PS = CNX.prepareStatement(SQL, generateKey ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        for (int i=0;i< Colums.length; i++)
        {
            PS.setObject(i+1, Colums[i]);

        }
        return PS;
    }

    public static void close (PreparedStatement ps )
    {
        if(ps!=null)
        {
            try {
                ps.close();
                System.out.println("fermeture avec succes");
            }catch (SQLException e)
            {
                System.err.printf("fermeture echoué");
            }
        }
    }

    public static void close (ResultSet rs)
    {
        if (rs!=null)
        {
            try {
                rs.close();
                System.out.println("fermeture avec succes");
            }catch (SQLException e)
            {
                System.err.println("fermeture echoué");
            }
        }
    }
    public static void close(PreparedStatement ps , ResultSet rs)
    {
        close(ps);
        close(rs);
    }
}
