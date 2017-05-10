package com.example.aly.gla_projet;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Aly on 03/05/2017.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME= "soiree.db";
    public static final String TABLE_NAME1= "itineraire_table";
    public static final String ITINERAIE_KEY= "id_itineraire";
    public static final String DUREE= "duree";
    public static final String TYPE_TRANSPORT= "type_transport";
    public static final String LISTE_ETAPES= "liste_etapes";
    public static final String TABLE_NAME2= "lieu_table";
    public static final String LIEU_KEY= "id_lieu";
    public static final String LIEU_TYPE= "type";
    public static final String LIEU_NOM= "nom";
    public static final String LIEU_ADRESSE= "adresse";
    public static final String LIEU_LONGITUDE= "longitude";
    public static final String LIEU_LATITUDE= "latitude";
    public static final String LIEU_DESCRIPTION= "description";
    public static final String LIEU_NOTE= "note";
    public static final String LIEU_BUDGET= "budget";
   // public static final String TABLE_NAME3= "profil_table";
   // public static final String PROFIL_KEY= "id_profil";
   // public static final String PROFIL_NOM= "nom";
    //public static final String PROFIL_PRENOM= "prenom";
    //public static final String PROFIL_ADRESSE= "adresse";
   // public static final String PROFIL_MDP= "mdp";
    //public static final String PROFIL_LISTE_PREFERENCES= "liste_preference";
    //public static final String PROFIL_EMAIL= "profil_email";
    //public static final String TABLE_NAME4= "organisateur_table";
    //public static final String ORGANISATEUR_KEY= "id_organisateur";
    //public static final String ORGANISATEUR_ID_SOIREE= "id_soiree";
   // public static final String ORGANISATEUR_EMAIL= "email";
    public static final String TABLE_NAME3= "soiree_table";
    public static final String SOIREE_KEY= "id_soiree";
    public static final String SOIREE_NOM= "nom";
    public static final String SOIREE_LISTE_LIEUX= "liste_lieux";
    public static final String SOIREE_LISTE_PARTICIPANTS= "liste_participants";
    public static final String SOIREE_HEURE_DEBUT= "heure_debut";
    public static final String SOIREE_HEURE_FIN= "heure_fin";
    public static final String SOIREE_THEME= "theme";
    public static final String SOIREE_NBR_PARTICIPANTS= "nb_participants";
    public static final String SOIREE_RDV_LONG= "rdv_long";
    public static final String SOIREE_RDV_LAT= "rdv_lat";
    public static final String SOIREE_ID_ORGANISATEUR= "id_organisateur";
  //  public static final String TABLE_NAME= "organiser_table";
   // public static final String ORGANISER_ID_ORGANISATEUR= "id_organisateur";// deux attributs clé
//    public static final String TABLE_NAME= "participer_table";
    //public static final String PARTICIPER_ID_UTILISATEUR= "id_utilisateur";
   // public static final String PARTICIPER_ID_SOIREE= "id_soiree";
   // public static final String TABLE_NAME= "seffectuer_table";
    //public static final String SEFFECTUER_ID_SOIREE= "id_soiree";
    //public static final String SEFFECTUER_ID_LIEU= "id_lieu";
   // public static final String TABLE_NAME= "utlisateur_table";
   // public static final String UTILISATEUR_EMAIL= "email";
   // public static final String UTILISATEUR_MDP= "mdp";
   // public static final String UTILISATEUR_LISTE_SOIREES_INVIT= "list_soirees_invit";
   // public static final String UTILISATEUR_LISTE_SOIREES_PARTICIP= "liste_soirees_particip";
    //public static final String UTILISATEUR_LISTE_AMIS= "liste_amis";
    //public static final String UTILISATEUR_ID_ITINERAIRE= "id_itineraie";
    //public static final String UTILISATEUR_ID_PROFIL= "id_profil";
   // public static final String TABLE_NAME= "inviter_table";
   // public static final String INVITER_KEY= "id_inviter";
    //public static final String INVITER_EMAIL= "email";


    public DatabaseHandler(Context context) {
        super(context,DATABASE_NAME , null, 1);
        SQLiteDatabase db = this.getWritableDatabase(); // il cree la bd et la table//
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table" + TABLE_NAME1 + "(id_itineraie INTEGER PRIMARY KEY AUTOINCREMENT, duree INTERGER , type_transport TEXT ,liste_etapes)");
        db.execSQL("create table" + TABLE_NAME2 + "(" +
                "id_lieu INTEGER PRIMARY KEY AUTOINCREMENT," +
                "type TEXT NOT NULL," +
                "nom TEXT NOT NULL," +
                "longititude REAL NOT NULL CHECKING (-90<longitude<90)," +
                "latitude REAL NOT NULL CHECKING (-90<latitude<90)," +
                "description TEXT CHECKING (description<=150)," +
                "note INTEGER NOT NULL CHECKING (0<= note <=5)," +
                "budget INTEGER NOT NULL CHECKING (1<= budget<=4),");




        db.execSQL("create table" + TABLE_NAME3 + "(id_soiree INTEGER PRIMARY KEY AUTOINCREMENT," +
                " nom TEXT ," +
                "liste_lieux TEXT NOT NULL DEFAULT 3," +
                "liste_participants TEXT NOT NULL CHECKING( 2<= liste_participants<=40)," +
                "heure_debut INTEGER NOT NULL CHECKING (18<= heure_debut<=20) ," +
                "heure_fin INTEGER NOT NULL," +
                "theme TEXT," +
                "nb_participants INTEGER NOT NULL CHECKING( nb_participants>1)," +
                "rdv_long REAL NOT NULL CHECKING( -90< rdv_long < 90)," +
                "rdv_lat REAL NOT NULL CHECKING (-90< rdv_lat <90)," +
                "id_organisateur INTEGER FOREIGN KEY (organisateur) REFERENCES(id_organisateur) ");
        db.execSQL("create table" + TABLE_NAME1 + "(" +



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1 );
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2 );
        onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME3 );
        onCreate(db)

    }
    public boolean add_user(String id, String duree ,String type_transport,String liste_etapes){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(TABLE_NAME1,id);
        contentValues.put(DUREE,duree);
        contentValues.put(TYPE_TRANSPORT,type_transport);
        contentValues.put(LISTE_ETAPES,liste_etapes);
        long res=db.insert(TABLE_NAME1,null,contentValues);
        if(res ==-1)
            return false;
        else
            return true;
        }

    }
    public boolean add_user(String id_lieu, String type ,String nom,String adresse,String longitude,String latitude,String description,String note,String budget){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(LIEU_KEY,id_lieu);
        contentValues.put(LIEU_TYPE,type);
        contentValues.put(LIEU_NOM,nom);
        contentValues.put(LIEU_LONGITUDE,longitude);
        contentValues.put(LIEU_LATITUDE,latitude);
        contentValues.put(LIEU_DESCRIPTION,description);
        contentValues.put(LIEU_NOTE,note);
        contentValues.put(LIEU_BUDGET,budget);
        long res= db.insert(TABLE_NAME1,null,contentValues);
        if(res ==-1)
            return false;
        else
            return true;

}
    public boolean add_user(String id_soiree,String nom,String liste_lieux,String liste_participants,String heure_debut,String heure_fin,String theme,String nbr_participants,String rdv_long,String rdv_lat,String id_organisateur){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(SOIREE_KEY,id_soiree);
        contentValues.put(SOIREE_NOM,nom);
        contentValues.put(SOIREE_LISTE_PARTICIPANTS,liste_participants);
        contentValues.put(SOIREE_HEURE_DEBUT,heure_debut);
        contentValues.put(SOIREE_HEURE_FIN,heure_fin);
        contentValues.put(SOIREE_THEME,theme);
        contentValues.put(SOIREE_NBR_PARTICIPANTS,nbr_participants);
        contentValues.put(SOIREE_RDV_LONG,rdv_long);
        contentValues.put(SOIREE_RDV_LAT,rdv_lat);
        contentValues.put(SOIREE_ID_ORGANISATEUR,id_organisateur);
        long res= db.insert(TABLE_NAME2,null,contentValues);
        if(res ==-1)
            return false;
        else
            return true;
    }
    }

