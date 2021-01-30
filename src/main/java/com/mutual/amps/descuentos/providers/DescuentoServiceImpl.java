package com.mutual.amps.descuentos.providers;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.mutual.amps.convenios.models.Convenio;
import com.mutual.amps.convenios.providers.IConvenioService;
import com.mutual.amps.descuentos.models.Cuota;
import com.mutual.amps.descuentos.models.Descuento;
import com.mutual.amps.descuentos.models.Item;
import com.mutual.amps.descuentos.models.repo.ICuotaRepo;
import com.mutual.amps.descuentos.models.repo.IDescuentoRepo;
import com.mutual.amps.descuentos.models.repo.IItemRepo;
import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.repo.ISocioRepo;
import com.mutual.amps.socios.providers.ISocioService;
import com.mutual.amps.variables.models.Variable;
import com.mutual.amps.variables.models.repo.IVariableRepo;
import com.mutual.amps.variables.providers.IVariableService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javassist.runtime.Desc;

@Service
@EnableScheduling
public class DescuentoServiceImpl implements IDescuentoService {

    @Autowired
    private IDescuentoRepo descuentoRepo;

    @Autowired
    private IItemRepo itemRepo;

    @Autowired
    public ICuotaRepo cuotaRepo;

    @Autowired
    public IVariableService variableService;

    @Autowired
    public ISocioService socioService;

    @Autowired
    public IConvenioService convenioService;

    @Override
    public List<Descuento> listarPorSocio(Socio socio) {
        return this.descuentoRepo.findBySocio(socio);
    }

    @Override
    public List<Descuento> listarPorConvenio(Convenio convenio) {
        return this.descuentoRepo.findByConvenio(convenio);
    }

    @Override
    public List<Descuento> listarTodo() {
        return this.descuentoRepo.findAll();
    }

    @Override
    public Descuento buscarPorId(Long id) {
        return this.descuentoRepo.findById(id).orElse(null);
    }

    @Override
    public void guardar(Descuento descuento) {
        this.descuentoRepo.save(descuento);

    }

    @Override
    public void eliminar(Long id) {
        this.descuentoRepo.deleteById(id);
    }

    // ITEMS

    @Override
    public List<Item> listarItemsPorDescuento(Descuento descuento) {
        return this.itemRepo.findAllByDescuento(descuento);
    }

    @Override
    public Item buscarItemPorId(Long id) {
        return this.itemRepo.findById(id).orElse(null);
    }

    @Override
    public void guardarItems(Descuento descuento) {

        List<Item> items = new ArrayList<Item>();
        List<Cuota> cuotas = new ArrayList<Cuota>();
        Cuota cuota;

        Double montoCuota = descuento.getValorTotal() / descuento.getNumCuotas();
        for (int i = 1; i <= descuento.getNumCuotas(); i++) {

            Calendar cal = Calendar.getInstance();
            Variable variableDiaCierre = this.variableService.buscarPorId(4);

            Integer diaCierre = (int) Math.round(variableDiaCierre.getValor());
            cal.set(Calendar.DAY_OF_MONTH, diaCierre);
            Date fechaCierre = cal.getTime();
            cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
            Date ultimoDiaMes = cal.getTime();
            if( descuento.getFechaAlta().getTime() >= fechaCierre.getTime() && 
                descuento.getFechaAlta().getTime() <= ultimoDiaMes.getTime()) {

                cal.add(Calendar.MONTH, i + 1);
                descuento.setUltimaCuota(cal.getTime());
            } else {
                cal.add(Calendar.MONTH, i);
                descuento.setUltimaCuota(cal.getTime());

            }
          
            /* this.descuentoRepo.save(descuento); */
            
            cuota = new Cuota(montoCuota, i, cal.getTime());
            cuotas.add(cuota);
            cuota.setDescuento(descuento);
            this.cuotaRepo.save(cuota);

        }



        items = fraccionarDescuento(montoCuota, items);


        for (Item itemLista : items) {
            itemLista.setDescuento(descuento);
            this.itemRepo.save(itemLista);
        }

    }

    @Override
    public void eliminarItem(Long id) {
        this.itemRepo.deleteById(id);
    }

    private List<Item> fraccionarDescuento(Double valorDescuento, List<Item> items) {

        Variable comision = this.variableService.buscarPorId(2);
        Variable valorItem = this.variableService.buscarPorId(3);

        Double porcentaje = (comision.getValor() / 100) + 1;

        if (valorDescuento > 5000.00) {
            int nroItems = (int) Math.floor(valorDescuento / valorItem.getValor());

            Double restante = valorDescuento - (nroItems * valorItem.getValor());

            for (int i = 0; i < nroItems; i++) {
                Item nuevoItem = new Item(valorItem.getValor(), valorItem.getValor() * porcentaje);

                items.add(nuevoItem);

            }

            if (restante != 0) {
                Item itemRestante = new Item(restante, restante * porcentaje);
                items.add(itemRestante);
            }

        } else {
            Item unicoItem = new Item(valorDescuento, valorDescuento * porcentaje);
            items.add(unicoItem);

        }

        return items;

    }


    @Override
    public List<Descuento> getDescuentosByFecha(String fecha) {
        return this.descuentoRepo.getDescuentosByFechaCuota(fecha);
    }

    
    /* @Scheduled(cron="15 * * * * *") */
    /* @Scheduled(cron = "15 * * * * *") */
    @Override
    @Scheduled(cron="0 0 1 * * *", zone = "America/Argentina/Ushuaia")
    public void guardarCuotaSocial() {
        List<Socio> socios = this.socioService.listarTodo();
        Calendar cal = Calendar.getInstance(); 
        Variable variableDiaCierre = this.variableService.buscarPorId(4);

        Integer diaCierre = (int) Math.round(variableDiaCierre.getValor());

        System.out.println(cal.toString());
        if(cal.get(Calendar.MONTH) == 0) {
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 11);
            
            cal.set(Calendar.DAY_OF_MONTH, diaCierre);
            System.out.println(cal.toString());
            
        } else if(cal.get(Calendar.MONTH) == 1) {

            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 12);
            
            cal.set(Calendar.DAY_OF_MONTH, diaCierre);
            
            
        } else {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 1);
            cal.set(Calendar.DAY_OF_MONTH, diaCierre);
            System.out.println(cal.toString());
        }
    
        
        socios.forEach(socio -> {

            if( socio.getFechaAlta().before(cal.getTime())){

                this.crearDescuentoCuotaSocial(socio);

            }


        });
    }

    @Override
    public void crearDescuentoCuotaSocial(Socio socio) {

        Descuento nuevoDescuento = new Descuento();
        Item item = new Item();

        Convenio convenio = this.convenioService.buscarPorId(1);

        Cuota cuota = new Cuota();
       

        Calendar cal = Calendar.getInstance();
    
        Variable cuotaSocial = this.variableService.buscarPorId(1);


        // FECHA DE CIERRE FORMATO: Fri Jan 29 09:59:30 ART 2021
        Variable variableDiaCierre = this.variableService.buscarPorId(4);

        Integer diaCierre = (int) Math.round(variableDiaCierre.getValor());

        Date fechaHoy = new Date();

        Calendar cal2 = Calendar.getInstance();
        // ASIGNO AL CALENDARIO LA FECHA DE CIERRE
        cal2.set(Calendar.DAY_OF_MONTH, diaCierre);
        Date fechaCierre = cal2.getTime();
        // ASIGNO AL CALENDARIO EL MAYOR DIA DEL MES
        cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date ultimoDiaMes = cal2.getTime();
        
        
        if(fechaHoy.getTime() >= fechaCierre.getTime() && fechaHoy.getTime() <= ultimoDiaMes.getTime()){
            cal.add(Calendar.MONTH, 2);
        } else if(fechaHoy.getTime() < fechaCierre.getTime()) {
            cal.add(Calendar.MONTH, 1);
        }
  
        nuevoDescuento.setDescripcion("Cuota Social");
        nuevoDescuento.setValorTotal(cuotaSocial.getValor());
        nuevoDescuento.setSocio(socio);
        nuevoDescuento.setNumCuotas(1);
        nuevoDescuento.setFechaAlta(new Date());
        nuevoDescuento.setUltimaCuota(cal.getTime());
        nuevoDescuento.setConvenio(convenio);
        this.descuentoRepo.save(nuevoDescuento); 

        cuota.setFechaCuota(cal.getTime());
        cuota.setMontoCuota(cuotaSocial.getValor());
        cuota.setNumCuota(1);
        cuota.setDescuento(nuevoDescuento);
        this.cuotaRepo.save(cuota);
          
        item.setValorSubTotal(cuotaSocial.getValor());
        item.setValorTotal(cuotaSocial.getValor());
        item.setDescuento(nuevoDescuento);
        this.itemRepo.save(item);
        
       
    }

    @Override
    public Double sumarTotalRecaudado() {
       return this.descuentoRepo.sumarTotalRecaudado();
    }




    

    
}
