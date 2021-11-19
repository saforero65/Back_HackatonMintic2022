package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import javax.validation.constraints.Size;

public class LoginUsuario {
	
    @Size(min = 5, max = 200, message 
    	      = "Default")
  	private String password;
      @Size(min = 3, max = 200, message 
    	      = "Default")
  	private String nick;
      
      
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
      
      
      

}
