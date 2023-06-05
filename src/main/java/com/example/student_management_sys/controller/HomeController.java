package com.example.student_management_sys.controller;

import com.example.student_management_sys.model.DatabaseModel;
import com.example.student_management_sys.model.KetQuaHocTap;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class HomeController {

    private String username;
    @FXML
    private MenuItem exitButton;
    @FXML
    private MenuButton buttonAccount;
    @FXML
    private MenuButton menuButton;
    @FXML
    private Button btn_studentInfo;
    @FXML
    private Button btn_learningOutcome;
    @FXML
    private Button btn_schedule;
    @FXML
    private Button btn_courseEnrolment;
    @FXML
    private Button btn_reExamine;
    @FXML
    private BarChart barChart_KQHT;
    @FXML
    private CategoryAxis xAxis_MH;
    @FXML
    private NumberAxis yAxis_Diem;

    private DatabaseModel databaseModel;

    public void setAccountName(String accountName) {
        buttonAccount.setText(accountName);
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @FXML
    public void initialize() {
    }

    public void initBarChart() {
        barChart_KQHT.setLegendVisible(false);
        barChart_KQHT.setAnimated(false);
        xAxis_MH.setLabel("Môn học");
        xAxis_MH.setAnimated(false);
        xAxis_MH.setTickLabelsVisible(false);
        yAxis_Diem.setAutoRanging(false);
        yAxis_Diem.setUpperBound(10);

        databaseModel = new DatabaseModel();
        List<KetQuaHocTap> currentUserKQHTList = null;
        try {
            currentUserKQHTList = databaseModel.getKetQuaHocTap(username);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        XYChart.Series series = new XYChart.Series();
        System.out.println(javafx.scene.text.Font.getFamilies());
        for (KetQuaHocTap currentUserKQHT:
                currentUserKQHTList) {
            series.getData().add(new XYChart.Data(currentUserKQHT.getNameMH(), currentUserKQHT.getDiemKT()));
        }
        barChart_KQHT.getData().add(series);
        for (final XYChart.Series<String, Number> series1 : (ObservableList<XYChart.Series<String, Number>>) barChart_KQHT.getData()) {
            for (final XYChart.Data<String, Number> data : series1.getData()) {
                Tooltip tooltip = new Tooltip();
                tooltip.setStyle("-fx-font-size: 40");
                tooltip.setStyle("-fx-font-weight: bold");
                tooltip.setText(data.getXValue() +"\nĐiểm: "+
                        data.getYValue().toString());
                tooltip.setShowDelay(Duration.seconds(0));
                Tooltip.install(data.getNode(), tooltip);
            }
        }
    }

    @FXML
    public void exitButtonClicked() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/login_view.fxml"));
            Parent root = loader.load();

            Stage loginStage = new Stage();
            loginStage.setTitle("Đăng nhập hệ thống");
            loginStage.setScene(new Scene(root, 600, 400));
            loginStage.show();

            Stage currentStage = (Stage) exitButton.getParentPopup().getOwnerWindow();
            currentStage.close();

        } catch (IOException e) {
            e.printStackTrace();
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Lỗi");
            alert.setHeaderText(null);
            alert.setContentText("Đã xảy ra lỗi. Vui lòng thử lại!");
            alert.showAndWait();
        }
    }



    @FXML
    public void btn_studentInfoClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/student_infor.fxml"));
        try {
            Stage currentStage = (Stage) btn_studentInfo.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_learningOutcomeClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/learning_outcomes.fxml"));
        try {
            Stage currentStage = (Stage) btn_learningOutcome.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_scheduleClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/schedule.fxml"));
        try {
            Stage currentStage = (Stage) btn_schedule.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_courseEnrolmentClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/register_for_the_course.fxml"));
        try {
            Stage currentStage = (Stage) btn_courseEnrolment.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void btn_reExamineClicked() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/student_management_sys/view/re-examine.fxml"));
        try {
            Stage currentStage = (Stage) btn_reExamine.getScene().getWindow();
            Parent root = loader.load();
            Scene scene = new Scene(root, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
