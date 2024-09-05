package chatbot.infra;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

public class Chatbot {
	
	private String userName = "YOUR NAME HERE";
	private String botName = "BOT NAME HERE";
	
	
	public Chatbot(String userName, String botName) {
		
		this.userName = userName;
		this.botName = botName;
		
	}
	
	// TASK THREE
	
	public String getResponse(String nowInputText) {
		
		String nowResponse="THIS IS DEFAULT RESPONSE";
		if(nowInputText.toUpperCase().equals("WE ARE")) {
			nowResponse = "Penn State!";
		} else if (nowInputText.toUpperCase().equals("HELLO")) {
	        nowResponse = "Hey there friend!";
	    } else if (nowInputText.toUpperCase().equals("HOW WAS YOUR DAY")) {
	        nowResponse = "Terrible.";
	    } else if (nowInputText.toUpperCase().equals("WHY WAS IT TERRIBLE")) {
	        nowResponse = "It just was.";
	    }
	
		
		System.out.println("--------------");
		System.out.println("User Message: "+nowInputText);
		
		System.out.println("Chatbot Response: "+nowResponse);
		return nowResponse;
		
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getBotName() {
		return botName;
	}

	public void setBotName(String botName) {
		this.botName = botName;
	}

	
	
	

}
