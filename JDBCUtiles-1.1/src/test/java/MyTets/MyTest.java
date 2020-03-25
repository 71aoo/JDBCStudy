package MyTets;

import com.Playwi0.dao.UserDaoJDBCImpl;
import com.Playwi0.pojo.User;
import org.junit.Test;

public class MyTest {


    public void one(){

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        User user = userDaoJDBC.getUser(1);

        System.out.println(user);
    }

    @Test
    public void two(){

        User user = new User("us", "man", 5);

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        int i = userDaoJDBC.addUser(user);

        System.out.println(i);
    }

}
