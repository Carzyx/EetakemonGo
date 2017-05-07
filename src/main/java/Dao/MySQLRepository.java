package Dao;

import Model.EetakemonType;

import javax.rmi.CORBA.Util;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.*;

/**
 * Created by Miguel Angel on 06/04/2017.
 */
public class MySQLRepository<T> {

    //INSERT echo
    public boolean add(String query, T t) {
        Connection con = getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(query);
            Class nameClass = t.getClass();
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; (i < propertyClass.length); i++) {
                stm.setObject(i+1, getMethod(t, propertyClass[i].getName()));
            }
            stm.execute();
            con.close();
            return true;

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución MySQLRepository.add:"
                + sqle.getErrorCode() + " " + sqle.getMessage());
            return false;
        }
    }

    public void update(String query, T t) {
        Connection con = getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(query);
            Class nameClass = t.getClass();
            Field[] propertyClass = nameClass.getDeclaredFields();

            for (int i = 0; i < propertyClass.length; i++) {
                stm.setObject(i + 1, getMethod(t, propertyClass[i].getName()));
                if (propertyClass[i].getName().toUpperCase().equals("ID")) {
                    stm.setObject(propertyClass.length + 1,
                        getMethod(t, propertyClass[i].getName()));
                }
            }
            stm.execute();
            con.close();
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución MySQLRepository.update"
                + sqle.getErrorCode() + " " + sqle.getMessage());
        }
    }

    public boolean delete(String query, T t) throws Exception {
        Connection con = getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(query);
            Class nameClass = t.getClass();
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {
                if (propertyClass[i].getName().toUpperCase().equals("ID")) {
                    stm.setObject(1, getMethod(t, propertyClass[i].getName()));
                }
            }
            stm.execute();
            stm.close();
            con.close();
            return true;

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución MySQLRepository.delete:"
                + sqle.getErrorCode() + " " + sqle.getMessage());
            return false;
        }
    }

    public List<T> select(String query, T t) {
        Connection con = getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(query);
            stm.execute();
            ResultSet resultSet = stm.getResultSet();
            List<T> result = getMapObject(t, resultSet);
            stm.close();
            con.close();
            return result;
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecucion MySQLRepository.select"
                + sqle.getErrorCode() + " " + sqle.getMessage());
            return null;
        }
    }

    //revisar bucle
    public List<T> selectByCondition(String query, Hashtable<String, String> conditions, T t) {
        Connection con = getConnection();
        try {
            PreparedStatement stm = con.prepareStatement(query);
            Class nameClass = t.getClass();
            int maxIndex = 1 + conditions.size();
            Field[] propertyClass = nameClass.getDeclaredFields();
            for (int i = 0; i < propertyClass.length; i++) {
                if (conditions.containsKey(propertyClass[i].getName()) && conditions.size() > 0) {
                    stm.setObject(maxIndex - conditions.size(), conditions.get(propertyClass[i].getName()));
                    conditions.remove(propertyClass[i].getName());
                }
            }
            stm.execute();
            ResultSet resultSet = stm.getResultSet();
            List<T> result = getMapObject(t, resultSet);
            stm.close();
            con.close();
            return result;
        } catch (SQLException sqle) {
            System.out.println("Error en la ejecucion MySQLRepository.select"
                + sqle.getErrorCode() + " " + sqle.getMessage());
            return null;
        }
    }

    private Connection getConnection() {
        Connection con = null;
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost:3306/EetakemonBBDDTest";
        Properties properties = new Properties();
        properties.setProperty("user", "root");
        properties.setProperty("password", "root");
        properties.setProperty("useSSL", "false");
        properties.setProperty("serverTimezone", "UTC");
        try {
            Class.forName(JDBC_DRIVER);
            con = DriverManager.getConnection(DB_URL, properties);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    private List<T> getMapObject(T t, ResultSet resultSet) {

        try {
            Class nameClass = t.getClass();
            List<T> list = new ArrayList<T>();
            Field[] propertyClass = nameClass.getDeclaredFields();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()) {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String columnName = resultSetMetaData.getColumnLabel(i);
                    String columnType = resultSetMetaData.getColumnTypeName(i);

                    for (int x = 0; x < propertyClass.length; x++) {
                        if (columnName.equals(propertyClass[x].getName()) || columnType.equals(propertyClass[x].getType().toString())) {
                            Object value = getConvertValueType(columnType, resultSet, i);
                            if (value != null) {
                                Method m = t.getClass().getMethod(setProperty(propertyClass[x].getName()), propertyClass[x].getType());
                                m.invoke(t, value);
                            }
                            break;
                        }
                    }
                }
                list.add(t);
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    private Object getConvertValueType(String columnType, ResultSet resultSet, int index)
        {
        try {
            switch (columnType) {
                case "VARCHAR":
                    return resultSet.getString(index);

                case "INT":
                    return resultSet.getInt(index);
                case "INT UNSIGNED":
                    return resultSet.getInt(index);

                case "DOUBLE":
                    return resultSet.getDouble(index);

                case "TINYINT":
                    return resultSet.getBoolean(index);
                case "CHAR"://PARA EL ENUM
                    return EetakemonType.valueOf(resultSet.getString(index));

                default:
                    return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    private String getMethod(T t, String key){
        try {
            Method m = t.getClass().getMethod(getProperty(key));
            Object object = m.invoke(t, null);
            String ret = String.valueOf(object);
            return ret;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private String getProperty(String key) {
        String m = key.substring(0, 1).toUpperCase();
        StringBuffer f = new StringBuffer("get").append(m).append(key.substring(1));
        return f.toString();
    }

    private String setProperty(String key) {
        String m = key.substring(0, 1).toUpperCase();
        StringBuffer f = new StringBuffer("set").append(m).append(key.substring(1));
        return f.toString();
    }
}





