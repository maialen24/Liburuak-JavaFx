package isad.ehu.controller.ui;

import isad.ehu.Book;
import isad.ehu.Liburuak;
import isad.ehu.controller.db.ZerbitzuKud;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class LiburuKud implements Initializable {

    private Liburuak mainApp;
    @FXML
    private Button ikusiBUtton;

    @FXML
    private ComboBox<Book> aukerak;

    @FXML
    void onClick(ActionEvent event) throws IOException {
        Book book = (Book)aukerak.getValue();

        mainApp.liburuaLortu(book);

        mainApp.XehetasunakErakutsi();

    }

    public void setMainApp(Liburuak liburuak) {
        this.mainApp = liburuak;
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Book> LiburuakList = ZerbitzuKud.getInstance().lortuLiburuak();
        ObservableList<Book> liburuak = FXCollections.observableArrayList(LiburuakList);

        aukerak.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                if (book==null)
                    return "";
                return book.getTitle();
            }

            @Override
            public Book fromString(String string) {
                return null;
            }
        });
        aukerak.setItems(liburuak);
        aukerak.setEditable(false);
    }
}

