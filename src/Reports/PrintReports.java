package Reports;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;

public class PrintReports extends JFrame {
    public void showReportArchivedAccounts(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/ArchivedAccountsEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/ArchivedAccountsBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportAvailableEquipment(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/AvailableEquipmentEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/AvailableEquipmentBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportCommonFailures(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/CommonFailuresEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/CommonFailuresBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportCompletedExaminationsPerWorker(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/CompletedExaminationsPerWorkerEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/CompletedExaminationsPerWorkerBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportCompletedInspections(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/CompletedInspectionsEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/CompletedInspectionsBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportLastDayExaminations(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/LastDayExaminationsEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/LastDayExaminationsBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportLastMonthExaminations(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/LastMonthExaminationsEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/LastMonthExaminationsBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportLastYearExaminations(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/LastYearExaminationsEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/LastYearExaminationsBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportListOfWorker(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/ListOfWorkerEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/ListOfWorkerBA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }

    public void showReportPercentageOfPassingInspection(Connection conn) throws JRException {
        String reportSrcFile;
        if(Locale.getDefault().getLanguage().equals("en")) {
            reportSrcFile = getClass().getResource("/reports/PercentageOfPassingInspectionEN.jrxml").getFile();
        } else {
            reportSrcFile = getClass().getResource("/reports/PercentageOfPassingInspection  BA.jrxml").getFile();
        }
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("reportsDirPath", reportsDir);
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 900);
        this.setVisible(true);
    }


}
