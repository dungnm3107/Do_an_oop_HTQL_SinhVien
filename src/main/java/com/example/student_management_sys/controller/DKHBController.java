package com.example.student_management_sys.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

public class DKHBController {

    @FXML
    private Button openPdfButton;



    @FXML
    private void openPdfButtonClicked(ActionEvent event) {
        String pdfFilePath = getClass().getResource("/com/example/student_management_sys/view/HocBong.pdf").getPath();

        File pdfFile = new File(pdfFilePath);
        if (pdfFile.exists()) {
            openPDF(pdfFile);
        } else {
            System.out.println("File not found: " + pdfFilePath);
        }
    }
    private void openPDF(File file) {
        try {
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
