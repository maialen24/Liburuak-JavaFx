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

    public List<Book> lortuZerbitzuak() {

        String query = "select ISBN, izenburua from Liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Book> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                String isbn = rs.getString("ISBN");
                String izena = rs.getString("izenburua");
                Book lib=new Book(isbn,izena);
                System.out.println(isbn + ":" + izena);
                emaitza.add(lib);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }

    public void gehitu(String pZerbitzu){
        String query = "insert into services (izena) values('"+pZerbitzu+"')";
        System.out.println(query);
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }
    public void ezabatu(String pZerbitzu){
        String query = "delete from services where izena='"+pZerbitzu+"'";
        System.out.println(query);
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }
    public Book getLiburua(String isbn){
        Book liburua=null;
        String query = "Select * from Liburua where ISBN='"+isbn+"'";
        System.out.println(query);
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
    public boolean badago(String isbn){
        boolean emaitza=false;
        String query = "Select argazkia from Liburua where isbn='"+isbn+"'";
        System.out.println(query);
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
        try{
            rs.next();
            System.out.println(rs.getString("argazkia"));
            if (rs.getString("argazkia")!=null) {
                emaitza = true;
            }
        }catch(SQLException throwables){
        throwables.printStackTrace();
    }
        return emaitza;
    }

    public void liburuaKargatu(Details details, String isbn){
        String query="update Liburua set OrriKop="+details.getOrriKop()+" and argitaletxe='"+details.getArgitaletxea().replace("'","''")+"'and argazkia='"+details.getArgazkia()+"' where ISBN='"+isbn+"'";
        //String query = "insert into Liburua (argitaletxe,OrriKop,argazkia) values('"+details.getArgitaletxea().replace("'","''")+"',"+details.getOrriKop()+",'"+details.getArgazkia()+"') where ISBN='"+isbn+"'";
        System.out.println(query);
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);
    }
}
