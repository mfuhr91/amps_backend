package com.mutual.amps.categorias.providers;

import java.util.List;

import com.mutual.amps.categorias.models.Categoria;
import com.mutual.amps.categorias.models.repo.ICategoriaRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoriaServiceImpl implements ICategoriaService {

    @Autowired
    private ICategoriaRepo categoriaRepo;

    @Override
    public List<Categoria> listarTodo() {
        return this.categoriaRepo.findAll();
    }

    @Override
    public Categoria buscarPorId(Integer id) {
       return this.categoriaRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Categoria categoria) {
        this.categoriaRepo.save(categoria);

    }

    @Override
    public void eliminar(Integer id) {
        this.categoriaRepo.deleteById(id);
    }

}
