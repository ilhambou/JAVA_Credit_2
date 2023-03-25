package org.example.Metier;

import org.example.Modele.IB_Credit;

public interface IB_IMetier {
    IB_Credit calcule_Mensualite(Long idCredit) throws Exception;

}
