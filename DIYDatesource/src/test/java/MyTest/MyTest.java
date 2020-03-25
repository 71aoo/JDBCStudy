package MyTest;

import com.Playwi0.Utiiles.MyDatesource;

import java.sql.Connection;

public class MyTest {

    public static void main(String[] args) throws Exception {

        MyDatesource myDatesource = new MyDatesource();

        Connection connection = myDatesource.getConnection();

        Connection connection1 = myDatesource.getConnection();

        connection.close();

        System.out.println(MyDatesource.getCurrentIdleCount());
    }
}
