package org.example.newsportalpro;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.util.HashMap;
import java.util.HashSet;
import java.util.ResourceBundle;

public class SignUpController implements Initializable {

    @FXML
    private TextField PasswordTextField;

    @FXML
    private TextField EmailTextField;

    @FXML
    private TextField UserTextField;

    @FXML
    private TextField ConfirmedPasswordTextField;

    @FXML
    private ComboBox<String> userTypeCombo;

    @FXML
    void showTermsAndPrivacyPolicy(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Terms&PrivacyPolicy.fxml"));
            Parent termsRoot = fxmlLoader.load();
            Scene scene = new Scene(termsRoot, 900, 600); // Adjust size as needed
            Stage stage = new Stage();
            stage.setTitle("Terms & Privacy Policy");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the Terms & Privacy Policy view.");
        }

    }
    @FXML
    void loadDashboardBtn(ActionEvent event) throws Exception {
        try {
            String password = PasswordTextField.getText();
            String confirmedPassword = ConfirmedPasswordTextField.getText();
            String email = EmailTextField.getText();
            String userName = UserTextField.getText().trim();
            String userType = userTypeCombo.getValue();


            if (!password.equals(confirmedPassword) || !email.contains("@")) {
                showAlert(Alert.AlertType.ERROR, "Error", "Passwords do not match or invalid email.");
                return;
            }

            UserSignUpInfo userSignUpInfo = new UserSignUpInfo(userName, email, userType, password, confirmedPassword);
            //    private final String userInformationTxtPath = "E:\\DBMS\\NewspaperPortal\\src\\main\\java\\com\\example\\New_User.txt";
//            String fileLocation = "src/main/resources/org/example/newsportalpro/database_files/";
            File file = new File("src/main/resources/org/example/newsportalpro/database_files/users_info.txt");

            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
            HashSet<String> hs;
            HashMap<String, UserSignUpInfo> hm;

            if (!file.exists() || file.length() == 0) {
                hs = new HashSet<>();
                hm = new HashMap<>();
            } else {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    hs = (HashSet<String>) ois.readObject();
                    hm = (HashMap<String, UserSignUpInfo>) ois.readObject();
                }
            }

            if (hs.contains(email)) {
                return;
            }

            hs.add(email);
            hm.put(email, userSignUpInfo);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(hs);
                oos.writeObject(hm);
            }

            System.out.println(UserTextField.getText());
            System.out.println(PasswordTextField.getText());

            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml_files/sign_in.fxml"));
            Parent dashboardPanel = fxmlLoader.load();
            Stage stage = new Stage();

            stage.setScene(new Scene(dashboardPanel, 900, 600));
            stage.setTitle("Log In");
            stage.show();
            Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
            st.close();

//            loadDashboardBtn(event);
        } catch (IOException e) {
//            showAlert(Alert.AlertType.ERROR, "Error", "An IO error occurred: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
//            showAlert(Alert.AlertType.ERROR, "Error", "A class not found error occurred: " + e.getMessage());
            e.printStackTrace();
            System.out.println(e.getMessage());
        }


    }


    @FXML
    void CAsigninbtn(ActionEvent event) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml_files/sign_in.fxml"));
        try {
            Parent dashboardPanel = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(dashboardPanel, 900, 600));
            stage.setTitle("Newspaper Dashboard");
            stage.show();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).close();
        } catch (IOException e) {
//            showAlert(Alert.AlertType.ERROR, "Error", "Failed to load the dashboard view.");
            e.printStackTrace();
        }

    }


    // Reused showAlert method
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        userTypeCombo.setItems(FXCollections.observableArrayList("Reader", "ProthomAlo-Writer", "TheDailyStar-Writer"));
    }
}

class UserSignUpInfo implements Serializable {
    String userName;
    String email;
    String password;
    String confirmedPassword;
    String userType;

    UserSignUpInfo(String userName, String email,String userType, String password, String confirmedPassword) {
        this.email = email;
        this.userName = userName;
        this.password = password;
        this.confirmedPassword = confirmedPassword;
        this.userType = userType;
    }

}
