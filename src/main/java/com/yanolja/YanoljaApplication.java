package com.yanolja;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/** DB 테이블 새로만들고 싶으면 createTable() 쓰면 됨
 * @Author Neo
 * @LastModified 21.11.05
 * */

@SpringBootApplication
public class YanoljaApplication {
    final static String id = "neo";
    final static String pwd = "1q2w3e4r!";
    static Connection conn = null;
    static Statement stmt = null;
    public static void main(String[] args) {
        SpringApplication.run(YanoljaApplication.class, args);
        createTable();
    }
    // DB 연결 및 모든 테이블 생성
    public static void createTable(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            String url = "jdbc:mysql://umc-neo-aws-study.cvv8ynus2ua9.ap-northeast-2.rds.amazonaws.com:3306/umc_yanolja?enabledTLSProtocols=TLSv1.2";
            conn = DriverManager.getConnection(url, id, pwd);
            System.out.println("연결 성공!");
            // user 테이블 없으면 새로 생성
            createUserTable();
            // owner 테이블 없으면 새로 생성
            createOwnerTable();
            // room 테이블 없으면 새로 생성
            createRoomTable();
            // roomContent 테이블 없으면 새로 생성
            createRoomContentTable();
            // rent 테이블 없으면 새로 생성
            createRentTable();
            // lodge 테이블 없으면 새로 생성
            createLodgeTable();
            // discount 테이블 없으면 새로 생성
            createDiscountTable();
            // reserve 테이블 없으면 새로 생성
            createReserveTable();
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
    // user 테이블 생성
    public static void createUserTable() throws SQLException{
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table if not exists user(")
                .append("userId INTEGER not NULL AUTO_INCREMENT,")
                .append("name VARCHAR(10) not NULL,")
                .append("profileImgUrl text,")
                .append("email VARCHAR(30) not NULL,")
                .append("password VARCHAR(100) not NULL,")
                .append("phoneNumber VARCHAR(20) not NULL,")
                .append("deleteYN varchar(1),")
                .append("createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,")
                .append("updateDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,")
                .append("deleteDate TIMESTAMP,")
                .append("PRIMARY KEY(userId));")
                .toString();
        stmt.execute(sql);
        System.out.println("user 테이블이 생성되었습니다!");
    }
    // owner 테이블 생성
    public static void createOwnerTable() throws SQLException{
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table if not exists owner(")
                .append("ownerId INTEGER not NULL AUTO_INCREMENT,")
                .append("name VARCHAR(10) not NULL,")
                .append("email VARCHAR(30) not NULL,")
                .append("password VARCHAR(100) not NULL,")
                .append("phoneNumber VARCHAR(15) not NULL,")
                .append("deleteYN VARCHAR(1),")
                .append("createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,")
                .append("updateDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,")
                .append("PRIMARY KEY(ownerId));")
                .toString();
        stmt.execute(sql);
        System.out.println("owner 테이블이 생성되었습니다!");
    }
    // room 테이블 생성
    public static void createRoomTable() throws SQLException {
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table if not exists room(")
                .append("roomId INTEGER not NULL AUTO_INCREMENT,")
                .append("ownerId INTEGER not NULL,")
                .append("roomType VARCHAR(10) not NULL,")
                .append("name VARCHAR(20) not NULL,")
                .append("phoneNumber VARCHAR(15),")
                .append("location VARCHAR(15) not NULL,")
                .append("imgUrl text not NULL,")
                .append("businessNumber VARCHAR(11) not NULL,")
                .append("service VARCHAR(15),")
                .append("information text,")
                .append("deleteYN VARCHAR(1),")
                .append("createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,")
                .append("updateDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,")
                .append("PRIMARY KEY(roomId),")
                .append("foreign key (ownerId) references owner(ownerId));")
                .toString();
        stmt.execute(sql);
        System.out.println("room 테이블이 생성되었습니다!");
    }
    // roomContent 테이블 생성
    public static void createRoomContentTable() throws SQLException {
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table if not exists roomContent(")
                .append("roomContentId INTEGER not NULL AUTO_INCREMENT,")
                .append("roomId INTEGER not NULL,")
                .append("name VARCHAR(20) not NULL,")
                .append("count INTEGER not NULL,")
                .append("content VARCHAR(30),")
                .append("deleteYN VARCHAR(1),")
                .append("createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,")
                .append("updateDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,")
                .append("PRIMARY KEY(roomContentId),")
                .append("FOREIGN KEY (roomId) REFERENCES room(roomId));")
                .toString();
        stmt.execute(sql);
        System.out.println("roomContent 테이블이 생성되었습니다!");
    }
    // rent 테이블 생성
    public static void createRentTable() throws SQLException {
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table if not exists rent(")
                .append("rentId INTEGER not NULL AUTO_INCREMENT,")
                .append("roomContentId INTEGER not NULL,")
                .append("openTime VARCHAR(10) not NULL,")
                .append("closeTime VARCHAR(10) not NULL,")
                .append("maxTime INTEGER not NULL,")
                .append("count INTEGER not NULL,")
                .append("price INTEGER not NULL,")
                .append("deleteYN VARCHAR(1),")
                .append("createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,")
                .append("updateDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,")
                .append("PRIMARY KEY(rentId),")
                .append("FOREIGN KEY (roomContentId) REFERENCES room (roomContentId));")
                .toString();
        stmt.execute(sql);
        System.out.println("rent 테이블이 생성되었습니다!");
    }
    // lodge 테이블 생성
    public static void createLodgeTable() throws SQLException {
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table if not exists lodge(")
                .append("lodgeId INTEGER not NULL AUTO_INCREMENT,")
                .append("roomContentId INTEGER not NULL,")
                .append("checkIn VARCHAR(20) not NULL,")
                .append("checkOut VARCHAR(20) not NULL,")
                .append("price INTEGER not NULL,")
                .append("deleteYN VARCHAR(1),")
                .append("createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,")
                .append("updateDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,")
                .append("PRIMARY KEY(lodgeId),")
                .append("FOREIGN KEY (roomContentId) REFERENCES roomContent (roomContentId));")
                .toString();
        stmt.execute(sql);
        System.out.println("lodge 테이블이 생성되었습니다!");
    }
    // discount 테이블 생성
    public static void createDiscountTable() throws SQLException {
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table if not exists discount(")
                .append("discountId INTEGER not NULL AUTO_INCREMENT,")
                .append("lodgeId INTEGER,")
                .append("rentId INTEGER,")
                .append("discountNum INTEGER not NULL,")
                .append("count INTEGER,")
                .append("firstComeYN VARCHAR(1),")
                .append("deleteYN VARCHAR(1),")
                .append("createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,")
                .append("updateDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,")
                .append("PRIMARY KEY(discountId),")
                .append("FOREIGN KEY (lodgeId) REFERENCES lodge (lodgeId),")
                .append("FOREIGN KEY (rentId) REFERENCES rent (rentId));")
                .toString();
        stmt.execute(sql);
        System.out.println("discount 테이블이 생성되었습니다!");
    }
    // reserve 테이블 생성
    public static void createReserveTable() throws SQLException {
        stmt = conn.createStatement();
        StringBuilder sb = new StringBuilder();
        String sql = sb.append("create table if not exists reserve(")
                .append("reserveId INTEGER not NULL AUTO_INCREMENT,")
                .append("userId INTEGER not NULL,")
                .append("roomId INTEGER not NULL,")
                .append("visitDate VARCHAR(20) not NULL,")
                .append("closeDate VARCHAR(20) not NULL,")
                .append("reserveName VARCHAR(10) not NULL,")
                .append("reservePhoneNumber VARCHAR(15) not NULL,")
                .append("useName VARCHAR(10) not NULL,")
                .append("usePhoneNumber VARCHAR(15) not NULL,")
                .append("visitMethod VARCHAR(15) not NULL,")
                .append("deleteYN VARCHAR(1),")
                .append("createDate TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,")
                .append("updateDate TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,")
                .append("PRIMARY KEY(reserveId),")
                .append("FOREIGN KEY (userId) REFERENCES user (userId),")
                .append("FOREIGN KEY (roomId) REFERENCES room (roomId));")
                .toString();
        stmt.execute(sql);
        System.out.println("reserve 테이블이 생성되었습니다!");
    }
}
