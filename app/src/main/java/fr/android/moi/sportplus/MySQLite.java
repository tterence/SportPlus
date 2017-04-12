package fr.android.moi.sportplus;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by benji on 07/04/2017.
 */

public class MySQLite extends SQLiteOpenHelper {
    private static final String TABLE_CONTEST = "table_contest";
    private static final String COL_ID = "ID";
    private static final String COL_E_DOMICILE = "Equipe_Domicile";
    private static final String COL_E_EXTERIEURE = "Equipe_Exterieure";
    private static final String COL_DATE = "Date";
    private static final String COL_LIEU = "Lieu";
    private static final String COL_COMPOSITION_DOM = "Composition_Domicile";
    private static final String COL_COMPOSITION_EXT = "Composition_Exterieure";
    private static final String COL_IMAGEPATH = "Image";
    private static final String COL_NOM = "Nom";

    private static final String CREATE_BDD = "CREATE TABLE " + TABLE_CONTEST + " ("
            + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + COL_E_DOMICILE + " TEXT NOT NULL, "
            + COL_E_EXTERIEURE + " TEXT NOT NULL, " + COL_DATE + " TEXT NOT NULL, " + COL_LIEU
            + " TEXT NOT NULL, " + COL_COMPOSITION_DOM + " TEXT NOT NULL, " + COL_COMPOSITION_EXT
            + " TEXT NOT NULL, " + COL_IMAGEPATH + " TEXT NOT NULL, " + COL_NOM + " TEXT NOT NULL );";

    public MySQLite(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BDD);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE " + TABLE_CONTEST + ";");
        onCreate(db);
    }
    @Override
    protected void finalize() throws Throwable {
        this.close();
        super.finalize();
    }
}
