package com.example.student_management_sys.controller.SinhVien;

import com.example.student_management_sys.model.DB.DatabaseModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.prefs.Preferences;

public class Controller {
    @FXML
    private Button btn_studentInfo;
    @FXML
    private Button btn_dashboard;
    @FXML
    private Button btn_learningOutcome;
    @FXML
    private Button btn_courseEnrolment;
    @FXML
    private Button btn_schedule;
    @FXML
    private Button btn_review;
    @FXML
    private MenuButton buttonAccount;
    @FXML
    private MenuItem exitButton;

    Preferences preferences = Preferences.userNodeForPackage(MainApplication.class);
    public String username = preferences.get("MSSV", "00000");

    public void setButtonAccount() throws SQLException {
        String maSV = preferences.get("MSSV", "00000");
        DatabaseModel databaseModel = new DatabaseModel();
        String name = databaseModel.getHoTen(maSV);
        buttonAccount.setText(name);
    }

    public FXMLLoader OpenNewFXMLFile(String path, Button btn) {
        FXMLLoader loader1 = new FXMLLoader(getClass().getResource(path));
        try {
            Stage currentStage = (Stage) btn.getScene().getWindow();
            Parent root1 = loader1.load();
            Controller controller = loader1.getController();
            controller.setButtonAccount();
            Scene scene = new Scene(root1, 1424, 750);
            currentStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return loader1;
    }

    @FXML
    public void btn_studentInfoClicked() {
     FXMLLoader loader =   OpenNewFXMLFile("/com/example/student_management_sys/view/student_infor.fxml", btn_studentInfo);
     StudentController studentController = loader.getController();
     studentController.showInferStudent(username);
    }

    @FXML
    public void btn_learningOutcomeClicked() throws SQLException {
        FXMLLoader loader = OpenNewFXMLFile("/com/example/student_management_sys/view/learning_outcomes.fxml", btn_learningOutcome);
        KQHTController kqhtController = loader.getController();
        kqhtController.showKetQuaHocTayByHocKi(username);
    }

    @FXML
    public void btn_scheduleClicked() throws SQLException {
        FXMLLoader loader = OpenNewFXMLFile("/com/example/student_management_sys/view/schedule.fxml", btn_schedule);
        ScheduleController scheduleController = loader.getController();
        scheduleController.setText();
        String date = scheduleController.getDate();
        scheduleController.setLichHoc(date);
    }

    @FXML
    public void btn_courseEnrolmentClicked() {
        FXMLLoader loader = OpenNewFXMLFile("/com/example/student_management_sys/view/register_for_the_course.fxml", btn_courseEnrolment);
        RegisterCourseController registerCourseController = loader.getController();
        registerCourseController.registerCourse(username);
    }

    @FXML
    public void btn_reViewClicked() {
        FXMLLoader loader = OpenNewFXMLFile("/com/example/student_management_sys/view/re-examine.fxml", btn_review);
        ReViewController reViewController = loader.getController();
        reViewController.reViewData(username);

    }

    @FXML
    public void btn_dashboardClicked() {
        FXMLLoader loader = OpenNewFXMLFile("/com/example/student_management_sys/view/home_view.fxml", btn_dashboard);
        HomeController homeController = loader.getController();
        homeController.showInforHomeView(username);
        homeController.initBarChart();
        homeController.setLichHoc();
    }

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
}
