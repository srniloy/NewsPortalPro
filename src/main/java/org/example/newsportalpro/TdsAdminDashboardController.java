package org.example.newsportalpro;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class TdsAdminDashboardController implements Initializable{


    @FXML
    private TextField newsHeading;

    @FXML
    private TextArea newsDiscription;

    @FXML
    private ImageView imageView;
    @FXML
    private ToggleGroup toggleNewsType;
    @FXML
    private Label userNameLabel;
    @FXML
    private ComboBox<String> writerCombo;
    private String articleImagePath;
    private String userEmail;
    private String userName;

    private static final String IMAGES_DIR = "src/main/resources/org/example/newsportalpro/images"; // Define the directory to save images
    private String articleDbPath = "src/main/resources/org/example/newsportalpro/database_files/articles.txt";

    @FXML
    private void importbtn(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Your Files");

        FileChooser.ExtensionFilter filter1 = new FileChooser.ExtensionFilter("All Files (*.*)", "*.*");
        FileChooser.ExtensionFilter filter2 = new FileChooser.ExtensionFilter("JPEG Files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter filter3 = new FileChooser.ExtensionFilter("PNG Files (*.png)", "*.png");
        fileChooser.getExtensionFilters().addAll(filter1, filter2, filter3);

        File file = fileChooser.showOpenDialog(stage);

        if (file != null) {
            try {
                // Load and display the image
                String demoPath = file.toURI().toURL().toString();
                Image image = new Image(file.toURI().toURL().toString());
                imageView.setImage(image);
                System.out.println(demoPath);

                // Save the image in the specified directory
                File destination = new File(IMAGES_DIR, file.getName());
                Files.copy(file.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
                articleImagePath = destination.getName();
                System.out.println("Image saved successfully to " + articleImagePath);

            } catch (MalformedURLException e) {
                System.out.println("File URL is malformed");
            } catch (IOException e) {
                System.out.println("Error saving the image: " + e.getMessage());
            }
        } else {
            System.out.println("No File Selected.");
        }
    }



    void setProfileInfo(String email, String name){
        userEmail = email;
        userName = name;
        userNameLabel.setText(name);
    }

    @FXML
    void publishbtn(ActionEvent event) {

        String writerName = writerCombo.getValue();
        String title = newsHeading.getText();
        String desc = newsDiscription.getText();
        String imgPath = articleImagePath;
        RadioButton TnewsType = (RadioButton) toggleNewsType.getSelectedToggle();
        String newsType = TnewsType.getText();
        LocalDate publishedDate = LocalDate.now();
        Article article = new Article(title, desc, imgPath, newsType, writerName, publishedDate.toString());

        article.print();

        File f = new File(articleDbPath);

        if(f.length() == 0){
            HashMap<String, ArrayList<Article>> hm = new HashMap<>();
            ArrayList<Article> articlesArray = new ArrayList<>();
            articlesArray.add(article);
            hm.put("thedailystar_articles", articlesArray);

            try {
                FileOutputStream fos = new FileOutputStream(articleDbPath);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(hm);
                oos.close();
            } catch (Exception e) {
                System.out.println("Exception during File Serialization : "+ e);
            }
        }
        else{
            try {
                FileInputStream fis = new FileInputStream(articleDbPath);
                ObjectInputStream ois = new ObjectInputStream(fis);
                HashMap<String,ArrayList<Article>> hm = (HashMap<String, ArrayList<Article>>) ois.readObject();

                ArrayList<Article> articlesArray = hm.get("thedailystar_articles");
                if(articlesArray == null){
                    articlesArray = new ArrayList<>();
                }

                articlesArray.add(article);
                hm.put("thedailystar_articles",articlesArray);

                FileOutputStream fos = new FileOutputStream(articleDbPath);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                oos.writeObject(hm);
                oos.close();
                ois.close();
                fis.close();
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        newsHeading.clear();
        newsDiscription.clear();
        imageView.setImage(null);
        toggleNewsType.selectToggle(null);

    }

    @FXML
    void Logoutbtn(ActionEvent event) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml_files/sign_in.fxml"));
        Parent dashboardPanel = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(dashboardPanel, 900, 600));
        stage.setTitle("Sign In");
        stage.show();
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.close();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        writerCombo.setItems(FXCollections.observableArrayList("Rakibul Hasan", "Tanvir Ahmed", "Md. Shakil Khan"));
    }
}

