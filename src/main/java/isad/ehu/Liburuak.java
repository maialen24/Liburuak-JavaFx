package isad.ehu;

import isad.ehu.controller.LiburuKud;
import isad.ehu.controller.XehetasunakKud;
import isad.ehu.utils.Sarea;
import javafx.application.Application;

import javafx.fxml.FXMLLoader;

import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;

import java.io.IOException;


public class Liburuak extends Application{

    private Book liburua;
    private Scene libScene;
    private Scene XeheScene;

    private Parent XehetasunakUI;
    private Parent LiburuUI;

    private Stage stage;

    private XehetasunakKud XehetasunakKud;
    private LiburuKud LiburuKud;
    private Sarea sarea=new Sarea();

    @Override
    public void start(Stage primaryStage) throws Exception {

        stage = primaryStage;
        pantailakKargatu();

        stage.setTitle("OpenLibrary APIa aztertzen");
        stage.setScene(libScene);
        stage.show();
    }


    private void pantailakKargatu() throws IOException {

        FXMLLoader loaderLiburuak = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
        LiburuUI = (Parent) loaderLiburuak.load();
        LiburuKud = loaderLiburuak.getController();
        LiburuKud.setMainApp(this);
        libScene= new Scene(LiburuUI,500,400);



        FXMLLoader loaderXehetasunak = new FXMLLoader(getClass().getResource("/Xehetasunak.fxml"));
        XehetasunakUI = (Parent) loaderXehetasunak.load();
        XehetasunakKud = loaderXehetasunak.getController();
        XehetasunakKud.setMainApp(this);
        XeheScene= new Scene(XehetasunakUI,800,600);
    }


    public static void main(String[] args) {
        launch(args);
    }


    public void liburuaLortu(Book book){
        try{
            liburua = sarea.readFromUrl(book.isbn);
    } catch (IOException ioException) {
        ioException.printStackTrace();

    }}

    public void XehetasunakErakutsi() throws IOException {

        XehetasunakKud.liburuaKargatu(liburua.details.number_of_pages,liburua.details.publishers,liburua.details.title, sarea.createImage(liburua.thumbnail_url));
        stage.setScene(XeheScene);
        stage.show();
    }
    public void LiburuakErakutsi() {
        stage.setScene(libScene);
        stage.show();
    }

}
