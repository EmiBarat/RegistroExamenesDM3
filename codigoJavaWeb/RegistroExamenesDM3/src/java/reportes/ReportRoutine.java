/*
 * Unas utilidades no usadas que dejo Manuel cunado me explicaba como sacar
 * reportes con parametros dinamicos
 */
package reportes;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

/**
 *
 * @author Emiliano Barat
 */
public class ReportRoutine {

    private static String DATOS_NOMBRE_PARAMETRO = "Datos";
    /**
     * Genera el pdf para el informe
     *
     * @param aux = Lista de objetos que mostraremos en el informe
     * @param map = Resto de parametros que viajan al informe
     * @throws JRException
     */
    private static JasperPrint getJasperPrint(Reporte reporte) throws JRException {
        // Definimos cual sera nuestra fuente de datos
        JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(reporte.getDatos());
        // Recuperamos el fichero fuente 
        JasperDesign jd = JRXmlLoader.load(reporte.getRuta());
        // Compilamos el informe jrxml
        JasperReport report = JasperCompileManager.compileReport(jd);
        // Rellenamos el informe con la conexion creada y sus parametros establecidos
        Map parametros = new HashMap<>(reporte.getParameteros());
        parametros.put(DATOS_NOMBRE_PARAMETRO, ds);
        JasperPrint print = JasperFillManager.fillReport(report, parametros);
        return print;

    }

    public static void exportPDF(Reporte reporte, String destino) throws JRException {
        // Exportamos el informe a formato PDF
        JasperExportManager.exportReportToPdfFile(getJasperPrint(reporte), destino);

    }

    public static void exportPDF(Reporte reporte, OutputStream outputStream) throws JRException {
        JasperExportManager.exportReportToPdfStream(getJasperPrint(reporte), outputStream);
    }

}
