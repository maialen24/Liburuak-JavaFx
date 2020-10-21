package isad.ehu.utils;

import com.google.gson.Gson;
import isad.ehu.Book;
import javafx.scene.image.Image;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {

    public Book readFromUrl(String isbn) throws IOException {
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
    public Image createImage(String url) throws IOException {
        url=url.replace("-S","-M");
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }

    }
    public void saveImage(String imageUrl, String path) {
        // This method only saves the "dummy" image
        try{
            URL url = new URL(imageUrl);
            InputStream is = url.openStream();
            OutputStream os = new FileOutputStream(path);

            byte[] b = new byte[2048];
            int length;

            while ((length = is.read(b)) != -1) {
                os.write(b, 0, length);
            }

            is.close();
            os.close();
        }catch(IOException e){
            e.printStackTrace();
        }

    }


}
