package com.selenium.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;



public class LoginTest {
	
	
	@Test(dataProvider="DP")
	public void validateLogin(String username, String password) {
		
		System.out.println("User Name is --->>>  "+username );
		System.out.println("Password  is --->>>  "+password );
		
//		WebDr.chromeDriver.setup();
//		WebDriver driver = new ChromeDriver();
//		driver.get(url);
//		driver.findElement(By.id("")).sendkeys(username);
//		driver.findElement(By.id("")).sendkeys(password);
//		driver.findElement(By.id("")).click();
		
	}
	
	
	
	
	
	@DataProvider(name="DP")
	public String [][] feedDP() throws Throwable {
		String data[][]= getDBValues ();
		return data;
	}
	
	
	
//	@Test
//	public void getDBValues () throws Throwable {
	
	public String[][] getDBValues () throws Throwable {
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		String url="jdbc:mysql://localhost:3306/testdb";
		String username="root";
		String password="admin";
		Connection con =DriverManager.getConnection(url, username, password);
	    Statement stat = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
	    ResultSet rs= stat.executeQuery("Select * from testdb.login");
	    rs.last();
	    int rows = rs.getRow();
	    
	    System.out.println("Number of rows " +rows);
	    
	    ResultSetMetaData rsmd = rs.getMetaData();
	    int cols = rsmd.getColumnCount();
	    
	    System.out.println("Number of cols :" + cols);
	    
	    String data[] [] = new String [rows][cols];
	    int i=0;
	    rs.beforeFirst();
	    while(rs.next()) {
	    	
	    	for (int j=0; j<cols; j++) {
	    		data [i][j]=rs.getString(j+1);
	    		
	    		System.out.println("rows and column "+data[i][j]);
	    	}
	    	i++;
	    	
	    }
	    
	    
	    return data;
	} 

}
