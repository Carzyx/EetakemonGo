package Dao;

import Model.User;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class GenericDaoImpl<T> extends MySQLRepository<T> implements IGenericDao<T> {


    public boolean add(T t){

        try{
            StringBuffer query = new StringBuffer();

            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();

            query.append("INSERT INTO ");
            query.append(simpleNameClass);

            Field[] propertyClass = nameClass.getDeclaredFields();
            for(int i=0; i< propertyClass.length; i++)
            {
                if(i == 0){
                    //todo first
                    query.append("(");
                }
                query.append(propertyClass[i].getName());

                if(i != propertyClass.length-1)
                {
                    query.append(", ");
                }
                if(i == propertyClass.length-1)
                {
                    query.append(") ");
                }
            }
            for(int i=0; i< propertyClass.length; i++)
            {
                if(i == 0){
                    //todo first
                    query.append("VALUES (");
                }
                query.append("'");
                query.append(propertyClass[i].get(t).toString());
                query.append("'");

                if(i != propertyClass.length-1)
                {
                    query.append(", ");
                }
                if(i == propertyClass.length-1)
                {
                    query.append(") ");
                }
            }
            System.out.println(query);
            add(query.toString());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }

    }


    public boolean updateById(T t)  {

        //UPDATE table_name SET column1 = value1, column2 = value2, ... WHERE condition;
        try{
            StringBuffer query = new StringBuffer();

            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();

            query.append(" UPDATE ");
            query.append(simpleNameClass);


            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {
                if (i == 0) {
                    //todo first
                    query.append("SET ");
                }
                query.append("'");
                query.append(propertyClass[i].getName());
                query.append("' ");

                query.append("= ");
                query.append("'");
                query.append(propertyClass[i].get(t));
                query.append("'");

                if (i != propertyClass.length - 1) {
                    query.append(", ");
                }
            }

            for (int i = 0; i < propertyClass.length; i++) {
                if (i == 0) {
                    //todo first
                    query.append(" WHERE id =");
                }
                if (propertyClass[i].getName().toUpperCase().equals("ID")) {
                    System.out.println("1- "+propertyClass[i].get(t));
                    System.out.println("1- "+propertyClass[i].get(t).toString());


                    query.append("'");
                    query.append(propertyClass[i].get(t).toString());
                    query.append("'");
                }
            }
            System.out.println(query);
            update(query.toString());
            return true;
        }
        catch (Exception e)
        {
            return false;
        }
    }


    public boolean removeById(T t)  {

        //DELETE FROM table_name WHERE condition;
        try{
            StringBuffer query = new StringBuffer();

            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();

            query.append("DELETE FROM ");
            query.append(simpleNameClass);

            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {
                if (i == 0) {
                    //todo first
                    query.append(" WHERE id =");
                }
                if (propertyClass[i].getName().toUpperCase().equals("ID")) {
                    query.append("'");
                    query.append(propertyClass[i].get(t).toString());
                    query.append("'");
                }
            }

            System.out.println(query);
            delete(query.toString());
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

            query.append("SELECT * FROM ");
            query.append(simpleNameClass);

            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {
                if (i == 0) {
                    //todo first
                    query.append(" WHERE ");
                }
                if (conditions.containsKey(propertyClass[i].getName())) {
                    query.append(propertyClass[i].getName());
                    query.append(" = '");
                    query.append(conditions.get(propertyClass[i].getName()));
                    query.append("' ");
                    conditions.remove(propertyClass[i].getName());
                    if(conditions.size() != 0)
                    {
                        query.append("AND ");
                    }
                }
            }

            System.out.println(query);
            return (T) select(t, query.toString());


        }
        catch (Exception e)
        {
            return null;
        }
    }


    public List<T> getAll(Class nameClass){
        //SELECT * FROM table_name;
        StringBuffer query = new StringBuffer();

        String simpleNameClass = nameClass.getSimpleName();

        query.append("SELECT * FROM ");
        query.append("'");
        query.append(simpleNameClass);
        query.append("' ");

        System.out.println(query);
        return null;

    }

    public T getById(T t) {
/*
        //SELECT * FROM table_name;
        try{
            StringBuffer query = new StringBuffer();

            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();

            query.append("SELECT * FROM ");
            query.append(simpleNameClass);

            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {
                if (i == 0) {
                    //todo first
                    query.append(" WHERE id =");
                }
                if (propertyClass[i].getName().toUpperCase().equals("ID")) {
                    query.append("'");
                    query.append(propertyClass[i].get(t).toString());
                    query.append("'");
                }
            }

            System.out.println(query);
            return null;

        }
        catch (Exception e)
        {
            return null;
        }
     */
        return null;
    }





}