package com.mutual.amps.socios.providers;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


import com.mutual.amps.socios.models.EstadoCivil;

import com.mutual.amps.socios.models.Socio;
import com.mutual.amps.socios.models.Tipo;
import com.mutual.amps.socios.models.TipoDocumento;
import com.mutual.amps.socios.models.repo.IEstadoCivilRepo;

import com.mutual.amps.socios.models.repo.ISocioRepo;
import com.mutual.amps.socios.models.repo.ITipoDocumentoRepo;
import com.mutual.amps.socios.models.repo.ITipoRepo;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
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


    @Override
    public List<Socio> listarTodo() {
        return this.socioRepo.findAll();
    }

    @Override
    public List<Socio> listarTodosNoBaja() {
       return this.socioRepo.findAllNoBaja();
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

    @Override
    public Socio buscarPorDoc(Long doc) {
       return this.socioRepo.findByNumDoc(doc);
    }

    @Override
    public ByteArrayInputStream exportarTodos() throws IOException {
        
        HSSFRichTextString titulo = new HSSFRichTextString("Listado de socios");

        List<HSSFRichTextString> columnas = new ArrayList<HSSFRichTextString>();

        DecimalFormat df = new DecimalFormat("#.00");

        String[] columnasStr = {"Documento","Nombre","CUIL","Dirección","Localidad",
                                "Fecha de Nacimiento","Teléfono","Correo","Estado Civil",
                                "Legajo","Número de Cuenta","Clase","Fecha de Alta","Fecha de ingreso laboral",
                                "Fecha de baja","Extranjero","Habilitado","Usuario","Cuota Social","Cuota Deportiva","Seguro Vida Fliar"};
        
        for (String col : columnasStr) {
            columnas.add(new HSSFRichTextString(col));
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

        Sheet sheet = wb.createSheet("socios");
        
        Row row;
        Cell cell;
        
        // TITULO
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,20));
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

        font.setBold(true);
        font.setFontHeightInPoints((short) 11);

        // FILA COLUMNAS
        row = sheet.createRow(2);
        
        for (int i = 0; i < columnas.size(); i++) {
            cell = row.createCell(i);
            columnas.get(i).applyFont(font);
            cell.setCellValue(columnas.get(i));
            cell.setCellStyle(styleCenter);
            CellUtil.setCellStyleProperties(cell, properties);  
        }

        // FIN FILAS COLUMNAS


        // DATOS

        List<Socio> socios = this.listarTodo();
        int initRow = 3;

        if(socios.size() > 0) {
            
    
            for ( Socio socio : socios) {

            
                    
                row = sheet.createRow(initRow);
                cell = row.createCell(0);
                cell.setCellValue(socio.getNumDoc());
                cell.setCellStyle(styleCenter);
                CellUtil.setCellStyleProperties(cell, properties);  
                
                cell = row.createCell(1);
                cell.setCellValue(socio.getNombre() + " " + socio.getApellido());
                cell.setCellStyle(styleLeft);
                CellUtil.setCellStyleProperties(cell, properties);  
                
                cell = row.createCell(2);
                cell.setCellValue(socio.getCuil());
                cell.setCellStyle(styleCenter);
                CellUtil.setCellStyleProperties(cell, properties);  
                
                cell = row.createCell(3);
                cell.setCellValue(socio.getDireccion());
                cell.setCellStyle(styleLeft);  
                CellUtil.setCellStyleProperties(cell, properties); 
    
                cell = row.createCell(4);
                cell.setCellValue(socio.getLocalidad().getNombre());
                cell.setCellStyle(styleLeft);  
                CellUtil.setCellStyleProperties(cell, properties);
                
                CellStyle cellStyle = wb.createCellStyle();
                cellStyle.setDataFormat(  
                    createHelper.createDataFormat().getFormat("d/m/yyyy"));
                cellStyle.setAlignment(HorizontalAlignment.CENTER);
                cell = row.createCell(5);
                cell.setCellValue(socio.getFechaNacimiento());
                cell.setCellStyle(cellStyle);
                CellUtil.setCellStyleProperties(cell, properties);  

                cell = row.createCell(6);
                cell.setCellValue(socio.getTelefono());
                cell.setCellStyle(styleCenter);  
                CellUtil.setCellStyleProperties(cell, properties);

                cell = row.createCell(7);
                cell.setCellValue(socio.getCorreo());
                cell.setCellStyle(styleLeft);  
                CellUtil.setCellStyleProperties(cell, properties);


                cell = row.createCell(8);
                cell.setCellValue(socio.getEstadoCivil().getNombre());
                cell.setCellStyle(styleLeft);  
                CellUtil.setCellStyleProperties(cell, properties);

                cell = row.createCell(9);
                cell.setCellValue(socio.getLegajo());
                cell.setCellStyle(styleCenter);  
                CellUtil.setCellStyleProperties(cell, properties);

                cell = row.createCell(10);
                cell.setCellValue(socio.getNumCuenta());
                cell.setCellStyle(styleCenter);  
                CellUtil.setCellStyleProperties(cell, properties);

                cell = row.createCell(11);
                cell.setCellValue(socio.getTipo().getNombre());
                cell.setCellStyle(styleLeft);  
                CellUtil.setCellStyleProperties(cell, properties);

                cell = row.createCell(12);
                cell.setCellValue(socio.getFechaAlta());
                cell.setCellStyle(cellStyle);
                CellUtil.setCellStyleProperties(cell, properties);  
                
                cell = row.createCell(13);
                cell.setCellValue(socio.getFechaIngresoLaboral());
                cell.setCellStyle(cellStyle);
                CellUtil.setCellStyleProperties(cell, properties);  

                cell = row.createCell(14);
                cell.setCellValue(socio.getFechaBaja());
                cell.setCellStyle(cellStyle);
                CellUtil.setCellStyleProperties(cell, properties);  

                cell = row.createCell(15);
                if(socio.getExtranjero()){
                    cell.setCellValue("SI");
                } else {
                    cell.setCellValue("NO");
                }
                cell.setCellStyle(styleCenter);  
                CellUtil.setCellStyleProperties(cell, properties);
                
                cell = row.createCell(16);
                if(socio.getHabilitado()){
                    cell.setCellValue("SI");
                } else {
                    cell.setCellValue("NO");
                }
                cell.setCellStyle(styleCenter);  
                CellUtil.setCellStyleProperties(cell, properties);

                cell = row.createCell(17);
                cell.setCellValue(socio.getUsuario().getNombreUsuario());
                cell.setCellStyle(styleLeft);  
                CellUtil.setCellStyleProperties(cell, properties);
                
                String importeCuotaSocial = df.format(socio.getCuotaSocial().getValor());
                cell = row.createCell(18);
                cell.setCellValue(importeCuotaSocial);
                cell.setCellStyle(styleRight);  
                CellUtil.setCellStyleProperties(cell, properties);

                String importeCuotaDeporte = "0.00";
                if(socio.getCuotaDeporte() != null){
                    importeCuotaDeporte = df.format(socio.getCuotaDeporte());
                }
                cell = row.createCell(19);
                cell.setCellValue(importeCuotaDeporte);
                cell.setCellStyle(styleRight);  
                CellUtil.setCellStyleProperties(cell, properties);
                
                String importeCuotaSeguroVida = "0.00";
                if(socio.getSeguroVida() != null){
                    importeCuotaSeguroVida = df.format(socio.getSeguroVida());
                }
                cell = row.createCell(20);
                cell.setCellValue(importeCuotaSeguroVida);
                cell.setCellStyle(styleRight);  
                CellUtil.setCellStyleProperties(cell, properties);
            
                initRow++;
     
            }
        }

        int filas = sheet.getPhysicalNumberOfRows();

        for (int i = 1; i < filas; i++) {
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                cell = cellIterator.next();
                int columnIndex = cell.getColumnIndex();
                sheet.autoSizeColumn(columnIndex);
            }
        }


        wb.write(stream);
        wb.close();

        return new ByteArrayInputStream(stream.toByteArray());

    }

   


    
}
