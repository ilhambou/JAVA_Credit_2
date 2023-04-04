package org.example.Modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor

public class IB_Client extends IB_Utilisateur {
    private String email, cin, tel , adresse ;
    private IB_Sexe sexe;

    public void setNomComplet(String nomdemandeur, String prenomdemandeur) {
        this.nom = nomdemandeur;
        this.prenom=prenomdemandeur;
    }
}
