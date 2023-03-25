package org.example.presentation;

import org.example.Metier.IB_IMetier;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;

@Controller
public class IB_CreditControleur implements IB_IControleur{
    @Autowired
    @Qualifier("metier")

   //IB_CreditMetier creditMetier;
    IB_IMetier creditMetier;


    public void setCreditMetier(IB_IMetier creditMetie) {
        this.creditMetier = creditMetie;
    }


    @Override
    public void afficher_Mensualite(Long idCredit) throws Exception {
       // IB_Credit creditAvecMensualite = creditMetier.calculer_mensualite(idCredit);
        var creditAvecMensualite = creditMetier.calcule_Mensualite(idCredit);
        System.out.println(creditAvecMensualite);
    }
}
