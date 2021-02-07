package com.mutual.amps.descuentos.providers;

import java.text.DateFormat;
import java.text.DecimalFormat;
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

import org.hibernate.Hibernate;
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

        DecimalFormat df = new DecimalFormat("#.00");

        Variable comision = this.variableService.buscarPorId(2);
        Variable valorItem = this.variableService.buscarPorId(3);

        Double porcentaje = (comision.getValor() / 100) + 1;

        if (valorDescuento > 5000.00) {
            int nroItems = (int) Math.floor(valorDescuento / Double.parseDouble(df.format(valorItem.getValor())));

            Double restante = valorDescuento - (nroItems * Double.parseDouble(df.format(valorItem.getValor())));

            for (int i = 0; i < nroItems; i++) {
                Item nuevoItem = new Item(  Double.parseDouble(df.format(valorItem.getValor())), 
                                            Double.parseDouble(df.format(valorItem.getValor())) * porcentaje);

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

    @Override
    public Double sumarTotalRecaudado() {
       return this.descuentoRepo.sumarTotalRecaudado();
    }

    @Override
    public Descuento buscarDescuentoPorSocioPorFechaAltaPorDescripcion(Integer socioId, Date fechaAlta, String descripcion ) {
       return this.descuentoRepo.buscarDescuentoBySocioByFechaAltaByDescripcion(socioId, fechaAlta, descripcion);
    }

    
    /* @Scheduled(cron="15 * * * * *") */
    @Override
    @Scheduled(cron="0 0 1 * * *", zone = "America/Argentina/Ushuaia")
    public void guardarCuotaSocial() {

        List<Socio> socios = this.socioService.listarTodo();
        
        Variable variableDiaCierre = this.variableService.buscarPorId(4);
        
        // FECHA DE CIERRE FORMATO: Fri Jan 20 09:59:30 ART 2021
        Integer diaCierre = (int) Math.round(variableDiaCierre.getValor());
        
        Calendar cal = Calendar.getInstance();
        /* cal.set(Calendar.MONTH, 3); */ // TODO: BORRAR
        Calendar calMesAnterior = this.getFechaCierreMesAnterior(cal, diaCierre + 1);
        /* System.out.println("********************************:   " + calMesAnterior.getTime()); */
        
        socios.forEach(socio -> {
            
            if( socio.getFechaAlta().before(calMesAnterior.getTime()) && !socio.getBaja()){
            
                
                this.crearDescuentos(socio);
            }
        });
    }

    @Override
    public void crearDescuentos(Socio socio) {

        DecimalFormat df = new DecimalFormat("#.00");
        
        Date fechaHoy = new Date();

        Variable variableDiaCierre = this.variableService.buscarPorId(4);

        // FECHA DE CIERRE FORMATO: Fri Jan 20 09:59:30 ART 2021
        Integer diaCierre = (int) Math.round(variableDiaCierre.getValor());

            
        Convenio convenio = this.convenioService.buscarPorId(1);

        Calendar cal = Calendar.getInstance();

        cal = this.getFechaCierreMesAnterior(cal, diaCierre + 1); 
        

        

        Calendar cal2 = Calendar.getInstance();
        // ASIGNO AL CALENDARIO LA FECHA DE CIERRE
        cal2.set(Calendar.DAY_OF_MONTH, diaCierre);
        Date fechaCierre = cal2.getTime();
        // ASIGNO AL CALENDARIO EL MAYOR DIA DEL MES
        cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMaximum(Calendar.DAY_OF_MONTH));
        Date ultimoDiaMes = cal2.getTime();
        
        
        if(fechaHoy.getTime() >= fechaCierre.getTime() && fechaHoy.getTime() <= ultimoDiaMes.getTime()){
            cal2.add(Calendar.MONTH, 2);
            cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMinimum(Calendar.DAY_OF_MONTH));
        } else if(fechaHoy.getTime() < fechaCierre.getTime()) {
            cal2.add(Calendar.MONTH, 1);
            cal2.set(Calendar.DAY_OF_MONTH, cal2.getActualMinimum(Calendar.DAY_OF_MONTH));
        }

    
        Descuento descuentoBuscado = this.buscarDescuentoPorSocioPorFechaAltaPorDescripcion(socio.getId(), cal.getTime() , "Cuota social");
        
        
        if(descuentoBuscado == null){

            Variable cuotaSocial = this.variableService.buscarPorId(1);
    
            Descuento descuentoCuotaSocial = new Descuento();
            Cuota cuotaCuotaSocial = new Cuota();
            Item itemCuotaSocial = new Item();
    
            this.crearUnDescuento("Cuota social", Double.parseDouble(df.format(cuotaSocial.getValor())), socio, cal2, convenio, descuentoCuotaSocial, cuotaCuotaSocial, itemCuotaSocial);
            

        }

        descuentoBuscado = this.buscarDescuentoPorSocioPorFechaAltaPorDescripcion(socio.getId(), cal.getTime() , "Cuota deportiva");
        Double cuotaGenerica;
        if(descuentoBuscado == null){
            if(socio.getCuotaDeporte() != null){
                cuotaGenerica = Double.parseDouble(df.format(socio.getCuotaDeporte()));
                Descuento descuentoDeporte = new Descuento();
                Cuota cuotaDeporte = new Cuota();
                Item itemDeporte = new Item();
                this.crearUnDescuento("Cuota deportiva", cuotaGenerica, socio, cal2, convenio, descuentoDeporte, cuotaDeporte, itemDeporte);
            }
        } else {
            cuotaGenerica = Double.parseDouble(df.format(socio.getCuotaDeporte()));
            Cuota cuota = descuentoBuscado.getCuotas().get(0);
            cuota.setMontoCuota(cuotaGenerica);
            
            this.cuotaRepo.save(cuota);

            Item item = descuentoBuscado.getItems().get(0);
            item.setValorSubTotal(cuotaGenerica);
            item.setValorTotal(cuotaGenerica);

            this.itemRepo.save(item);

            descuentoBuscado.setValorCuota(cuotaGenerica);
            descuentoBuscado.setValorTotal(cuotaGenerica);

            this.descuentoRepo.save(descuentoBuscado);

            
        }

        descuentoBuscado = this.buscarDescuentoPorSocioPorFechaAltaPorDescripcion(socio.getId(), cal.getTime() , "Seguro vida flia");
        if(descuentoBuscado == null){
            if(socio.getSeguroVida() != null){
                cuotaGenerica = Double.parseDouble(df.format(socio.getSeguroVida()));
                Descuento descuentoSeguro = new Descuento();
                Cuota cuotaSeguro = new Cuota();
                Item itemSeguro = new Item();
                this.crearUnDescuento("Seguro vida flia", cuotaGenerica, socio, cal2, convenio, descuentoSeguro, cuotaSeguro, itemSeguro);
                
            }
        } else {
            cuotaGenerica = Double.parseDouble(df.format(socio.getSeguroVida()));
            Cuota cuota = descuentoBuscado.getCuotas().get(0);
            cuota.setMontoCuota(cuotaGenerica);
            
            this.cuotaRepo.save(cuota);

            Item item = descuentoBuscado.getItems().get(0);
            item.setValorSubTotal(cuotaGenerica);
            item.setValorTotal(cuotaGenerica);

            this.itemRepo.save(item);
            
            descuentoBuscado.setValorCuota(cuotaGenerica);
            descuentoBuscado.setValorTotal(cuotaGenerica);

            this.descuentoRepo.save(descuentoBuscado);
        }
    }

    private void crearUnDescuento(String descripcion, Double montoDescuento, Socio socio, Calendar cal, Convenio convenio, Descuento descuento, Cuota cuota, Item item){

       /*  System.out.println(montoDescuento); */
        
        descuento.setDescripcion(descripcion);
        descuento.setValorCuota(montoDescuento);
        descuento.setValorTotal(montoDescuento);
        descuento.setSocio(socio);
        descuento.setNumCuotas(1);
        descuento.setFechaAlta(new Date());
        descuento.setUltimaCuota(cal.getTime());
        descuento.setConvenio(convenio);
        this.descuentoRepo.save(descuento);
       
         
        item.setValorSubTotal(montoDescuento);
        item.setValorTotal(montoDescuento);
        item.setDescuento(descuento);
        this.itemRepo.save(item);

        cuota.setFechaCuota(cal.getTime());
        cuota.setMontoCuota(montoDescuento);
        cuota.setNumCuota(1);
        
        cuota.setDescuento(descuento);
        this.cuotaRepo.save(cuota);
        

    }

   

    private Calendar getFechaCierreMesAnterior(Calendar cal, int dia){  
              
       /*  System.out.println("###### HOY:  " + cal.getTime()); */

        // 0 = ENERO - 11 = DICIEMBRE
        if(cal.get(Calendar.MONTH) == 0) { // OK ->  1 mes y 5 dias antes del 1ro de cada mes
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 10); // MES DE ENERO + 11 meses = NOVIEMBRE
            
            cal.set(Calendar.DAY_OF_MONTH, dia);
           /*  System.out.println("###### UN AÑO Y DOS MES ANTES:  " + cal.getTime()); */
            
        } else if(cal.get(Calendar.MONTH) == 1) {
            
            cal.set(Calendar.YEAR, cal.get(Calendar.YEAR) - 1);
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) + 10); // MES DE FEBRERO + 10 meses = DICIEMBRE
            
            cal.set(Calendar.DAY_OF_MONTH, dia); 
            
            /* System.out.println("###### UN AÑO Y UN MES ANTES:  " + cal.getTime()); */
            
            
        } else {
            cal.set(Calendar.MONTH, cal.get(Calendar.MONTH) - 2);
            cal.set(Calendar.DAY_OF_MONTH, dia); 
            /* System.out.println("###### UN MES ANTES:  " + cal.getTime()); */
        }
        return cal;
    }





    

    
}
