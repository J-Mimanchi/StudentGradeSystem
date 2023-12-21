package com.util;


import java.sql.*;

//获得已连接好数据库的对象 并提供关闭资源服务
public class DBUtil {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/student_gui";//数据库连接字符串
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";//驱动包路径
    private static final String USER_NAME = "root";
    private static final String PWD = "jxb120608";

    //加载驱动
    static {
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //获取数据库链接
    public static Connection getConn() throws SQLException {
        return DriverManager.getConnection(URL,USER_NAME,PWD);
    }


    public static void closeConn(Connection connection) {
        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closePs(PreparedStatement ps) {
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void closeRs(ResultSet rs){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}
