package Dao;

import javax.rmi.CORBA.Util;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.Properties;

/**
 * Created by Miguel Angel on 06/04/2017.
 */
public class MySQLRepository<T> {

    //SELECT --> executeQuery
    //INSERT, UPDATE or DELETE --> executeUpdate


    public void add(String query) {
        Connection con = getConnection();
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(query);
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución MySQLRepository.add:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
    }

    public void delete(String query) {
        Connection con = getConnection();
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(query);
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución MySQLRepository.delete:"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
    }

    public void update(String query) {
        Connection con = getConnection();
        try {
            Statement stmt = con.createStatement();
            int rs = stmt.executeUpdate(query);
            stmt.close();
            con.close();

        } catch (SQLException sqle) {
            System.out.println("Error en la ejecución MySQLRepository.update"
                    + sqle.getErrorCode() + " " + sqle.getMessage());
        }
    }

    public T select(T t, String query) {
        Connection con = getConnection();
        try {
            Statement stmt = con.createStatement();
            ResultSet resultSet = stmt.executeQuery(query);
            T result = getMapObject(t, resultSet);
            stmt.close();
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

    private T getMapObject(T t, ResultSet resultSet){

        try {
            Class nameClass = t.getClass();

            Field[] propertyClass = nameClass.getDeclaredFields();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next())
            {
                for (int i = 1; i <= resultSetMetaData.getColumnCount(); i++) {
                    String columnName = resultSetMetaData.getColumnLabel(i);
                    String columnType = resultSetMetaData.getColumnTypeName(i);

                    for(int x = 0; x <= propertyClass.length; x++) {

                        if (columnName.equals(propertyClass[x].getName()) || columnType.equals(propertyClass[x].getType().toString())) {
                            Object value = getConvertValueType(columnType, resultSet, i);
                            if(value != null)
                            {
                                propertyClass[x].set(t, value);
                            }
                            break;
                        }
                    }
                }
            }
            return t;
        }
        catch(Exception e) {
            return null;
        }
    }

    private Object getConvertValueType(String columnType, ResultSet resultSet, int index) throws SQLException {
       /*
        if( Boolean.class.isAssignableFrom( type ) ) return resultSet.getBoolean(index);
        if( Byte.class.isAssignableFrom( type) ) return resultSet.getByte(index);
        if( Short.class.isAssignableFrom(type ) ) return resultSet.getShort(index);
        if( int.class.isAssignableFrom(type ) ) return resultSet.getInt(index);
        if( Long.class.isAssignableFrom( type ) ) return resultSet.getLong(index);
        if( Float.class.isAssignableFrom(type ) ) return resultSet.getFloat(index);
        if( Double.class.isAssignableFrom(type ) ) return resultSet.getDouble(index);
        if( String.class.isAssignableFrom(type)) return resultSet.getString(index);
        return null;

       */
       try{
           switch (columnType) {
               case "VARCHAR":
                   return resultSet.getString(index);

               case "INT":
                   return resultSet.getInt(index);

               case "DOUBLE":
                   return resultSet.getDouble(index);

               case "TINYINT":
                   return resultSet.getBoolean(index);

               default:
                   return null;
           }
       }
       catch (Exception e)
       {
           return null;
       }
    }



}





