package org.example;

import com.mysql.cj.xdevapi.Client;
import org.example.Modele.IB_Client;
import org.example.Modele.IB_Credit;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;


//principe SOLID  ?

public class JDBC_Test {

    public static void main(String[] args) {


        Connection connection = null;
        var credits = new ArrayList<IB_Credit>();
        try {
            ClassLoader cl = Thread.currentThread().getContextClassLoader();
            var config = cl.getResourceAsStream("config.properties");
            if(config == null) throw new IOException("fichier properties introuvable");
            Properties propertiesFile = new Properties();
            propertiesFile.load(config);
            var url = propertiesFile.getProperty("URL");
            var user = propertiesFile.getProperty("USERNAME");
            var pass = propertiesFile.getProperty("PASSWORD");
            connection = DriverManager.getConnection(url,user,pass);
            System.out.println("cnx etablit avec succès");
            var statment = connection.createStatement();
            var rs = statment.executeQuery("SELECT cr.id,cr.capital,cr.nbrMois,cr.taux, cr.demandeur, cr.mensualite,u.nom,u.prenom" + "from credit cr, client cl ,utilisateur u"+"where cr.demandeur = cl.id and" + "cl.id = u.id and" + "cr.capital = '30000'");
            while (rs.next())
            {
                var id = rs.getLong("id");
                var capital = rs.getDouble("capital");
                var nbrMois= rs.getInt("nbrMois");
                var taux = rs.getDouble("taux");
                var nomdemandeur = rs.getString("nom");
                var prenomdemandeur= rs.getString("prenom");
                var mensulite = rs.getDouble("mensualite");
                var client = new IB_Client();
                client.setNomComplet(nomdemandeur,prenomdemandeur);
                credits.add(new IB_Credit(id,capital,nbrMois,taux,client,mensulite));
            }
            if(credits.isEmpty()) throw new SQLException("aucun credit trouvé");
            else credits.forEach(System.out::println);
        }
        catch (IOException e){e.printStackTrace();}
        //Singleton.closeSession()
        catch (SQLException e){System.err.println("cnx echoé");}
        finally {
            if(connection != null)
            {
                try {
                    connection.close();
                    System.out.println("fermeture de session avec succes");
                }catch (SQLException e){
                    System.err.println("fermeture de seession echoué");
                }
            }
        }

        ///---------------------------------------------- tp 2 ------------------------------------------
        /*String url = "jdbc:mysql://localhost:3306/credits";
        var login = "root";
        var pass = "";

        Connection connection=null;
        Statement statement = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

       // ArrayList credits = new ArrayList<IB_Credit>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("le driver de mysql a ete charge avec succes");
            connection = DriverManager.getConnection(url,login,pass);
            System.out.println("cnx etablit avec succe");
            statement = connection.createStatement();
            //statement.executeQuery("SELECT * FROM credits where ");
            //ps = connection.prepareStatement("select * from credit where id = ?");
            //ps.setLong(1,3L);
            ps = connection.prepareStatement("select * from credit");
            rs = ps.executeQuery();
            while (rs.next())
            {
                var id = rs.getLong("id");
                var capitale_emprunte = rs.getDouble("capitale_emprunte");
                var nombre_mois = rs.getInt("nombre_mois");
                var taux_mensuel = rs.getDouble("taux_mensuel");
                var nom_demandeur = rs.getString("nom_demandeur");
                var mensualite = rs.getDouble("mensualite");
               IB_Credit credit = new IB_Credit(id,capitale_emprunte,nombre_mois,taux_mensuel,nom_demandeur,mensualite);
                credits.add(credit);
            }
            credits.forEach(System.out::println);

        } catch (ClassNotFoundException e) {
            System.out.println("Le driver du MySQL est introuvble" );
        } catch (SQLException e) {
            System.out.println("Connexion échouée");
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                    System.out.println("Connexion fermé avec succ ");
                }
            } catch (SQLException e) {
                System.out.println("Connexion n'est pas fermé av succ");

            }
            try {
                if(ps != null)
                {
                    ps.close();
                    System.out.println("Connexion ferme av succ ");
                }
            } catch (SQLException e) {
                System.out.println("Connexion n'a pas pu etre femrme");
            }
            try {
                if(connection != null)
                {
                    connection.close();
                    System.out.println("Connexion ffermeavec succes ");
                }
            } catch (SQLException e) {
                System.out.println("Connexion n'a pas pu etre ferme");
            }


        }*/
        //Connection connection = Singleton.getSession();


    }

}

