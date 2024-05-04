package org.example.newsportalpro;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class ReaderHomepageController {

    @FXML
    private ImageView dailystarimg;

    @FXML
    private Button prothomaloreadbtn;

    @FXML
    private ImageView protomaloimg;

    @FXML
    private Button readthedailystar;

    private String userEmail = "undefined";

    @FXML
    void Logoutbtn(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("SignIn.fxml"));
        Parent dashboardPanel = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(dashboardPanel, 900, 600));
        stage.setTitle("Newspaper Dashboard");
        stage.show();
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.close();
    }

    @FXML
    void Prothomalobtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Prothomalo.fxml"));

        Parent dashboardPanel = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(dashboardPanel, 900, 600));
        stage.setTitle("Newspaper Dashboard");
        stage.show();
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.close();


    }

    @FXML
    void readthedailystarbtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("TheDailyStar.fxml"));

        Parent dashboardPanel = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(dashboardPanel, 900, 600));
        stage.setTitle("Newspaper Dashboard");
        stage.show();
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.close();

    }

    @FXML
    void readdhakatribunebtn(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DhakaTribune.fxml"));

        Parent dashboardPanel = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(dashboardPanel, 900, 600));
        stage.setTitle("Newspaper Dashboard");
        stage.show();
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.close();

    }
    void setProfileInfo(String email) {
        userEmail = email;
    }
}

