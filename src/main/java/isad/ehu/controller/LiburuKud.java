package isad.ehu.controller;

import isad.ehu.Book;
import isad.ehu.Liburuak;
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
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(
                new Book("1491910399", "R for Data Science"),
                new Book("1491946008", "Fluent Python"),
                new Book("9781491906187", "Data Algorithms")
        );
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
        aukerak.setItems(books);
        aukerak.setEditable(false);
    }
}

