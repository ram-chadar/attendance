package com.dbcon;
import java.sql.*;  
  
  
public class Conn {  
private static Connection con=null;  
static{  
try{  
Class.forName("com.mysql.jdbc.Driver");  
//con=DriverManager.getConnection("jdbc:mysql://node38208-digitalstud.cloud.cms500.com/samsdatabase","root","18GV1vCZV1");  
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/samsdatabase","root","admin");  
//con=DriverManager.getConnection("jdbc:mysql://localhost:3306/samsdatabase","root","javabykiran");  

}catch(Exception e){
	System.out.println(e);
}  
}  
  
public static Connection getCon(){  
    return con;  
}  
  
}  