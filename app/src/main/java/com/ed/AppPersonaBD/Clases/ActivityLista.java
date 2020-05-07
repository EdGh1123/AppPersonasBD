package com.ed.AppPersonaBD.Clases;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.ed.AppPersonaBD.BDAC.ControladorBD;
import com.ed.AppPersonaBD.BDAC.PersonaBD;
import com.ed.AppPersonaBD.DME.ActivityEditar;
import com.ed.AppPersonaBD.R;

import java.util.ArrayList;

public class ActivityLista extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ControladorBD controlador; ArrayList<PersonaBD> listaPersonas;  ListaPersonasAdapter adapter; SearchView buscador; ListView lista;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        controlador = new ControladorBD(getApplicationContext());
        lista = findViewById(R.id.idListview);
        listaPersonas = controlador.obtenerRegistros();

        adapter = new ListaPersonasAdapter(getApplicationContext(), R.layout.item_list, listaPersonas);
        lista.setAdapter(adapter);

        registerForContextMenu(lista);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                ArrayList<PersonaBD> listaPersons = controlador.obtenerRegistros();
                ListaPersonasAdapter adaptador = new ListaPersonasAdapter(getApplicationContext(), R.layout.item_list, listaPersons);
                lista.setAdapter(adaptador);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(getApplicationContext(), "se ha cancelado la edicion", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.menu_lista, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        switch (item.getItemId()) {
            case R.id.IdModificar:
                modificarRegistro(menuInfo.position);
                return true;
            case R.id.IdEliminar:
                borrarRegistro(menuInfo.position);
                return true;
        }
        return super.onContextItemSelected(item);
    }

    private void modificarRegistro(int posicion) {
        Intent intent = new Intent(this, ActivityEditar.class);
        intent.putExtra("indice", posicion);
        startActivityForResult(intent, 2);
    }

    private void borrarRegistro(int posicion) {
        int retorno = controlador.borrarRegistro(listaPersonas.get(posicion));
        if (retorno == 1) {
            Toast.makeText(getApplicationContext(), "se ha eliminado el dato.", Toast.LENGTH_SHORT).show();
            listaPersonas = controlador.obtenerRegistros();
            adapter = new ListaPersonasAdapter(getApplicationContext(), R.layout.item_list, listaPersonas);
            lista.setAdapter(adapter);
        } else {
            Toast.makeText(getApplicationContext(), "error al eliminar", Toast.LENGTH_SHORT).show();
        }
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_searchin, menu);
        MenuItem item= menu.findItem(R.id.idbuscador);
        SearchView searchView=(SearchView) MenuItemCompat.getActionView(item);
        searchView.setOnQueryTextListener(this);
        MenuItemCompat.setOnActionExpandListener(item, new MenuItemCompat.OnActionExpandListener() {
            @Override
            public boolean onMenuItemActionExpand(MenuItem item) {

                return false;
            }

            @Override
            public boolean onMenuItemActionCollapse(MenuItem item) {
                adapter.setFilter(listaPersonas);
                return true;
            }
        });
        return true;
    }


    public boolean onQueryTextSubmit(String query) {
        return false;
    }


    public boolean onQueryTextChange(String newText) {
        try {

            ArrayList<PersonaBD>listafiltrada=filter(listaPersonas,newText);
            adapter.setFilter(listafiltrada);

        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }
    private ArrayList<PersonaBD> filter(ArrayList<PersonaBD> personas, String texto){
        ArrayList<PersonaBD>listaFiltrada=new ArrayList<>();
        try{
            texto=texto.toLowerCase().trim();

            for(PersonaBD perso : personas){
                String cod=perso.getCedula().toString();
                if (cod.contains(texto)){
                    listaFiltrada.add(perso);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return listaFiltrada;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
