


package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;



public class Test {
	
	static String token = "qui il token";

	//qui i vari parametri richiesti dal servizio(in questo caso streamelements) da aggiungere nel url
	static String limit="1000";
	static String offset="32";
	
	
	
	 public static String getUserListJson() throws IOException {
    	 String userlist = "";
		 System.out.println("ciao");

    		 String base = "https://api.streamelements.com/kappa/v2/points/5ed619e4ccbc2295774d13a4/alltime?"+
    		 "limit="+limit+"&offset="+offset+"&authorization="+token;
    		 URL u = new URL(base);
    		 URLConnection connection = u.openConnection();
    		 connection.connect();
    		 BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
    		 String inputline;
    		 //string buffer serve per creare una stringa mutabile(le stringe di default sono immutabili)
    		 //quindi per cambiare il valore della stringa , la stringa deve essere ridichiarata(con StirngBuffer no!)
    		 //maggiori info nel txt website galthor se dovesis avere vuoti di memoria
    		 StringBuffer b = new StringBuffer();
    		 while((inputline = in.readLine()) != null) {
    			b.append(inputline + "\n");
    		//	in.close();
    			userlist = b.toString();
    		 }
    		 return userlist;
    		 }
	 
	
	 
	 public static Map getUsersLists(String userlist) {
		 
			Map users = new HashMap();
			JSONObject json = new JSONObject(userlist);
			//quando devi ottenere dati dagli array
		    JSONArray userArray = json.getJSONArray("users");
		 
		    int tot = json.getInt("_total");
		    System.out.println(tot);
		    Map UserList = new HashMap<>();
		    for (int i = 0; i < userArray.length(); i++) {            
		        String username = userArray.getJSONObject(i).getString("username");
		      int point =  userArray.getJSONObject(i).getInt("points");
			    System.out.println(point);
			 
		        UserList.put("username", username);
		        System.out.println(UserList.get("username"));
		    }
			
			users.put("total", json.getInt("_total"));
			
			for(int i = 0; i < userArray.length(); i++) {
		//		System.out.println(UserList.get("username"+i));
			

			}
			return UserList;
		}
		
	
public static void main(String []args){
	      
	    try {
			
			
			String user = getUserListJson();
			System.out.println(user);
			Map userlistmap = getUsersLists(user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	     
	   
}
}
