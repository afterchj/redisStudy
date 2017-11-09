package com.tpadsz.test.utils;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hongjian.chen on 2017/9/14.
 */
public class DbMySql {

    public static int getTaskNum() {
        int times=0;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstm = null;
        String sql;
        String url = "jdbc:mysql://10.10.11.57:3306/ctc_hq?user=hongjian&password=hongjian&useUnicode=true&characterEncoding=UTF8";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection(url);
            sql = "SELECT COUNT(1) FROM `log_task` where app_id='9' and result_code='000' and DateDiff(create_date,CURDATE())=0";
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            if (rs.next()) {
                times=rs.getInt(1);
//                System.out.println(rs.getInt(1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null && pstm != null && conn != null) {
                try {
                    rs.close();
                    pstm.close();
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return times;
    }

    public static void main(String[] args) {
        getTaskNum();
    }
}
