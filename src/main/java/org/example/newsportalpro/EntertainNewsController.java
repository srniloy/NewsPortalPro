package org.example.newsportalpro;


import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class EntertainNewsController implements Initializable {

    @FXML
    private Text date1;

    @FXML
    private Text date2;

    @FXML
    private Text date3;

    @FXML
    private Text date4;

    @FXML
    private Text date5;

    @FXML
    private Text date6;

    @FXML
    private ImageView img1;

    @FXML
    private ImageView img2;

    @FXML
    private ImageView img3;

    @FXML
    private ImageView img4;

    @FXML
    private ImageView img5;

    @FXML
    private ImageView img6;

    @FXML
    private AnchorPane newsPane1;

    @FXML
    private AnchorPane newsPane2;

    @FXML
    private AnchorPane newsPane3;

    @FXML
    private AnchorPane newsPane4;

    @FXML
    private AnchorPane newsPane5;

    @FXML
    private AnchorPane newsPane6;

    @FXML
    private Text title1;

    @FXML
    private Text title2;

    @FXML
    private Text title3;

    @FXML
    private Text title4;

    @FXML
    private Text title5;

    @FXML
    private Text title6;

    @FXML
    private Text writer1;

    @FXML
    private Text writer2;

    @FXML
    private Text writer3;

    @FXML
    private Text writer4;

    @FXML
    private Text writer5;

    @FXML
    private Text writer6;




    private String articleDbPath = "src/main/resources/org/example/newsportalpro/database_files/articles.txt";

    private String imgLoaction = "src/main/resources/org/example/newsportalpro/images/";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        newsPane1.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                System.out.println("clicked");
            }
        });

        try {
            FileInputStream fis = new FileInputStream(articleDbPath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            HashMap<String, ArrayList<Article>> hm = (HashMap<String, ArrayList<Article>>) ois.readObject();

            ArrayList<Article> articlesArray = (ArrayList<Article>) hm.get("prothomalo_articles");
            articlesArray.addAll(hm.get("thedailystar_articles"));
            if(articlesArray==null){
                articlesArray = new ArrayList<>();
            }
            newsPane1.setVisible(false);
            newsPane2.setVisible(false);
            newsPane3.setVisible(false);
            newsPane4.setVisible(false);
            newsPane5.setVisible(false);
            newsPane6.setVisible(false);
            int j = 0;
            for (int i = 0; i < articlesArray.size(); i++) {
                articlesArray.get(i).print();

                if(articlesArray.get(i).newsType.equals("Entertainment")){
                    switch (j) {
                        case 0:
                            newsPane1.setVisible(true);
                            title1.setText(articlesArray.get(i).title);
                            writer1.setText(articlesArray.get(i).writerName);
                            date1.setText(articlesArray.get(i).publishedDate);
                            InputStream stream1 = new FileInputStream(imgLoaction + articlesArray.get(i).imgPath);
                            Image image1 = new Image(stream1);
                            img1.setImage(image1);
                            break;
                        case 1:
                            newsPane2.setVisible(true);
                            title2.setText(articlesArray.get(i).title);
                            writer2.setText(articlesArray.get(i).writerName);
                            date2.setText(articlesArray.get(i).publishedDate);
                            InputStream stream2 = new FileInputStream(imgLoaction + articlesArray.get(i).imgPath);
                            Image image2 = new Image(stream2);
                            img2.setImage(image2);
                            break;
                        case 2:
                            newsPane3.setVisible(true);
                            title3.setText(articlesArray.get(i).title);
                            writer3.setText(articlesArray.get(i).writerName);
                            date3.setText(articlesArray.get(i).publishedDate);
                            InputStream stream3 = new FileInputStream(imgLoaction + articlesArray.get(i).imgPath);
                            Image image3 = new Image(stream3);
                            img3.setImage(image3);
                            break;
                        case 3:
                            newsPane4.setVisible(true);
                            title4.setText(articlesArray.get(i).title);
                            writer4.setText(articlesArray.get(i).writerName);
                            date4.setText(articlesArray.get(i).publishedDate);
                            InputStream stream4 = new FileInputStream(imgLoaction + articlesArray.get(i).imgPath);
                            Image image4 = new Image(stream4);
                            img4.setImage(image4);
                            break;
                        case 4:
                            newsPane5.setVisible(true);
                            title5.setText(articlesArray.get(i).title);
                            writer5.setText(articlesArray.get(i).writerName);
                            date5.setText(articlesArray.get(i).publishedDate);
                            InputStream stream5 = new FileInputStream(imgLoaction + articlesArray.get(i).imgPath);
                            Image image5 = new Image(stream5);
                            img5.setImage(image5);
                            break;
                        case 5:
                            newsPane6.setVisible(true);
                            title6.setText(articlesArray.get(i).title);
                            writer6.setText(articlesArray.get(i).writerName);
                            date6.setText(articlesArray.get(i).publishedDate);
                            InputStream stream6 = new FileInputStream(imgLoaction + articlesArray.get(i).imgPath);
                            Image image6 = new Image(stream6);
                            img6.setImage(image6);
                            break;
                    }
                    j++;

                }

            }


        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }


    }
    @FXML
    void backIconClickAction(MouseEvent event) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml_files/reader_homepage.fxml"));
        Parent dashboardPanel = fxmlLoader.load();
        Stage stage = new Stage();

        stage.setScene(new Scene(dashboardPanel, 900, 600));
        stage.setTitle("Sign In");
        stage.show();
        Stage st = (Stage)((Node)event.getSource()).getScene().getWindow();
        st.close();
    }
}
