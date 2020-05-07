package com.ed.AppPersonaBD.DME;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ed.AppPersonaBD.BDAC.ControladorBD;
import com.ed.AppPersonaBD.BDAC.PersonaBD;
import com.ed.AppPersonaBD.R;

import java.util.ArrayList;

public class ActivityEditar extends AppCompatActivity {
    int estrato,indice;
    ControladorBD controlador;
    EditText nombre,salario,cedula;
    String nivel;
    Button actualizar,cancelar;
    Spinner spnestratos,spnniveles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        cedula=findViewById(R.id.idCedula);
        nombre=findViewById(R.id.idNombre);
        salario=findViewById(R.id.idSalario); spnniveles=findViewById(R.id.SPN2); spnestratos=findViewById(R.id.SPN1);
        actualizar=findViewById(R.id.idActualizar); cancelar=findViewById(R.id.idCancelar);


        controlador= new ControladorBD(getApplicationContext());
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.Estratos,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnestratos.setAdapter(adapter);

        spnestratos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                estrato= Integer.parseInt(parent.getItemAtPosition(position).toString());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        ArrayAdapter<CharSequence> adaptador=ArrayAdapter.createFromResource(this,R.array.Niveles,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spnniveles.setAdapter(adaptador);
        spnniveles.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nivel= parent.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        Intent i = getIntent();
        indice = i.getIntExtra("indice", 0);
        ArrayList<PersonaBD> lista = controlador.obtenerRegistros();
        PersonaBD persona = lista.get(indice);
        cedula.setText(persona.getCedula());
        nombre.setText(persona.getNombre());
        salario.setText(String.valueOf(persona.getSalario()));
        actualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PersonaBD estudiant = new PersonaBD(cedula.getText().toString().trim(), nombre.getText().toString(),estrato, Float.parseFloat(salario.getText().toString().trim()),nivel);
                int retorno = controlador.actualizarRegistro(estudiant);
                if (retorno == 1) {
                    Toast.makeText(getApplicationContext(), "Exito en la Edicion", Toast.LENGTH_SHORT).show();
                    setResult(Activity.RESULT_OK);
                    finish();
                } else {
                    Toast.makeText(getApplicationContext(), "Lo sentimos, no se pudo editar.", Toast.LENGTH_SHORT).show();
                }
                limpiar();
            }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });

    }


    private void limpiar() {
        nombre.setText("");
        salario.setText("");
    }


}
