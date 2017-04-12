package fr.android.moi.sportplus;



/**
 * Created by benji on 07/04/2017.
 */

public class Contest {
    private int id;
    private String nom;
    private String dom;
    private String ext;
    private String date;
    private String lieu;
    private String composition_dom;
    private String composition_ext;
    private String imagePath;

    public Contest(){}

    public Contest(String dom, String nom, String ext, String date, String lieu, String composition_dom, String composition_ext, String imagePath){
        this.dom = dom;
        this.ext = ext;
        this.date = date;
        this.lieu = lieu;
        this.composition_dom = composition_dom;
        this.composition_ext = composition_ext;
        this.imagePath = imagePath;
        this.nom = nom;
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

    public String getComposition_dom(){
        return composition_dom;
    }

    public void setComposition_dom(String composition_dom){
        this.composition_dom = composition_dom;
    }
    public String getComposition_ext(){
        return composition_ext;
    }
    public void setComposition_ext(String composition_ext){
        this.composition_ext = composition_ext;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
    public void setImagePath (String imagePath){
        this.imagePath = imagePath;
    }
    public String getImage(){
        return imagePath;
    }
    public String getNom(){
        return nom;
    }
    public void setNom(String nom){
        this.nom = nom;
    }

    public String toString(){
        return "ID: "+id+"\nNom de la rencontre: "+nom
                +"\nEquipe à domicile : "+dom
                +"\nEquipe à l'extérieur : "+ext
                +"\nDate du match : "+date
                +"\nLieu du match : "+lieu
                +"\nComposition equipe à domicile : "+composition_dom
                +"\nComposition equipe extérieur : "+composition_ext
                +"\nLien vers image : "+imagePath;
    }
}
