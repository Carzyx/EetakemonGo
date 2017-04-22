package Dao;

import Model.User;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class GenericDaoImpl<T> extends MySQLRepository<T> implements IGenericDao<T> {

    public boolean add(T t){
        try{
            Class nameClass = t.getClass();
            StringBuffer query = new StringBuffer("INSERT INTO ").append(nameClass.getSimpleName().toLowerCase()).append("(");
            Field[] propertyClass = nameClass.getDeclaredFields();
            for(int i=0; i< propertyClass.length; i++)
            {
                query.append(propertyClass[i].getName());
                if(i < propertyClass.length-1)
                    query.append(", ");
            }
            query.append(") VALUES (");
            for(int i=0; i< propertyClass.length; i++)
            {
                if(i<propertyClass.length-1)
                    query.append("?,");
                else
                    query.append("?)");
            }
            System.out.println(query);
            add(query.toString(),t);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }

    public boolean updateById(T t)  {
        try{
            Class nameClass = t.getClass();
            StringBuffer query = new StringBuffer("UPDATE ").append(nameClass.getSimpleName().toLowerCase()).append(" SET ");
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {
                query.append("'");
                query.append(propertyClass[i].getName());
                query.append("' ");
                query.append("= ?");

                if (i < propertyClass.length - 1) {
                    query.append(", ");
                }
            }
            query.append(" WHERE id =?");
            System.out.println(query);
            update(query.toString(),t);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public boolean removeById(T t)  {

        try{
            Class nameClass = t.getClass();
            StringBuffer query = new StringBuffer("DELETE FROM ").append(nameClass.getSimpleName().toLowerCase()).append(" WHERE id = ?");
            System.out.println(query);
            delete(query.toString(),t);
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public T getByParameter(T t, Hashtable<String, String> conditions) {

        //SELECT * FROM table_name;
        try{
            StringBuffer query = new StringBuffer();
            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();
            query.append("SELECT * FROM ").append(simpleNameClass.toLowerCase()).append(" WHERE ");
            Field[] propertyClass = nameClass.getDeclaredFields();
            int j=1;
            for (int i = 0; i < propertyClass.length; i++) {
                if (conditions.containsKey(propertyClass[i].getName())) {
                    query.append(propertyClass[i].getName());
                    query.append(" = ?");
                    if(j<conditions.size())
                    {
                        query.append(" AND ");
                        j++;
                    }
                }
            }
            System.out.println(query);
            return (T) select(t, query.toString(),conditions);
        }
        catch (Exception e)
        {
            return null;
        }
    }

    public List<T> getAll(T t) throws Exception {
        List<T> list=new ArrayList<T>();
        boolean fin=false;
        Hashtable<String,String> conditions=new Hashtable<String, String>();
        StringBuffer query;
        for (int i=0;!fin;i++){
            query = new StringBuffer();
            Class nameClass = t.getClass();
            query.append("SELECT * FROM ").append(nameClass.getSimpleName().toLowerCase()).append(" WHERE id=?");
            conditions.put("id",String.valueOf(i));
            T a=select(t,query.toString(),conditions);
            if(a==null)
                fin=true;
            else
                list.add(a);
        }

        return list;
    }

    public T getById(T t,int i) {
        //SELECT * FROM table_name;
        try{
            Hashtable<String,String> conditions=new Hashtable<String, String>();
            StringBuffer query;
            query = new StringBuffer();
            Class nameClass = t.getClass();
            query.append("SELECT * FROM ").append(nameClass.getSimpleName().toLowerCase()).append(" WHERE id=?");
            conditions.put("id",String.valueOf(i));
            T a=select(t,query.toString(),conditions);
            return a;
        }
        catch (Exception e)
        {
            return null;
        }
    }





}