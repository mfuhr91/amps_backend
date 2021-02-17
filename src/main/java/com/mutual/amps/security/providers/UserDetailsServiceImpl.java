package com.mutual.amps.security.providers;

import com.mutual.amps.security.UsuarioPrincipal;
import com.mutual.amps.usuarios.models.Usuario;
import com.mutual.amps.usuarios.providers.IUsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    IUsuarioService usuarioService;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = usuarioService.buscarPorNombreUsuario(nombreUsuario).get(0);
        return UsuarioPrincipal.build(usuario);
    }
    
}
