package fr.android.moi.sportplus;

import java.sql.Blob;
import java.util.Date;

/**
 * Created by benji on 07/04/2017.
 */

public class Contest {
    private int id;
    private String dom;
    private String ext;
    private Date date;
    private String lieu;
    private String composition_dom;
    private String composition_ext;
    private Blob image;

    public Contest(){}

    public Contest(String dom, String ext, Date date, String lieu, String composition_dom, String composition_ext, Blob image){
        this.dom = dom;
        this.ext = ext;
        this.date = date;
        this.lieu = lieu;
        this.composition_dom = composition_dom;
        this.composition_ext = composition_ext;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getEdom() {
        return dom;
    }

    public String getEext() {
        return ext;
    }

    public void setEdom(String dom) {
        this.dom = dom;
    }
    public void setEext(String dom) {
        this.dom = ext;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    public void setImage (Blob image){
        this.image = image;
    }
    public Blob getImage(){
        return image;
    }

    public String toString(){
        return "ID : "+id+"\nEquipe à domicile : "+dom+"" +
                "\nEquipe à l'extérieur : "+ext+"\nDate du match : "+date
                +"\nLieu du match : "+lieu
                +"\nEquipe à l'extérieur : "+ext
                +"\nComposition equipe à domicile : "+composition_dom
                +"\nComposition equipe extérieur : "+composition_ext;
    }
}
