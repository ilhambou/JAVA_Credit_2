package org.example;

import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Singleton {
    private static Singleton INSTANCE = null;
    private static Connection session = null;
    private Singleton()
    {
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            var config = cl.getResourceAsStream("config.properties");
            if(config == null) throw new IOException("fichier introuvable");
            Properties propertiesFile = new Properties();
            propertiesFile.load(config);
            var url = propertiesFile.getProperty("URL");
            var lg = propertiesFile.getProperty("USERNAME");
            var pass = propertiesFile.getProperty("PASSWORD");
            session = DriverManager.getConnection(url,lg,pass);
            System.out.println("cnx etablit avec succes");


        }catch (IOException e){e.printStackTrace();}
        catch (SQLException e){System.err.println("cnx echoue");}

       /* public static Singleton getINSTANCE()
        {
            if(INSTANCE == null) INSTANCE = new Singleton();
            return INSTANCE;
        }
        public static Connection getSession()
        {
            if(session == null) getINSTANCE();
            return session;
        }
        public static void closeSession()
        {
            if(session != null)
            {
                try {
                    session.close();
                    System.out.println("fermeture ");
                }catch (SQLException e)
                {
                    System.err.println("fermeture echoue");
                }
            }
        }*/

    }
}
