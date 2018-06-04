package util;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;

import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.oasis.JROdtExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOdtReportConfiguration;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;
import persistence.Conexao;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * jasperreports-5.0.1.jar
 * iText-2.1.7.jar (needed to generate PDF)
 * jfreechart-1.0.12.jar (needed to graphics and charts)
 * jcommon-1.0.15.jar (needed to graphics and charts)
 * commons-beanutils-1.8.2.jar
 * commons-collections-3.2.1.jar
 * commons-digester-2.1.jar
 * commons-logging-1.1.jar
 * 
 * @author rafael
 */
public class RelatorioUtil {
    public static void exibirRelatorio(HashMap<String, Object> params, String nomeArquivoSemExtensão) throws Exception {
        try {
            
            //Gerar Relatório
            JasperPrint print = JasperFillManager.fillReport(RelatorioUtil.class.getResourceAsStream("/relatorios/"+nomeArquivoSemExtensão+".jasper"), params, Conexao.getConnection());
            
            // Código para exibir o relatório.
            JasperViewer.viewReport(print, false);
            
        } catch (JRException ex) {
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
    
    public static void gerarPDF(HashMap<String, Object> params, String nomeArquivoSemExtensão) throws Exception {
        try {
            JasperPrint print = JasperFillManager.fillReport(RelatorioUtil.class.getResourceAsStream("/relatorios/"+nomeArquivoSemExtensão+".jasper"), params, Conexao.getConnection());
            
            /* Código para gerar pdf. */
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(print));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput( nomeArquivoSemExtensão + ".pdf" ));
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            exporter.setConfiguration(configuration);
            
            exporter.exportReport();
            
        } catch (JRException ex) {
            Logger.getLogger(RelatorioUtil.class.getName()).log(Level.SEVERE, null, ex);
            throw ex;
        }
    }
}
