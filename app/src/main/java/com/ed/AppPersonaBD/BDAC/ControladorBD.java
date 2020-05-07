package com.ed.AppPersonaBD.BDAC;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ControladorBD {
    private BD bd;
    public ControladorBD(Context context) {
        this.bd = new BD(context, DefDB.tabla_per, null, 1);
    }
    public long agregarRegistro(PersonaBD persona) {
        try {
            SQLiteDatabase database = bd.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(DefDB.col_cedula, persona.getCedula());
            values.put(DefDB.col_nombre, persona.getNombre());
            values.put(DefDB.col_estrato, persona.getEstrato());
            values.put(DefDB.col_salario, persona.getSalario());
            values.put(DefDB.col_nivel_edu, persona.getNivel_edu());
            return database.insert(DefDB.tabla_per, null, values);
        } catch (Exception e) {
            return -1L;
        }
    }

    public int actualizarRegistro(PersonaBD persona) {
        try {
            SQLiteDatabase database = bd.getWritableDatabase();
            ContentValues valoresActualizados = new ContentValues();
            valoresActualizados.put(DefDB.col_nombre, persona.getNombre());
            valoresActualizados.put(DefDB.col_cedula, persona.getCedula());
            valoresActualizados.put(DefDB.col_nombre, persona.getNombre());
            valoresActualizados.put(DefDB.col_estrato, persona.getEstrato());
            valoresActualizados.put(DefDB.col_salario, persona.getSalario());
            valoresActualizados.put(DefDB.col_nivel_edu, persona.getNivel_edu());

            String campoParaActualizar = DefDB.col_cedula + " = ?";
            String[] argumentosParaActualizar = {String.valueOf(persona.getCedula())};

            return database.update(DefDB.tabla_per, valoresActualizados, campoParaActualizar, argumentosParaActualizar);
        } catch (Exception e) {
            return 0;
        }
    }

    public int borrarRegistro(PersonaBD persona) {
        try {
            SQLiteDatabase database = bd.getWritableDatabase();
            String[] argumentos = {String.valueOf(persona.getCedula())};
            return database.delete(DefDB.tabla_per, DefDB.col_cedula + " = ?", argumentos);
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<PersonaBD> obtenerRegistros() {
        ArrayList<PersonaBD> personas = new ArrayList<>();

        SQLiteDatabase database = bd.getReadableDatabase();

        String[] columnasConsultar = {DefDB.col_cedula, DefDB.col_nombre, DefDB.col_estrato
                , DefDB.col_salario, DefDB.col_nivel_edu};

        Cursor cursor = database.query(DefDB.tabla_per, columnasConsultar
                , null, null, null, null, null);

        if (cursor == null) {
            return personas; } if (!cursor.moveToFirst()) {
            return personas; } do {
            PersonaBD persona = new PersonaBD(cursor.getString(0), cursor.getString(1)
                    , cursor.getInt(2), cursor.getFloat(3), cursor.getString(4));
            personas.add(persona);
        } while (cursor.moveToNext());

        cursor.close();
        return personas;
    }
    public boolean buscarEstudiante(String ced){
        String[] args = new String[] {ced};
        SQLiteDatabase database = bd.getReadableDatabase();
        Cursor c = database.query(DefDB.tabla_per, null, "cedula=?", args, null, null, null);
        if (c.getCount()>0) {
            database.close();
            return true;
        }
        else{
            database.close();
            return false;}

    }


}
