package MyTest;

import com.Playwi0.dao.UserDaoJDBCImpl;
import com.Playwi0.pojo.User;
import org.junit.Test;

import java.util.List;

public class MyTest {

    @Test
    public void one(){

        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();

        List<User> list = userDaoJDBC.selectUser(18);

        for (User u: list) {
            System.out.println(u);
        }
    }
}
