package com.ed.AppPersonaBD.BDAC;

public class DefDB {

    public static final String col_nombre = "nombre";
    public static final String col_estrato = "estrato";
    public static final String col_salario = "salario";
    public static final String col_nivel_edu = "nivel";
    public static final String tabla_per = "persona";
    public static final String nameDb = "Universidad";
    public static final String col_cedula = "cedula";


    public static final String CREATE_TABLA_PERSONA="CREATE TABLE persona(" +
            "cedula TEXT primary key," +
            "nombre TEXT not null,"+
            "estrato INTEGER not null," +
            "salario REAL not null," +
            "nivel TEXT not null" +
            ");";
}
