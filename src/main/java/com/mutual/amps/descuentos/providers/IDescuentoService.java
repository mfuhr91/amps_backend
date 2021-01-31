package com.mutual.amps.descuentos.providers;

import java.util.Date;
import java.util.List;

import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.descuentos.models.Descuento;
import com.mutual.amps.descuentos.models.Item;
import com.mutual.amps.socios.models.Socio;

import org.springframework.transaction.annotation.Transactional;

public interface IDescuentoService {
    
    @Transactional(readOnly = true)
    public List<Descuento> listarPorSocio(Socio socio);

    @Transactional(readOnly = true)
    public List<Descuento> listarPorConvenio(Convenio convenio);

    @Transactional(readOnly = true)
    public List<Descuento> listarTodo();
    
    @Transactional(readOnly = true)
    public Descuento buscarPorId(Long id);

    public void guardar(Descuento descuento);

    public void eliminar(Long id);

    @Transactional(readOnly = true)
    public List<Item> listarItemsPorDescuento(Descuento descuento);
    
    @Transactional(readOnly = true)
    public Item buscarItemPorId(Long id);

    public void guardarItems(Descuento descuento);

    public void eliminarItem(Long id);

    @Transactional(readOnly = true)
    public List<Descuento> getDescuentosByFecha(String fecha);

    public void guardarCuotaSocial();

    public void crearDescuentoCuotaSocial(Socio socio);

    @Transactional(readOnly = true)
    public Double sumarTotalRecaudado();
    
}