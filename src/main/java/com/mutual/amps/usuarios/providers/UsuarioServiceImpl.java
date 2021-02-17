package com.mutual.amps.usuarios.providers;

import java.util.List;

import com.mutual.amps.usuarios.models.Usuario;
import com.mutual.amps.usuarios.models.repo.IUsuarioRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private IUsuarioRepo usuarioRepo;

    @Override
    public List<Usuario> listarTodo() {
        return this.usuarioRepo.findAll();
    }

    @Override
    public Usuario buscarPorId(Integer id) {
        
        return this.usuarioRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Usuario usuario) {
        
        usuario.setContrasena(this.passwordEncoder.encode(usuario.getContrasena()));
        

        this.usuarioRepo.save(usuario);

    }

    @Override
    public void eliminar(Integer id) {
        this.usuarioRepo.deleteById(id);
    }

    @Override
    public List<Usuario> buscar(String param) {
      return this.usuarioRepo.findByParam(param);
    }

    @Override
    public List<Usuario> buscarPorNombreUsuario(String nombreUsuario) {
        return usuarioRepo.findByNombreUsuario(nombreUsuario);
    }
}
