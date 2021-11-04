package com.yanolja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * DB 초기화 하고싶을때 connectDB 쓰면 됨
 */
@SpringBootApplication
public class YanoljaApplication {
    final static String id = "neo";
    final static String pwd = "1q2w3e4r!";
    static Connection conn = null;
    static Statement stmt = null;
    public static void main(String[] args) {
        SpringApplication.run(YanoljaApplication.class, args);
        //connectDB();
    }
    // DB 연결
    public static void connectDB(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://umc-neo-aws-study.cvv8ynus2ua9.ap-northeast-2.rds.amazonaws.com:3306/umc_yanolja?enabledTLSProtocols=TLSv1.2";
            conn = DriverManager.getConnection(url, id, pwd);
            System.out.println("연결 성공!");
            // 유저 테이블 없으면 새로 생성
            createUserTable();
        }catch(ClassNotFoundException e){
            System.out.println("드라이버 로딩 실패");
        }catch (SQLException e){
          System.out.println("error : " + e);
        }finally {
            try{
                // 자원 해제
                if(conn != null && !conn.isClosed())
                    conn.close();
            }catch (SQLException e){
                e.printStackTrace();;
            }
        }
    }
    // 유저 테이블 생성
    public static void createUserTable() throws SQLException{
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table user(")
                .append("userId INTEGER not NULL,")
                .append("name VARCHAR(10) not NULL,")
                .append("profileImgUrl text,")
                .append("email VARCHAR(30) not NULL,")
                .append("password VARCHAR(20) not NULL,")
                .append("phoneNumber VARCHAR(20) not NULL,")
                .append("deleteYN varchar(1),")
                .append("PRIMARY KEY(userId));")
                .toString();
        stmt.execute(sql);
    }
}
