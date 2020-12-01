import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import json.Root;

public class waqi {
	public static void main(String args[]) throws Exception{
		Connection conn=getDBConnect();
		try {
			String[] name= {"Ningde","Fuzhou","Putian","Quanzhou","Xiamen"};
			for(int i=0;i<name.length;i++) {
				String json = getJSON("https://api.waqi.info/feed/"+name[i]+"/?token=6530220a3b69dc08b212bd43a2cbb5df965d5941");
				ObjectMapper objectMapper = new ObjectMapper();
				objectMapper.setSerializationInclusion(Include.NON_NULL);
				Root root = objectMapper.readValue(json, Root.class);
				if(root!=null && root.getStatus().equals("ok")) {
//					System.out.println(root.getData().getIaqi().getCo().getV());
//					if(root.getData().getIaqi().getD()!=null) {
//						String d=String.valueOf(root.getData().getIaqi().getD().getV());
//						System.out.println("d="+d);
//					}					
					
					String sql = "INSERT INTO most_waqi (name,idx,geo,aqi,co,relative_humidity,no2,o3,atmospheric_pressure,pm10,pm25,so2,wind,dew,wd,temperature,time_) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		    		  PreparedStatement ptmt = conn.prepareStatement(sql); 
		    		  ptmt.setString(1, root.getData().getCity().getName());
		    		  ptmt.setString(2, String.valueOf(root.getData().getIdx()));
		    		  ptmt.setString(3, root.getData().getCity().getGeo().toString());
		    		  ptmt.setString(4, String.valueOf(root.getData().getAqi()));
		    		  ptmt.setString(5, String.valueOf(root.getData().getIaqi().getCo().getV()));
		    		  if(root.getData().getIaqi().getH()!=null) {
		    			  ptmt.setString(6, String.valueOf(root.getData().getIaqi().getH().getV()));  
		    		  }else {
		    			  ptmt.setString(6, null);
		    		  }
		    		  
		    		  ptmt.setString(7, String.valueOf(root.getData().getIaqi().getNo2().getV()));
		    		  ptmt.setString(8, String.valueOf(root.getData().getIaqi().getO3().getV()));
		    		  if(root.getData().getIaqi().getP()!=null) {
		    			  ptmt.setString(9, String.valueOf(root.getData().getIaqi().getP().getV()));  
		    		  }else {
		    			  ptmt.setString(9, null);
		    		  }
		    		  
		    		  ptmt.setString(10, String.valueOf(root.getData().getIaqi().getPm10().getV()));
		    		  ptmt.setString(11, String.valueOf(root.getData().getIaqi().getPm25().getV()));
		    		  ptmt.setString(12, String.valueOf(root.getData().getIaqi().getSo2().getV()));
		    		  if(root.getData().getIaqi().getW()!=null) {
		    			  ptmt.setString(13, String.valueOf(root.getData().getIaqi().getW().getV()));  
		    		  }else {
		    			  ptmt.setString(13, null);
		    		  }
		    		  if(root.getData().getIaqi().getD()!=null) {
		    			  ptmt.setString(14, String.valueOf(root.getData().getIaqi().getD().getV()));  
		    		  }else {
		    			  ptmt.setString(14, null);
		    		  }
		    		  if(root.getData().getIaqi().getWd()!=null) {
		    			  ptmt.setString(15, String.valueOf(root.getData().getIaqi().getWd().getV()));  
		    		  }else {
		    			  ptmt.setString(15, null);
		    		  }
		    		  if(root.getData().getIaqi().getT()!=null) {
		    			  ptmt.setString(16, String.valueOf(root.getData().getIaqi().getT().getV()));  
		    		  }else {
		    			  ptmt.setString(16, null);
		    		  }
		    		  
		    		  ptmt.setString(17, root.getData().getTime().getS());
		    		  ptmt.execute(); 
				}
			}							 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println(e.toString());
		}finally {
			if(conn!=null) {
				conn.close();
	    	}	      	
	    }		
	}
	
	public static String getJSON(String url) throws IOException {

	     URL u = new URL(url);
	     HttpURLConnection c = (HttpURLConnection) u.openConnection();
	     c.setRequestMethod("GET");
	     c.setUseCaches(false);
	     c.setAllowUserInteraction(false);
	     c.setConnectTimeout(9000); 
	     c.setReadTimeout(9000); 
	     c.setRequestProperty("User-Agent","Mozilla/5.0");
	     c.connect();
	     int status = c.getResponseCode();

	     switch (status) {
	         case 200:
	         case 201:
	             BufferedReader br = new BufferedReader(new InputStreamReader(c.getInputStream(),"utf-8"));
	             StringBuilder sb = new StringBuilder();
	             String line;
	             while ((line = br.readLine()) != null) {
	                 sb.append(line + "\n");
	             }
	             br.close();
	             return sb.toString();
	     }
	     return null;
	}
	
	public static Connection getDBConnect() throws Exception{
	    Connection conn=null;
	    try {	   
	        Class.forName("com.mysql.jdbc.Driver");
	        conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/most?useUnicode=true&characterEncoding=UTF-8","root","!CID1234");	            
	        }catch(Exception ex){
	            System.out.println("Error: "+ex.getStackTrace());
	        }
	    return conn;
	}

}
