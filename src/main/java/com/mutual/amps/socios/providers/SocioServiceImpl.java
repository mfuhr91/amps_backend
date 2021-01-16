package com.mutual.amps.socios.providers;

import java.util.List;

import com.mutual.amps.socios.models.Categoria;
import com.mutual.amps.socios.models.EstadoCivil;
import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.TipoDocumento;
import com.mutual.amps.socios.models.repo.ICategoriaRepo;
import com.mutual.amps.socios.models.repo.IEstadoCivilRepo;
import com.mutual.amps.socios.models.repo.ISocioRepo;
import com.mutual.amps.socios.models.repo.ITipoDocumentoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioServiceImpl implements ISocioService {

    @Autowired
    private ISocioRepo socioRepo;
    @Autowired
    private ITipoDocumentoRepo tipoDocRepo;
    @Autowired
    private ICategoriaRepo categoriaRepo;
    @Autowired
    private IEstadoCivilRepo estadoCivilRepo;

    @Override
    public List<Socio> listarTodo() {
        return this.socioRepo.findAll();
    }

    @Override
    public Socio buscarPorId(Integer id) {

        return this.socioRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Socio socio) {
        this.socioRepo.save(socio);

    }

    @Override
    public void eliminar(Integer id) {
        this.socioRepo.deleteById(id);
    }

    @Override
    public List<TipoDocumento> listarTiposDocs() {
       return this.tipoDocRepo.findAll();
    }

    @Override
    public List<EstadoCivil> listarEstadosCiviles() {
        return this.estadoCivilRepo.findAll();
    }

    @Override
    public List<Categoria> listarCategorias() {
        return this.categoriaRepo.findAll();
    }
}
