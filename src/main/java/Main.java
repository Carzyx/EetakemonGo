import Controller.EetakemonService;
import Controller.UserService;
import Dao.*;
import Model.*;
import com.mysql.jdbc.StringUtils;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;

/**
 * Created by Miguel Angel on 04/04/2017.
 */
public class Main {

    public static void main(String [] args) throws IllegalAccessException {
        //Meter esto en la api para inicializar los servdores;
        UserDao userDao = new UserDao(new GenericDaoImpl<User>());
        UserService serviceUser = new UserService(userDao);
        EetakemonDao eetakemonDao=new EetakemonDao(new GenericDaoImpl<Eetakemon>(),new GenericDaoImpl<EetakemonsUser>());
        AtackDao atackDao=new AtackDao(new GenericDaoImpl<Atack>(),new GenericDaoImpl<AtacksEetakemon>());
        EetakemonService serviceEetakemon=new EetakemonService(eetakemonDao,atackDao);
        //Interesa colocar Bases de datos locales en la api para no depender de esto siempre que es mas lento


        //EetakemonDao eetakemonDao =new EetakemonDao(new GenericDaoImpl<Eetakemon>());
        //EetakemonService service1=new EetakemonService(eetakemonDao);
        //boolean createUser = serviceUser.createUser(new User("Minombre","MiSurname","Username","1234","micorreo",1,"asdfl"));
        /*User user =new User("Prueba","sadsadas","MyUser","miguel1234","miguel1234",1);
        User userSignUp = service.signIn(user.getUsername(), user.getPassword());
        boolean isUserSignIn = (userSignUp.getUsername() != null);
        System.out.println("signIn : "+isUserSignIn);
        service.createUser(new User("test'A",
                "testApA",
                "SuperTestA",
                "testA1234",
                "testA@gmail.com",
                1));


        boolean isUserUpdated = service.upadteUser(new User("TESTA",
                "testApA",
                "SuperTestA",
                "testA1234",
                "testA@gmail.com",
                1),"SuperTestA");
        System.out.println("isUserUpdated : "+isUserUpdated);
        boolean isUserDeleted = service.deleteUser("SuperTestA", "testA1234");
        System.out.println("isUserDeleted : "+isUserDeleted);*/

        //Eetakemon eetakemon =new Eetakemon("lobo",1,100,EetakemonType.FUEGO,"asa","asdadsa");
        //boolean isEetakemoncreated=eetakemonService.createEetakemon(eetakemon);
        //System.out.println("isEetakemoncreated : "+isEetakemoncreated);
        //boolean isEetakemonAdd=eetakemonService.addEetakemonToUser(3,eetakemon);
        //System.out.println("isEetakemonAdd : "+isEetakemonAdd);
        //AtacksEetakemon atacksEetakemon=new AtacksEetakemon();
        //boolean isEetakemonAdd=eetakemonService.addAtackToEetakemon(1,atacksEetakemon);
        //System.out.println("isEetakemonAdd : "+isEetakemonAdd);
        /*Atack atack=new Atack();
        atack.setName("Rayazo");
        atack.setDamagebase(1);
        atack.setType(EetakemonType.FUEGO);
        atack.setDescription("asasa");
        boolean isAtackcreated=eetakemonService.addAtack(atack);
        System.out.println("isAtackcreated : "+isAtackcreated);*/
        //User user=service.getUser(1);
        //System.out.println(user.getName());
        //Eetakemon eetakemon=serviceEetakemon.getEetakemon(1);
        //System.out.println(eetakemon.getName());
        List<Eetakemon> eetakemons=serviceEetakemon.getUserEetakemons(3);
        System.out.print("");

    }
}