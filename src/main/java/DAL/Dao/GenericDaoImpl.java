package DAL.Dao;

import DAL.Dao.Interfaces.IGenericDao;
import org.apache.log4j.Logger;

import java.lang.reflect.Field;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class GenericDaoImpl<T> extends MySQLRepository<T> implements IGenericDao<T> {

    private final static Logger logger = Logger.getLogger(GenericDaoImpl.class);

    public boolean add(T t) {
        try {
            Class nameClass = t.getClass();
            StringBuilder query = new StringBuilder("INSERT INTO ")
                .append(nameClass.getSimpleName().toLowerCase()).append("(");
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; (i < propertyClass.length ); i++) {
                query.append(propertyClass[i].getName());
                if (i < propertyClass.length -1) {
                    query.append(",");
                }
            }
            query.append(") VALUES (");
            for (int i = 0; i < propertyClass.length; i++) {
                if (i < propertyClass.length -1) {
                    query.append("?,");
                }
            }
            query.append("?)");
            return add(query.toString(), t);
        } catch (Exception ex) {
            logger.error("Error en la ejecución GenericDaoImpl<T>.add: ", ex);
            return false;
        }
    }

    public boolean updateByConditions(T t, Hashtable<String, String> conditions) {
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
            query = registerConditions(propertyClass, conditions, query);
            return update(query.toString(), conditions, t);
        } catch (Exception ex) {
            logger.error("Error en la ejecución GenericDaoImpl<T>.updateByConditions: ", ex);
            return false;
        }
    }

    public boolean removeByConditions(T t, Hashtable<String, String> conditions) {
        try {
            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();
            StringBuffer query = new StringBuffer("DELETE FROM ").append(simpleNameClass.toLowerCase());
            Field[] propertyClass = nameClass.getDeclaredFields();
            query = registerConditions(propertyClass, conditions, query);
            return delete(query.toString(), conditions, t);
        } catch (Exception ex) {
            logger.error("Error en la ejecución GenericDaoImpl<T>.removeByConditions: ", ex);
            return false;
        }
    }

    public T get(T t) {
        List<T> result = getAll(t);

        if(result != null && result.size() > 0)
        {
            return result.get(0);
        }

        return t;
    }

    public List<T> getAll(T t) {
        try {
            StringBuilder query = new StringBuilder();
            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();
            query.append("SELECT * FROM ").append(simpleNameClass.toLowerCase());

            return select(query.toString(), t);
        } catch (Exception ex) {
            logger.error("Error en la ejecución GenericDaoImpl<T>.getAll: ", ex);
            return null;
        }
    }

    public T getByParameters(T t, Hashtable<String, String> conditions) {
          try {
              List<T> result = getAllByParameters(t, conditions);
              if(result != null && result.size() > 0)
              {
                  return result.get(0);
              }
              return null;

          } catch (Exception ex) {
              logger.error("Error en la ejecución GenericDaoImpl<T>.getByParameters: ", ex);
              return null;
        }
    }

    public List<T> getAllByParameters(T t, Hashtable<String, String> conditions) {

        //SELECT * FROM table_name;
        try {
            StringBuffer query = new StringBuffer();
            Class nameClass = t.getClass();
            String simpleNameClass = nameClass.getSimpleName();
            query.append("SELECT * FROM ").append(simpleNameClass.toLowerCase());
            Field[] propertyClass = nameClass.getDeclaredFields();
            if(conditions != null && conditions.size() > 0)
            {
                query = registerConditions(propertyClass, conditions, query);
            }

            return selectByCondition(query.toString(), conditions, t);
        } catch (Exception ex) {
            logger.error("Error en la ejecución GenericDaoImpl<T>.getAllByParameters: ", ex);
            return null;
        }
    }

    private StringBuffer registerConditions(Field[] propertyClass, Hashtable<String, String> conditions, StringBuffer query) {
        Hashtable<String, String> conditionsToAdd = new Hashtable<>();
        conditionsToAdd.putAll(conditions);
        if(conditionsToAdd.size() == 0)
        {
            return query;
        }

        query.append(" WHERE ");
        for (int i = 0; i < propertyClass.length; i++) {
            if (conditionsToAdd.containsKey(propertyClass[i].getName())) {
                query.append(propertyClass[i].getName());
                query.append(" = ?");
                conditionsToAdd.remove(propertyClass[i].getName());
                if ( conditionsToAdd.size() > 0) {
                    query.append(" AND ");
                }
            }
        }
        return query;
    }

}