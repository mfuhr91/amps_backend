package com.mutual.amps.descuentos.providers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.mutual.amps.descuentos.models.Descuento;
import com.mutual.amps.descuentos.models.Item;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
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
                        
                        
                        String importe = df.format(item.getValor()).replace(".", "");
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

            return archivo;
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
        }	
        
		return null;
	}

    @Override
    public ByteArrayInputStream exportarPorMes(String mes) throws IOException {

        String[] arr = mes.split("-");

        String mesTitulo = String.join("-", arr[1], arr[0]);

        HSSFRichTextString titulo = new HSSFRichTextString("Descuentos del " + mesTitulo);

        List<HSSFRichTextString> columnas = new ArrayList<HSSFRichTextString>();

        DecimalFormat df = new DecimalFormat("#.00");

        String[] columnasStr = {"Descripción","Documento","Nombre","CUIT","Nombre","Fecha","Monto"};
        
        for (String col : columnasStr) {
            columnas.add(new HSSFRichTextString(col));
        }
        
        List<HSSFRichTextString> grupos = new ArrayList<HSSFRichTextString>();
        String[] gruposStr = {"Socio", "Convenio"};

        for (String gr : gruposStr) {
            grupos.add(new HSSFRichTextString(gr));
        }

        Workbook wb = new HSSFWorkbook();
        HSSFFont fontTitulo = (HSSFFont) wb.createFont();
        HSSFFont font = (HSSFFont) wb.createFont();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        HashMap<String, Object> properties = new HashMap<String, Object>();  
        CreationHelper createHelper = wb.getCreationHelper(); 
        CellStyle styleLeft = wb.createCellStyle();
        CellStyle styleCenter = wb.createCellStyle();
        CellStyle styleRight = wb.createCellStyle();
        styleCenter.setAlignment(HorizontalAlignment.CENTER); 
        styleLeft.setAlignment(HorizontalAlignment.LEFT); 
        styleRight.setAlignment(HorizontalAlignment.RIGHT); 

        Sheet sheet = wb.createSheet("descuentos");
        sheet.setDefaultColumnWidth(13);
        Row row;
        Cell cell;
        
        // TITULO
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,6));
        row = sheet.createRow(0);
        cell = row.createCell(0);
        fontTitulo.setBold(true);
        fontTitulo.setFontHeightInPoints((short) 14);
        titulo.applyFont(fontTitulo);
        cell.setCellValue(titulo);
        cell.setCellStyle(styleCenter);

        // FIN TITULO

        // COLOCA BORDES A COLUMNAS
        properties.put(CellUtil.BORDER_TOP, BorderStyle.THIN);  
        properties.put(CellUtil.BORDER_BOTTOM, BorderStyle.THIN);  
        properties.put(CellUtil.BORDER_LEFT, BorderStyle.THIN);  
        properties.put(CellUtil.BORDER_RIGHT, BorderStyle.THIN); 

        // FILA GRUPOS - 3RA FILA
        font.setBold(true);
        font.setFontHeightInPoints((short) 11);
        row = sheet.createRow(2);

        // PRIMERA CELDA
        cell = row.createCell(0);
        CellUtil.setCellStyleProperties(cell, properties);
        sheet.addMergedRegion(new CellRangeAddress(2,2,1,2));
        // FIN PRIMERA CELDA

        cell = row.createCell(1);
        grupos.get(0).applyFont(font);
        cell.setCellValue(grupos.get(0));
        cell.setCellStyle(styleCenter);
        CellUtil.setCellStyleProperties(cell, properties);
        
        cell = row.createCell(2);
        CellUtil.setCellStyleProperties(cell, properties);

        sheet.addMergedRegion(new CellRangeAddress(2,2,3,4));
        cell = row.createCell(3);
        grupos.get(1).applyFont(font);
        cell.setCellValue(grupos.get(1));
        cell.setCellStyle(styleCenter);
        CellUtil.setCellStyleProperties(cell, properties);

        cell = row.createCell(4);
        CellUtil.setCellStyleProperties(cell, properties);
        cell = row.createCell(5);
        CellUtil.setCellStyleProperties(cell, properties);
        cell = row.createCell(6);
        CellUtil.setCellStyleProperties(cell, properties);

        // FIN FILA GRUPOS

        // FILA COLUMNAS
        row = sheet.createRow(3);
        
        for (int i = 0; i < columnas.size(); i++) {
            cell = row.createCell(i);
            columnas.get(i).applyFont(font);
            cell.setCellValue(columnas.get(i));
            CellUtil.setCellStyleProperties(cell, properties);  
        }

        // FIN FILAS COLUMNAS


        // DATOS

        List<Descuento> descuentos = this.descuentoService.getDescuentosByFecha(mes);
        int initRow = 4;
        Double montoTotal = 0.00;
        if(descuentos.size() > 0) {
            
    
            for ( Descuento descuento : descuentos) {

                List<Item> items = descuento.getItems();

                for ( Item item: items) {
                    
                    row = sheet.createRow(initRow);
                    cell = row.createCell(0);
                    cell.setCellValue(descuento.getDescripcion());
                    cell.setCellStyle(styleLeft);
                    CellUtil.setCellStyleProperties(cell, properties);  
                    
                    cell = row.createCell(1);
                    cell.setCellValue(descuento.getSocio().getNumDoc());
                    CellUtil.setCellStyleProperties(cell, properties);  
                    
                    cell = row.createCell(2);
                    cell.setCellValue(descuento.getSocio().getNombre() + " " + descuento.getSocio().getApellido());
                    cell.setCellStyle(styleLeft);
                    CellUtil.setCellStyleProperties(cell, properties);  
                    
                    cell = row.createCell(3);
                    cell.setCellValue(descuento.getConvenio().getCuit());
                    CellUtil.setCellStyleProperties(cell, properties); 
        
                    cell = row.createCell(4);
                    cell.setCellValue(descuento.getConvenio().getNombre());
                    cell.setCellStyle(styleLeft);  
                    CellUtil.setCellStyleProperties(cell, properties);
                    
                    CellStyle cellStyle = wb.createCellStyle();
                    cellStyle.setDataFormat(  
                        createHelper.createDataFormat().getFormat("h:mm d/m/yyyy"));
                    cell = row.createCell(5);
                    cell.setCellValue(descuento.getFechaAlta());
                    cell.setCellStyle(cellStyle);
                    CellUtil.setCellStyleProperties(cell, properties);  
                    
                    /* cell = row.createCell(6);
                    cell.setCellValue(descuento.getNumCuotas());
                    CellUtil.setCellStyleProperties(cell, properties);   */
                    
                    String importe = df.format(item.getValor());

                    cell = row.createCell(6);
                    cell.setCellValue(importe);
                    cell.setCellStyle(styleRight);  
                    CellUtil.setCellStyleProperties(cell, properties);
                
                    initRow++;

                    montoTotal = montoTotal + item.getValor();
                }
            }
        }

        // TOTAL
        row = sheet.createRow(initRow + 1);
        cell = row.createCell(5);
        HSSFRichTextString total = new HSSFRichTextString("Total");
        total.applyFont(font);
        cell.setCellValue(total);
        CellUtil.setCellStyleProperties(cell, properties);

        cell = row.createCell(6);
        HSSFRichTextString celdaMontoTotal = new HSSFRichTextString(montoTotal.toString());
        celdaMontoTotal.applyFont(font);
        cell.setCellValue(celdaMontoTotal);
        cell.setCellStyle(styleRight);
        CellUtil.setCellStyleProperties(cell, properties);

        // FIN TOTAL 


        wb.write(stream);
        wb.close();

        return new ByteArrayInputStream(stream.toByteArray());




        /* 
        
        private String descripcion;
    
        private Date fechaAlta;

        private Date ultimaCuota;

        private Integer numCuotas;

        
        private Double valorCuota;

        private Double valorSubTotal;

        private Double valorTotal;

        private Socio socio;

        private Convenio convenio;

        private List<Cuota> cuotas;

        private List<Item> items;
        
        */
    }
}

