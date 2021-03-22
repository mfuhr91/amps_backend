package com.mutual.amps.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.mutual.amps.usuarios.models.Usuario;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails {

    
    private static final long serialVersionUID = 2579066123506052231L;
    private Integer id;
    private String nombreUsuario;
    private String contrasena;
    private Collection<? extends GrantedAuthority> authorities;

    public UsuarioPrincipal(Integer id, String nombreUsuario, String contrasena,
            List<GrantedAuthority> authorities) {
                this.id = id;
                this.nombreUsuario = nombreUsuario;
                this.contrasena = contrasena;
                this.authorities = authorities;
    }
   
    public static UsuarioPrincipal build(Usuario usuario) {

        
            GrantedAuthority authority = new SimpleGrantedAuthority(usuario.getRol().getNombreRol()); 
            
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    
            authorities.add(authority);

            return new UsuarioPrincipal(usuario.getId(), usuario.getNombreUsuario(), usuario.getContrasena(), authorities);
        



    }

    public Integer getId() {
        return id;
    }

    @Override
    public String getPassword() {
        return contrasena;
    }

    @Override
    public String getUsername() {
       return nombreUsuario;
    }

    @Override
    public boolean isAccountNonExpired() {
       
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
       return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    
    
}
