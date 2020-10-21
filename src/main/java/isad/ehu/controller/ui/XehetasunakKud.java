package isad.ehu.controller.ui;

import isad.ehu.Liburuak;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class XehetasunakKud implements Initializable {
    private Liburuak mainApp;
    @FXML
    private Button atzeraButton;

    @FXML
    private Label izenburuText;

    @FXML
    private Label argitaletxeText;

    @FXML
    private Label OrriKopText;

    @FXML
    private ImageView imageJarri;

    @FXML
    void onClick(ActionEvent event) {
        mainApp.LiburuakErakutsi();
    }

    public void liburuaKargatu( int orriKop, String[] argitaletxe, String izenburua, Image argazkia){
        izenburuText.setText(izenburua);
        OrriKopText.setText(""+orriKop);
        String a=Arrays.toString(argitaletxe);
        argitaletxeText.setText(a.substring(1,(a.length()-1)));
        imageJarri.setImage(argazkia);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }



    public void setMainApp(Liburuak liburuak) {
        this.mainApp = liburuak;
    }
}
