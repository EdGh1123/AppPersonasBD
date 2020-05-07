package com.ed.AppPersonaBD.BDAC;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class PersonaBD implements Parcelable, Serializable {
    private String nombre;
    private float salario;
    private String nivel_edu;
    private int estrato;

    private String cedula;


    public PersonaBD(String cedula, String nombre, int estrato, float salario, String nivel_edu) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.estrato = estrato;
        this.salario = salario;
        this.nivel_edu = nivel_edu;
    }

    protected PersonaBD(Parcel in) {
        cedula = in.readString();
        nombre = in.readString();
        estrato = in.readInt();
        salario = in.readFloat();
        nivel_edu = in.readString();
    }

    public static final Creator<PersonaBD> CREATOR = new Creator<PersonaBD>() {
        @Override
        public PersonaBD createFromParcel(Parcel in) {
            return new PersonaBD(in);
        }

        @Override
        public PersonaBD[] newArray(int size) {
            return new PersonaBD[size];
        }
    };

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstrato() {
        return estrato;
    }

    public void setEstrato(int estrato) {
        this.estrato = estrato;
    }

    public float getSalario() {
        return salario;
    }

    public void setSalario(float salario) {
        this.salario = salario;
    }

    public String getNivel_edu() {
        return nivel_edu;
    }

    public void setNivel_edu(String nivel_edu) {
        this.nivel_edu = nivel_edu;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "cedula='" + cedula + '\'' +
                ", nombre='" + nombre + '\'' +
                ", estrato=" + estrato +
                ", salario=" + salario +
                ", nivel_edu='" + nivel_edu + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(cedula);
        dest.writeString(nombre);
        dest.writeInt(estrato);
        dest.writeFloat(salario);
        dest.writeString(nivel_edu);
    }
}
