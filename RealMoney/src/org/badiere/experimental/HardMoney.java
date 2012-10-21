package org.badiere.experimental;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

public class HardMoney {

	// Minimize the use of external libs for now
	private URLConnection conn;
	//private URL goldUrl;
	private InputStream is;
	
	// Yahoo gold spot query
	private String goldUrlString = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22XAUUSD%3DX%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=cbfunc";
	
	// Yahoo silver spot query
	private String silverUrlString = "http://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20yahoo.finance.quotes%20where%20symbol%20in%20(%22XAGUSD%3DX%22)&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=cbfunc";

	
	public HardMoney() throws MalformedURLException{
		
		
	}
	
	
	public String getGoldSpot(){

		String spot = null;
		
		try {
			spot = parseJson(goldUrlString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spot;
		
	}
	
	public String getSilverSpot(){

		String spot = null;
		
		try {
			spot = parseJson(silverUrlString);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return spot;
		
	}
	
	public String getDJIA(){

		String djia = null;
		
		try {
			
			djia = parseJson(silverUrlString);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return djia;
		
	}
	
	
	public String getDownInGold(){
		
		String gold = getGoldSpot();
		
		
		return "";
	}
	
	
	private String parseJson(String urlString) throws JsonParseException, IOException{

		URL url = new URL(urlString);
			
		conn = url.openConnection();
		is = conn.getInputStream();
		
		JsonFactory f = new JsonFactory();
		JsonParser jp;

		// make it a string
		String isStr = convertStreamToString(is);
		
		int start = isStr.indexOf("results");
		String results = isStr.substring(start + 9);
		
		jp = f.createJsonParser(results);

		jp.nextToken();
		
		String value = null;
		while (jp.nextToken() != JsonToken.END_OBJECT) {
			jp.nextToken();
			String fieldname = jp.getCurrentName();
			if (fieldname.equals("LastTradePriceOnly")){
				value = jp.getText();
				break;
				
			}
		} 
		
		return value;
	}
	
	/**
	 * Input stream needs to be cleaned up so make it a string that's easier
	 * parse.
	 * @param is
	 * @return
	 */
	String convertStreamToString(InputStream is) {
	    try {
	        return new java.util.Scanner(is).useDelimiter("\\A").next();
	    } catch (java.util.NoSuchElementException e) {
	        return "";
	    }
	}	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
