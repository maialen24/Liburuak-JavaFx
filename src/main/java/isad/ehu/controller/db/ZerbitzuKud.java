package isad.ehu.controller.db;

import isad.ehu.Book;
import isad.ehu.Details;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }

    public List<Book> lortuLiburuak() {

        String query = "select ISBN, izenburua from Liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Book> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                String isbn = rs.getString("ISBN");
                String izena = rs.getString("izenburua");
                Book lib=new Book(isbn,izena);
                emaitza.add(lib);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void gehitu(String isbn, String titulu){
        String query = "insert into Liburua (ISBN,izenburua) values('"+isbn+"','"+titulu+"')";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }
    public void ezabatu(String isbn){
        String query = "delete from Liburua where izena='"+isbn+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }

    public Book getLiburua(String isbn){
        Book liburua=null;
        String query = "Select * from Liburua where ISBN='"+isbn+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        try {
            rs.next();
            liburua=new Book (rs.getString("ISBN"), rs.getString("izenburua"));
            liburua.getDetails().setDetails(rs.getString("argitaletxe"),rs.getString("izenburua"),rs.getInt("OrriKop"),rs.getString("argazkia"));
        }catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return liburua;
    }
    public boolean badago(String isbn)  {
        String query = "Select argitaletxe from Liburua where isbn='"+isbn+"' AND argitaletxe is not null";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        try {
            return rs.next();
        }
        catch(SQLException throwables){
            throwables.printStackTrace();
        }
        return false;

    }

    public void liburuaKargatu(Details details, String isbn){
        String query="update Liburua set OrriKop="+details.getOrriKop()+", argitaletxe='"+details.getArgitaletxea().replace("'","''")+"', argazkia='"+details.getArgazkia()+"' where ISBN='"+isbn+"'";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }
}
