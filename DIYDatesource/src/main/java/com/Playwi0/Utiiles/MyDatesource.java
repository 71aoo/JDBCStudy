package com.Playwi0.Utiiles;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

public class MyDatesource {

    private static Properties properties = null;

    // 初始化池中连接数
    private static int initCount = 5;

    // 池中最小空闲连接数
    private static int minIdleCount = 3;

    // 池中最大容量
    private static int maxIdleCount = 10;

    // 当前连接数
    private static int currentIdleCount = 0;

    // 数据源创建连接数
    private static int createCount = 0;

    // 连接池
    private final static LinkedList<Connection> CONNECTIONSPOOLS = new LinkedList<Connection>();

    static {

        try {

            InputStream in = MyDatesource.class.getClassLoader().getResourceAsStream("JDBC.properties");

            properties = new Properties();

            properties.load(in);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {

            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public MyDatesource(){

        try {

            for (int i = 0; i < initCount; i++) {

                Connection connection = DriverManager.getConnection(
                                        properties.getProperty("url"),
                                        properties.getProperty("user"),
                                        properties.getProperty("password"));

                CONNECTIONSPOOLS.addLast(createProxyConnection(connection));
                currentIdleCount ++;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 返回代理连接
    public Connection getConnection() throws Exception{

        synchronized (CONNECTIONSPOOLS){

            if (currentIdleCount > 0){
                currentIdleCount --;

                if (currentIdleCount < minIdleCount){

                        Connection connection = DriverManager.getConnection(
                                properties.getProperty("url"),
                                properties.getProperty("user"),
                                properties.getProperty("password"));

                        CONNECTIONSPOOLS.addLast(createProxyConnection(connection));
                        currentIdleCount ++;
                }

                return CONNECTIONSPOOLS.removeFirst();

            }

            Connection connection = DriverManager.getConnection(
                    properties.getProperty("url"),
                    properties.getProperty("user"),
                    properties.getProperty("password"));

            return createProxyConnection(connection);
        }
    }

    // 代理
    public Connection createProxyConnection(final Connection realConn){

        ClassLoader connClassLoader = realConn.getClass().getClassLoader();

        Class<?>[] interfaces = realConn.getClass().getInterfaces();

        Connection ProxyConnection = (Connection) Proxy.newProxyInstance(
                connClassLoader,
                interfaces,
                new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                        String methodName = method.getName();

                        if (methodName.equals("close")){
                            if (MyDatesource.currentIdleCount < MyDatesource.maxIdleCount){

                                MyDatesource.CONNECTIONSPOOLS.addLast((Connection) proxy);
                                MyDatesource.currentIdleCount ++;

                                return 1;
                            }else {

                                realConn.close();

                                return 1;
                            }
                        }

                        return method.invoke(realConn,args);
                    }
                });

        return ProxyConnection;
    }

    public static int getInitCount() {
        return initCount;
    }

    public static int getMinIdleCount() {
        return minIdleCount;
    }

    public static int getMaxIdleCount() {
        return maxIdleCount;
    }

    public static int getCurrentIdleCount() {
        return currentIdleCount;
    }

    public static int getCreateCount() {
        return createCount;
    }
}

