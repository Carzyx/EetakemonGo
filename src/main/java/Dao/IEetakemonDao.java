package Dao;


import Model.Eetakemon;

import java.util.List;

/**
 * Created by Miguel Angel on 13/03/2017.
 */
public interface IEetakemonDao{

    boolean add(Eetakemon eetakemon) throws IllegalAccessException;
    boolean updateById(Eetakemon eetakemon) throws IllegalAccessException;
    boolean removeById(Eetakemon eetakemon) throws IllegalAccessException;
    Eetakemon getById(Eetakemon eetakemon) throws IllegalAccessException;
    List<Eetakemon> getAll();
}
