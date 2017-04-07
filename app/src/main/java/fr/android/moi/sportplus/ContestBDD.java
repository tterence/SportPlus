package fr.android.moi.sportplus;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by benji on 07/04/2017.
 */

public class ContestBDD {
    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "match.db";

    private static final String TABLE_CONTEST = "table_contest";
    private static final String COL_ID = "ID";
    private static final int NUM_COL_ID = 0;
    private static final String COL_E_DOMICILE = "Equipe Domicile";
    private static final int NUM_COL_E_DOMICILE = 1;
    private static final String COL_E_EXTERIEURE = "Equipe Exterieure";
    private static final int NUM_COL_E_EXTERIEURE = 2;
    private static final String COL_DATE = "Date";
    private static final int NUM_COL_DATE = 3;
    private static final String COL_LIEU = "Lieu";
    private static final int NUM_COL_LIEU = 4;
    private static final String COL_COMPOSITION_DOM = "Composition Domicile";
    private static final int NUM_COL_COMPOSITION_DOM = 5;
    private static final String COL_COMPOSITION_EXT = "Composition Exterieure";
    private static final int NUM_COL_COMPOSITION_EXT = 6;
    private static final String COL_IMAGE = "Image";
    private static final int NUM_COL_IMAGE = 7;

    private SQLiteDatabase bdd;
    private MySQLite mySQLite;

}
