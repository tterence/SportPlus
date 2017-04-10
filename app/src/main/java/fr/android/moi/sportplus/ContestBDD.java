package fr.android.moi.sportplus;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Date;

/**
 * Created by benji on 07/04/2017.
 */

public class ContestBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "match.db";

    private static final String TABLE_CONTEST = "table_contest";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_E_DOMICILE = "Equipe_Domicile";
    private static final int NUM_COL_E_DOMICILE = 1;
    private static final String COL_E_EXTERIEURE = "Equipe_Exterieure";
    private static final int NUM_COL_E_EXTERIEURE = 2;
    private static final String COL_DATE = "Date";
    private static final int NUM_COL_DATE = 3;
    private static final String COL_LIEU = "Lieu";
    private static final int NUM_COL_LIEU = 4;
    private static final String COL_COMPOSITION_DOM = "Composition_Domicile";
    private static final int NUM_COL_COMPOSITION_DOM = 5;
    private static final String COL_COMPOSITION_EXT = "Composition_Exterieure";
    private static final int NUM_COL_COMPOSITION_EXT = 6;
    private static final String COL_IMAGEPATH = "Image";
    private static final int NUM_COL_IMAGEPATH = 7;
    private static final String COL_NOM = "Nom";
    private static final int NUM_COL_NOM = 8;

    private SQLiteDatabase bdd;
    private MySQLite mySQLite;

    public ContestBDD(Context context){
        mySQLite = new MySQLite(context, NOM_BDD, null, VERSION_BDD);
    }

    public void open(){
        bdd = mySQLite.getWritableDatabase();
    }

    public void close(){
        bdd.close();
    }
    public SQLiteDatabase getBDD() {
        return bdd;
    }

    public long insertContest (Contest contest){
        ContentValues values = new ContentValues();
        values.put(COL_COMPOSITION_DOM, contest.getComposition_dom());
        values.put(COL_COMPOSITION_EXT, contest.getComposition_ext());
        values.put(COL_DATE, contest.getDate());
        values.put(COL_LIEU, contest.getLieu());
        values.put(COL_E_DOMICILE, contest.getEdom());
        values.put(COL_E_EXTERIEURE, contest.getEext());
        values.put(COL_IMAGEPATH, contest.getImage());
        values.put(COL_NOM, contest.getNom());
        return bdd.insert(TABLE_CONTEST, null, values);
    }
    public int updateContest (int id, Contest contest){
        ContentValues values = new ContentValues();
        values.put(COL_COMPOSITION_DOM, contest.getComposition_dom());
        values.put(COL_COMPOSITION_EXT, contest.getComposition_ext());
        values.put(COL_DATE, contest.getDate());
        values.put(COL_LIEU, contest.getLieu());
        values.put(COL_E_DOMICILE, contest.getEdom());
        values.put(COL_E_EXTERIEURE, contest.getEext());
        values.put(COL_IMAGEPATH, contest.getImage());
        values.put(COL_NOM, contest.getNom());
        return bdd.update(TABLE_CONTEST, values, COL_ID + " = " + id, null);
    }
    public Contest getContest(String param){
        Cursor cursor = bdd.query(TABLE_CONTEST, new String[]{
                COL_ID, COL_E_DOMICILE, COL_COMPOSITION_EXT, COL_DATE, COL_LIEU,
                COL_COMPOSITION_DOM, COL_COMPOSITION_EXT, COL_IMAGEPATH,
                COL_NOM}, COL_NOM + " LIKE \"" + param +"\"",null,null,null,null);
        return cursorToLivre(cursor);
    }

    private Contest cursorToLivre(Cursor cursor) {
        if (cursor.getCount() == 0) {
            return null;
        }
        cursor.moveToFirst();
        Contest contest = new Contest();
        contest.setId(cursor.getInt(NUM_COL_ID));
        contest.setEdom(cursor.getString(NUM_COL_E_DOMICILE));
        contest.setEext(cursor.getString(NUM_COL_E_EXTERIEURE));
        contest.setDate(cursor.getString(NUM_COL_DATE));
        contest.setLieu(cursor.getString(NUM_COL_LIEU));
        contest.setComposition_dom(cursor.getString(NUM_COL_COMPOSITION_DOM));
        contest.setComposition_ext(cursor.getString(NUM_COL_COMPOSITION_EXT));
        contest.setImagePath(cursor.getString(NUM_COL_IMAGEPATH));
        contest.setNom(cursor.getString(NUM_COL_NOM));
        cursor.close();

        return contest;
    }


}
