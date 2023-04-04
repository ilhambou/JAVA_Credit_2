package org.example.dao;

import java.util.List;

public interface IB_IDao<T,ID> {
    T trouverParId(ID id);
    List<T> findall ();
    T save (T t);
    T update (T t);
    Boolean delete(T t);
    Boolean deleteById (ID id);
}
