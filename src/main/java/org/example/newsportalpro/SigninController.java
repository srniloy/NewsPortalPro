package org.example.newsportalpro;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class SigninController {

    @FXML
    private TextField PasswordTextField;

    @FXML
    private TextField UserTextField;


    @FXML
    void CreateAccountBtn(ActionEvent event) throws IOException {

        System.out.println(UserTextField.getText());
        System.out.println(PasswordTextField.getText());

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml_files/sign_up.fxml"));
        Parent dashboardPanel = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(dashboardPanel, 900, 600));
        stage.setTitle("Newspaper Dashboard");
        stage.show();
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.close();
    }
    @FXML
    void SignInBtn(ActionEvent event) throws IOException {
//        StringBuilder sb = new StringBuilder();
//        sb.append(UserTextField.getText().toString() + "\n");
//        sb.append(PasswordTextField.getText().toString());
        String email = UserTextField.getText();
        String pass = PasswordTextField.getText();
        String fileLocation = "src/main/resources/org/example/newsportalpro/database_files/users_info.txt";

        try{
            FileInputStream fis = new FileInputStream(fileLocation);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashSet<String> hs = (HashSet<String>) ois.readObject();
            HashMap<String,UserSignUpInfo> hm = (HashMap<String, UserSignUpInfo>) ois.readObject();


            if(hs.contains(email) && hm.get(email).password.compareTo(pass) == 0){
                System.out.println("ini-email: "+email+" ini-Password: "+ pass);
                System.out.println("store-userName: "+hs.contains(email)+" store-Password: "+ hm.get(email).password);

                FXMLLoader fl = new FXMLLoader(getClass().getResource("fxml_files/reader_homepage.fxml"));
                Parent  mainPanel = fl.load();
                ReaderHomepageController cntrl = fl.getController();
                cntrl.setProfileInfo(email);
                Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
                Stage stage = new Stage();
                stage.setScene(new Scene(mainPanel,900,600));
                stage.setTitle("Newspaper Dashboard");
                stage.setResizable(false);
                stage.centerOnScreen();
                stage.show();
                st.close();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
