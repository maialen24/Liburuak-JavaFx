package isad.ehu;

import java.util.Arrays;

public class Details {
    String[] publishers ;
    Integer number_of_pages;
    String title;
    String argazkia="";

    @Override
    public String toString() {
        return "Details{" +
                "publishers=" + Arrays.toString(publishers) +
                ", number_of_pages=" + number_of_pages +
                ", title='" + title + '\'' +
                '}';
    }

    public void setDetails(String pArgitaletxe, String pIzenburua, int pOrriak, String pArgazkia){
        argazkia=pArgazkia;
        publishers[publishers.length-1]=pArgitaletxe;
        title=pIzenburua;
        number_of_pages=pOrriak;
    }

    public String getArgazkia(){return argazkia;}

    public int getOrriKop(){return number_of_pages;}


    public String getArgitaletxea(){return publishers[0];}
}
