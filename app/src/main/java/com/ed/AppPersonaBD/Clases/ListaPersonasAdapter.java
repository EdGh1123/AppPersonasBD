package com.ed.AppPersonaBD.Clases;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ed.AppPersonaBD.BDAC.PersonaBD;
import com.ed.AppPersonaBD.R;

import java.util.ArrayList;

public class ListaPersonasAdapter extends BaseAdapter {
    private ArrayList<PersonaBD> lista;
    private Context contexto;
    private int layoutRecurso;

    public ListaPersonasAdapter(Context contexto, int layoutRecurso, ArrayList<PersonaBD> lista) {
        this.lista = lista;
        this.contexto = contexto;
        this.layoutRecurso = layoutRecurso;
    }

    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(contexto).inflate(layoutRecurso, null);
        }

        PersonaBD persona=lista.get(position);


        TextView cedulalis=view.findViewById(R.id.textCedula);
        TextView estratolis=view.findViewById(R.id.textEstrato);
        TextView salariolis=view.findViewById(R.id.textSalario);
        TextView nivellis=view.findViewById(R.id.textNivel);
        TextView nombrelis=view.findViewById(R.id.txtNombre);
        cedulalis.setText(persona.getCedula());
        nombrelis.setText(persona.getNombre());
        estratolis.setText(String.valueOf(persona.getEstrato()));
        salariolis.setText(String.valueOf(persona.getSalario()));
        nivellis.setText(persona.getNivel_edu());

        return view;
    }

    public void setFilter(ArrayList<PersonaBD> listapersonas){
        this.lista=new ArrayList<>();
        this.lista.addAll(listapersonas);
        notifyDataSetChanged();
    }
}
