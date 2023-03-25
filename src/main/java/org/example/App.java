package org.example;

import org.example.Modele.IB_Credit;

import java.rmi.server.RemoteRef;
import java.sql.*;
import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/credits";
        var login = "root";
        var pass = "";
        Connection connection=null;
        Statement statement = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList credits = new ArrayList<IB_Credit>();

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


        }
    }
}