package isad.ehu;

import com.google.gson.Gson;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;


public class ComboBoxAdvBooks extends Application  {

    private Text label;

    public class Details {
        String[] publishers;
        Integer number_of_pages;
        String title;

        @Override
        public String toString() {
            return "Details{" +
                    "publishers=" + Arrays.toString(publishers) +
                    ", number_of_pages=" + number_of_pages +
                    ", title='" + title + '\'' +
                    '}';
        }
    }

    public class Book {
        String isbn;
        String title;

        String info_url;
        String bib_key;
        String preview_url;
        String thumbnail_url;
        Details details;

        public Book(String isbn, String title) {
            this.isbn = isbn;
            this.title = title;
        }

        @Override
        public String toString() {
            return "Book{" +
                    "isbn='" + isbn + '\'' +
                    ", title='" + title + '\'' +
                    ", info_url='" + info_url + '\'' +
                    ", bib_key='" + bib_key + '\'' +
                    ", preview_url='" + preview_url + '\'' +
                    ", thumbnail_url='" + thumbnail_url + '\'' +
                    ", details=" + details +
                    '}';
        }
    }

    private Book readFromUrl(String isbn) throws IOException {
        URL openlibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+isbn+"&jscmd=details&format=json");
        URLConnection yc = openlibrary.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(
                yc.getInputStream()));
        String inputLine = in.readLine();
        in.close();

        String[] zatiak = inputLine.split("ISBN:"+isbn+"\":");
        inputLine = zatiak[1].substring(0, zatiak[1].length()-1);

        Gson gson = new Gson();
        return gson.fromJson(inputLine, Book.class);
    }


    @Override
    public void start(Stage primaryStage) {

        primaryStage.setTitle("OpenLibrary APIa aztertzen");

        ComboBox comboBox = new ComboBox();
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(
                new Book("1491910399", "R for Data Science"),
                new Book("1491946008", "Fluent Python"),
                new Book("9781491906187", "Data Algorithms")
        );
        comboBox.setItems(books);
        comboBox.setEditable(false);

        comboBox.setOnAction( e -> {
            Book book = (Book)comboBox.getValue();
            System.out.println(book.details.toString());
            try {
                Book liburua = readFromUrl(book.isbn);
                this.label.setText( liburua.details.title + "\n" +
                        liburua.details.number_of_pages + "\n" +
                        liburua.details.publishers[0] );

            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });


        comboBox.setConverter(new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                if (book==null)
                    return "";
                return book.title;
            }

            @Override
            public Book fromString(String string) {
                return null;
            }
        });

        label = new Text();
        label.wrappingWidthProperty().set(400);

        VBox vbox = new VBox(comboBox, this.label);
        vbox.setPadding(new Insets(0, 0, 0, 20));
        vbox.setAlignment(Pos.BASELINE_CENTER);

        Scene scene = new Scene(vbox, 400, 120);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}