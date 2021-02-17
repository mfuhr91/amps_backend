package com.mutual.amps.descuentos.providers;

import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mutual.amps.descuentos.models.Descuento;
import com.mutual.amps.descuentos.models.Item;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExportarDescuentosImpl implements IExportarDescuentos {

    @Autowired
    private IDescuentoService descuentoService;

	
	private Logger logger = LoggerFactory.getLogger(ExportarDescuentosImpl.class);
	
	@Override
	public File exportar(String mes) {
        System.out.println(mes);
        

        List<Descuento> descuentos = this.descuentoService.getDescuentosByFecha(mes);

        System.out.println(descuentos.size());
        
		
        
		try {
            File archivo = File.createTempFile("archivo",".prn");

            PrintWriter writer = new PrintWriter(archivo, "UTF-8");
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            Date date = new Date();
            String fecha = sdf.format(date).toString();
            DecimalFormat df = new DecimalFormat("#.00");

            if(descuentos.size() > 0) {

                descuentos.forEach(descuento -> {

                    
                    List<Item> items = descuento.getItems();
                    
                    items.forEach(item -> {
                        
                        String numDoc = descuento.getSocio().getNumDoc().toString();
                        String numCuenta = descuento.getSocio().getNumCuenta().toString();
                        
                        
                        String importe = df.format(item.getValorTotal()).replace(".", "");
                        if(importe.length() < 13) {
                            importe = "0".repeat(13 - importe.length()).concat(importe);
                        }
                        if(numDoc.length() < 8){
                            numDoc = "0".repeat(8 - numDoc.length()).concat(numDoc);
                        }
                        if(numCuenta.length() < 9){
                            numCuenta = "0".repeat(9 - numCuenta.length()).concat(numCuenta);
                        }
                        
                    
                        writer.println(numDoc.concat("03").concat("00").concat("20")
                        .concat(numCuenta).concat("0".repeat(52))
                        .concat("100").concat("000").concat(importe)
                        .concat("268").concat("0".repeat(6)).concat(fecha).concat("0".repeat(21)));
                    });
    
                    
                });
            }else {
                
                writer.println("No hay descuentos para exportar!");

            }

           
            writer.close();
         /*    tempFile.deleteOnExit();
            BufferedWriter archivo = new BufferedWriter(new FileWriter(tempFile));
            archivo.write("Esto es un fichero temporal");
            archivo.close(); */

            return archivo;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
        }	
        
		return null;
	}
}

