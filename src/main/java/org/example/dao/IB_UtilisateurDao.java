package org.example.dao;

import org.example.Modele.IB_Utilisateur;

import java.util.List;

public class IB_UtilisateurDao implements IB_IDao<IB_Utilisateur, Long>{
    @Override
    public IB_Utilisateur trouverParId(Long aLong) {
        return null;
    }

    @Override
    public List<IB_Utilisateur> findall() {
        return null;
    }

    @Override
    public IB_Utilisateur save(IB_Utilisateur ibUtilisateur) {
        return null;
    }

    @Override
    public IB_Utilisateur update(IB_Utilisateur ibUtilisateur) {
        return null;
    }

    @Override
    public Boolean delete(IB_Utilisateur ibUtilisateur) {
        return null;
    }

    @Override
    public Boolean deleteById(Long aLong) {
        return null;
    }
}
