package com.mayikt.pay;


import com.baomidou.mybatisplus.toolkit.IdWorker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author xxm
 * @create 2019-06-25 8:43
 */
public class TestMain {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        final String url = "jdbc:mysql://127.0.0.1:3306/meite_pay?autoReconnect=true&characterEncoding=UTF-8&useUnicode=true&useSSL=false&serverTimezone=Asia/Shanghai";
        final String name = "com.mysql.jdbc.Driver";
        final String user = "root";
        final String password = "root";
        Connection conn = null;
        Class.forName(name);
        conn = DriverManager.getConnection(url, user, password);
        if (conn != null) {
            System.out.println("获取连接成功");
            insert(conn);
        } else {
            System.out.println("获取连接失败");
        }
    }

    private static void insert(Connection conn) {
        // 开始时间
        Long begin = new Date().getTime();
        // sql 前缀
        String prefix = "insert into t_teacher (id,t_name,t_password,sex,description,pic_url,school_name,remark) values ";
        try {
            // 保存sql后缀
            StringBuilder suffix = new StringBuilder();
            // 设置事务为自动提交
            conn.setAutoCommit(false);
            // 比起st,pst会更好些
            PreparedStatement pst = conn.prepareStatement("");//准备执行语句
            // 外层循环,总提交事务次数
            for (int i = 0; i < 1000; i++) {
                suffix = new StringBuilder();
                for (int j = 0; j < 10000; j++) {
                    IdWorker idWorker = new IdWorker();
                    long id = idWorker.getId();
                    // 构建sql后缀
                    //suffix.append("("+id+",'" + i * j + "','123456'" + ",'男'" + ",'教师'" + ",'study.163.com'" + ",'网易云课堂'" + ",'备注'" + "),");
                    suffix.append("(null,'" + i * j + "','123456'" + ",'男'" + ",'教师'" + ",'study.163.com'" + ",'网易云课堂'" + ",'备注'" + "),");
                }

                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                // 添加执行sql
                pst.addBatch(sql);
                // 执行操作
                pst.executeBatch();

                // 提交事务
                conn.commit();

                // 清空上一次添加的数据
                suffix = new StringBuilder();
            }
            pst.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 结束时间
        Long end = new Date().getTime();
        System.out.println("耗时:"+(end-begin)/1000+"s");
    }
}
