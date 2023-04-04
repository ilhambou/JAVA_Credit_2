package org.example.dao;

import org.example.Modele.IB_Client;
import org.example.Modele.IB_Credit;
import org.example.Modele.IB_Utilisateur;

import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.util.Properties;

public class MySqlSessionFactory extends IB_DaoFactory{
    private static MySqlSessionFactory INSTANCE = null;
    private static Connection session = null;
    private static IB_IDao<IB_Client, Long> CLIENT_DAO = null;
    private static IB_IDao<IB_Credit, Long> CREDIT_DAO = null;
    private static IB_IDao<IB_Utilisateur, Long> USER_DAO = null;

    // throws DaoConfigException
    private MySqlSessionFactory() throws Exception{
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            var config = cl.getResourceAsStream("application.properties");
            if(config == null) throw new IOException("fichier introuvable");
            Properties propertiesFile =  new Properties();
            propertiesFile.load(config);
            var url = propertiesFile.getProperty("URL");
            var lg = propertiesFile.getProperty("USERNAME");
            var pass = propertiesFile.getProperty("PASSWORD");
            session = DriverManager.getConnection(url,lg,pass);
            System.out.println("cnx avec success");

            var creditDao = propertiesFile.getProperty("CREDITDAO");
            var clientDao = propertiesFile.getProperty("CLIENTDAO");
            var utilisateurDao = propertiesFile.getProperty("USERDAO");

            Class cCreditDao = Class.forName(creditDao);
            Class cClientDao = Class.forName(clientDao);
            Class cUserDao = Class.forName(utilisateurDao);

            CREDIT_DAO = (IB_IDao<IB_Credit, Long>) cCreditDao.getDeclaredConstructor().newInstance();
            CLIENT_DAO = (IB_IDao<IB_Client, Long>) cClientDao.getDeclaredConstructor().newInstance();
            USER_DAO = (IB_IDao<IB_Utilisateur, Long>) cUserDao.getDeclaredConstructor().newInstance();

            Method
                    setFactory = cCreditDao.getMethod("serFactory", IB_DaoFactory.class);
                    setFactory.invoke(CREDIT_DAO, this);
                    setFactory = cClientDao.getMethod("setFactory", IB_DaoFactory.class);
                    setFactory.invoke(CLIENT_DAO,this);
                    setFactory = cUserDao.getMethod("setFactory",IB_DaoFactory.class);
                    setFactory.invoke(USER_DAO,this);



        }catch (Exception e)
        {
            //throw new DaoConfigException(e.getMessage());
            throw new Exception(e.getMessage());
        }

    }



    @Override
    public IB_IDao<IB_Utilisateur, Long> getUtilisateurDao() {
        return null;
    }

    @Override
    public IB_IDao<IB_Client, Long> getClientDao() {
        return null;
    }

    @Override
    public IB_IDao<IB_Credit, Long> getCreditDao() {
        return null;
    }
}
