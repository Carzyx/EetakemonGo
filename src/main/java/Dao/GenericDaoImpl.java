package Dao;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class GenericDaoImpl<T> extends MySQLRepository<T> implements IGenericDao<T> {

    public boolean add(T t) {
        try {
            Class nameClass = t.getClass();
            StringBuffer query = new StringBuffer("INSERT INTO ")
                .append(nameClass.getSimpleName().toLowerCase()).append("(");
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 1; (i < propertyClass.length - 1) || (i < 3); i++) {
                query.append(propertyClass[i].getName());
                if ((i < propertyClass.length - 2) || (i < 2)) {
                    query.append(",");
                }
            }
            query.append(") VALUES (");
            for (int i = 1; i < propertyClass.length - 1; i++) {
                if ((i < propertyClass.length - 2) || (i < 2)) {
                    query.append("?,");
                }
            }
            query.append("?)");
            add(query.toString(), t);
            return true;
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return false;
        }
    }

    /* public boolean addForeingTables(String bbdd,int i,int j){
         try
         {
             StringBuffer query = new StringBuffer("INSERT INTO ").append(bbdd).append(" (iduser,ideetakemon) VALUES (?,?)");
             add(query.toString(),i,j);
             return true;
         }
         catch (Exception e){
             return false;
         }
     }
     public boolean getForeingTables(String bbdd,int i){
         try
         {
             StringBuffer query = new StringBuffer("SELECT * FROM ").append(bbdd).append(" WHERE id==?");
             getForeing(query.toString(),i);
             return true;
         }
         catch (Exception e){
             return false;
         }
     }
     */
    public boolean updateById(T t) {
        try {
            Class nameClass = t.getClass();
            StringBuffer query = new StringBuffer("UPDATE ")
                .append(nameClass.getSimpleName().toLowerCase()).append(" SET ");
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {
                query.append(propertyClass[i].getName());
                query.append("= ?");

                if (i < propertyClass.length - 1) {
                    query.append(", ");
                }
            }
            query.append(" WHERE id =?");
            update(query.toString(), t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean removeById(T t) {

        try {
            Class nameClass = t.getClass();
            StringBuffer query = new StringBuffer("DELETE FROM ")
                .append(nameClass.getSimpleName().toLowerCase()).append(" WHERE id = ?");
            delete(query.toString(), t);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public List<T> getByParameter(T t, Hashtable<String, String> conditions) {

        //SELECT * FROM table_name;
        try {
            StringBuffer query = new StringBuffer();
            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();
            query.append("SELECT * FROM ").append(simpleNameClass.toLowerCase()).append(" WHERE ");
            Field[] propertyClass = nameClass.getDeclaredFields();
            int j = 1;
            for (int i = 0; i < propertyClass.length; i++) {
                if (conditions.containsKey(propertyClass[i].getName())) {
                    query.append(propertyClass[i].getName());
                    query.append(" = ?");
                    if (j < conditions.size()) {
                        query.append(" AND ");
                        j++;
                    }
                }
            }
            return select(t, query.toString(), conditions);
        } catch (Exception e) {
            return null;
        }
    }
    /*public  getListInClassByParameter(T t, Hashtable<String, String> conditions) {
        StringBuffer query = new StringBuffer();
        Class nameClass = t.getClass();
        String simpleNameClass = nameClass.getSimpleName();
        query.append("SELECT * FROM ").append(simpleNameClass.toLowerCase()).append(" JOIN WHERE ");
        Hashtable<String,String>conditions=new Hashtable<String, String>();
        conditions.p
        return (T) select(t, query.toString(),conditions);
    }*/

    public List<T> getAll(T t) {
        List<T> list = new ArrayList<T>();
        boolean fin = false;
        Hashtable<String, String> conditions = new Hashtable<String, String>();
        StringBuffer query;
        for (int i = 0; !fin; i++) {
            query = new StringBuffer();
            Class nameClass = t.getClass();
            query.append("SELECT * FROM ").append(nameClass.getSimpleName().toLowerCase())
                .append(" WHERE id=?");
            conditions.put("id", String.valueOf(i));
            return select(t, query.toString(), conditions);
        }

        return list;
    }

    public List<T> getById(T t, int i) {
        //SELECT * FROM table_name;
        try {
            Hashtable<String, String> conditions = new Hashtable<String, String>();
            StringBuffer query;
            query = new StringBuffer();
            Class nameClass = t.getClass();
            query.append("SELECT * FROM ").append(nameClass.getSimpleName().toLowerCase())
                .append(" WHERE id=?");
            conditions.put("id", String.valueOf(i));
            return select(t, query.toString(), conditions);
        } catch (Exception e) {
            return null;
        }
    }


}