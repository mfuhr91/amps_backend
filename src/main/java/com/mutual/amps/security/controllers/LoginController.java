package com.mutual.amps.security.controllers;

import javax.validation.Valid;

import com.mutual.amps.security.jwt.JwtDTO;
import com.mutual.amps.security.jwt.JwtProvider;
import com.mutual.amps.usuarios.models.Usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
public class LoginController {

    @Autowired
    private AuthenticationManager authenticationManager;
    

    @Autowired
    private JwtProvider jwtProvider;


    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario){

        System.out.println(usuario.toString());
        
        Authentication authentication = authenticationManager.authenticate(
            (Authentication) new UsernamePasswordAuthenticationToken(usuario.getNombreUsuario(),
                    usuario.getContrasena()));
                    
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDTO jwtDTO = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return new ResponseEntity<JwtDTO>(jwtDTO, HttpStatus.OK);
    }

}
