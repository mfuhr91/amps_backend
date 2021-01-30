package com.mutual.amps.socios.providers;

import java.util.List;

import com.mutual.amps.descuentos.providers.IDescuentoService;
import com.mutual.amps.socios.models.EstadoCivil;

import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.Tipo;
import com.mutual.amps.socios.models.TipoDocumento;
import com.mutual.amps.socios.models.repo.IEstadoCivilRepo;

import com.mutual.amps.socios.models.repo.ISocioRepo;
import com.mutual.amps.socios.models.repo.ITipoDocumentoRepo;
import com.mutual.amps.socios.models.repo.ITipoRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SocioServiceImpl implements ISocioService {

    @Autowired
    private ISocioRepo socioRepo;
    @Autowired
    private ITipoDocumentoRepo tipoDocRepo;
    @Autowired
    private IEstadoCivilRepo estadoCivilRepo;
    @Autowired
    private ITipoRepo tipoRepo;

    @Autowired
    private IDescuentoService descuentosService;


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

        this.descuentosService.crearDescuentoCuotaSocial(socio);

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
    public List<Tipo> listarTipos() {
        return this.tipoRepo.findAll();
    }

    @Override
    public List<Socio> buscar(String param) {
        return this.socioRepo.findByParam(param);
    }

    @Override
    public Integer contarSocios() {
        return this.socioRepo.contarSocios();
    }

    
}
