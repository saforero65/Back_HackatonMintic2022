package com.misiontic2022.hackaton.webreciclaje.webreciclaje.model;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UsuarioSecurity  implements UserDetails{
	
	

    @Size(min = 7, max = 200, message 
    	      = "Default")
	private String nombrecompleto;
	@Min(value = 3, message = "Default")
	@Max(value= 30)
	private String password;
	
	@Min(value = 3, message = "Default")
	@Max(value= 30)
	@NotBlank
	private String nick;
	@NotBlank
	@Email
	private String email;
	@NotBlank
	private String tipo;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public static UsuarioSecurity build(Usuario  usuario){
        List<GrantedAuthority> authorities =
        		
        		usuario.getRoles().stream().map(rol -> new SimpleGrantedAuthority(rol
                )).collect(Collectors.toList());
        return new UsuarioSecurity(usuario.getNombrecompleto(), usuario.getPassword(),usuario.getNick(), usuario.getEmail(), usuario.getTipo(), authorities);
    }
	
	
	public UsuarioSecurity(@Size(min = 7, max = 200, message = "Default") String nombrecompleto,
			@Min(value = 7, message = "Default") @Max(30) @NotBlank String password,@Min(value = 7, message = "Default") @Max(30) @NotBlank String nick, @NotBlank @Email String email,
			@NotBlank String tipo, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.nombrecompleto = nombrecompleto;
		this.password = password;
		this.nick = nick;
		this.email = email;
		this.tipo = tipo;
		this.authorities = authorities;
	}
	
	


	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}
	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return nick;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}


	public String getNombrecompleto() {
		return nombrecompleto;
	}


	public void setNombrecompleto(String nombrecompleto) {
		this.nombrecompleto = nombrecompleto;
	}


	public String getNick() {
		return nick;
	}


	public void setNick(String nick) {
		this.nick = nick;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	

}
