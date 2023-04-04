package org.example.Modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class IB_Utilisateur {
    protected Long id ;
    protected String login , motDePass, nom, prenom;
    protected IB_Role role ;


    public String nomVComplet()
    {
        return prenom + " " + nom;
    }
}
