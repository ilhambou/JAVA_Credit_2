package org.example.dao;

import org.example.Modele.IB_Client;
import org.example.Modele.IB_Credit;
import org.example.Modele.IB_Utilisateur;

public abstract class IB_DaoFactory {
    public static final int MYSQL_DATA_UNIT = 1,
                            File_DATA_UNIT = 2,
                            INMEMORY_DATA_UNIT = 3;
    public abstract IB_IDao<IB_Utilisateur, Long> getUtilisateurDao();
    public abstract IB_IDao<IB_Client, Long> getClientDao();
    public abstract IB_IDao<IB_Credit, Long> getCreditDao();
    public static final IB_DaoFactory getDaoFactory(int factoryType)
    {
        switch (factoryType)
        {
            //case 1: return MySqlSessionFactory.getINstance();
            //case 2: return FileFactory.getInstance();
            //case 3: return MemFactory.getInstance();
            default: return null;
        }
    }


}
