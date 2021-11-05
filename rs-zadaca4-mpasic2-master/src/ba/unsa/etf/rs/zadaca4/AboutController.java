package ba.unsa.etf.rs.zadaca4;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    public ImageView imgAbout;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Image image = new Image("/img/slikica.jpg");
        imgAbout.setImage(image);

    }
}
