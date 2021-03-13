package com.mutual.amps.descuentos.providers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

import org.springframework.transaction.annotation.Transactional;


public interface IExportarDescuentos {
    
    @Transactional(readOnly = true)
    public File exportar(String mes);

    @Transactional(readOnly = true)
    public ByteArrayInputStream exportarPorMes(String mes) throws IOException;
}
