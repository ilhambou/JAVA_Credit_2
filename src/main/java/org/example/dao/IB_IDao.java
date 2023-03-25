package org.example.dao;

public interface IB_IDao<T,ID> {
    T trouverParId(ID id);
}
