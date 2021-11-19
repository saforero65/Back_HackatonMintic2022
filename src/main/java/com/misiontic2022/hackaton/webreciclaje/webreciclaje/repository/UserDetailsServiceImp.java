package com.misiontic2022.hackaton.webreciclaje.webreciclaje.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.Usuario;
import com.misiontic2022.hackaton.webreciclaje.webreciclaje.model.UsuarioSecurity;


@Service
@Transactional
public class UserDetailsServiceImp implements UserDetailsService{
@Autowired
UsuarioRepository usuarioRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByNick(username).get(0);
		
		return UsuarioSecurity.build(usuario);
	}

}
