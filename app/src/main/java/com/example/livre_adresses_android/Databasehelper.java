package com.example.livre_adresses_android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class Databasehelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_CONTACTS = "contacts";
    private static final String COL_ID = "id";
    private static final String COL_NOM = "nom";
    private static final String COL_TEL = "telephone";
    private static final String COL_FAVORI = "favori";

    public Databasehelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Création de la table
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_CONTACTS + " (" +
                COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_NOM + " TEXT, " +
                COL_TEL + " TEXT, " +
                COL_FAVORI + " INTEGER)";
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CONTACTS);
        onCreate(db);
    }

    // Ajouter un contact
    public long addContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NOM, contact.getNom());
        cv.put(COL_TEL, contact.getTelephone());
        cv.put(COL_FAVORI, contact.isFavori() ? 1 : 0);
        long id = db.insert(TABLE_CONTACTS, null, cv);
        db.close();
        return id;
    }

    // Mettre à jour un contact
    public int updateContact(Contact contact) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_NOM, contact.getNom());
        cv.put(COL_TEL, contact.getTelephone());
        cv.put(COL_FAVORI, contact.isFavori() ? 1 : 0);
        int rows = db.update(TABLE_CONTACTS, cv, COL_ID + "=?", new String[]{String.valueOf(contact.getId())});
        db.close();
        return rows;
    }

    // Supprimer un contact
    public void deleteContact(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_CONTACTS, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }

    // Récupérer tous les contacts
    public List<Contact> getAllContacts() {
        List<Contact> contactList = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_CONTACTS + " ORDER BY " + COL_NOM + " ASC", null);

        if (cursor.moveToFirst()) {
            do {
                Contact contact = new Contact(
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_ID)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_NOM)),
                        cursor.getString(cursor.getColumnIndexOrThrow(COL_TEL)),
                        cursor.getInt(cursor.getColumnIndexOrThrow(COL_FAVORI)) == 1
                );
                contactList.add(contact);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return contactList;
    }

    // Mettre à jour favoris
    public void setFavori(int id, boolean favori) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COL_FAVORI, favori ? 1 : 0);
        db.update(TABLE_CONTACTS, cv, COL_ID + "=?", new String[]{String.valueOf(id)});
        db.close();
    }
}