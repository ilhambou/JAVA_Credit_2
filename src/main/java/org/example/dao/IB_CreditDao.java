package org.example.dao;

import org.example.Modele.IB_Credit;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
@Component("dao")


public class IB_CreditDao implements IB_IDao<IB_Credit,Long>{


    public static Set<IB_Credit> IB_BDCredits()
    {
        return new HashSet<IB_Credit>(
                Arrays.asList(
                        new IB_Credit(1L,40000.0,120,2.5,"ilham",0.0),
                        new IB_Credit(2L,860000.0,240,2.5,"ilham2",0.0),

                        new IB_Credit(2L,860000.0,240,2.5,"bouich",0.0)


                )
        );


    }




    public IB_Credit trouverParId(Long id)
    {
        System.out.println("touver le id num : " + id);
        return IB_BDCredits().stream().filter(credit -> credit.getIB_id() == id).findFirst().orElse(null);

    }

    /// ??????????????
    /*public static Set<IB_Credit> BDCredits()
    {
        return null;
    }*/
/*
    @Override
    public Object trouverParId(Object o) {
        return null;
    }*/


}
