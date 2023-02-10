package com.example.examen_1eval_pmdm_jorge_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    public final int entradasDisponibles = 5;
    public static final String EXTRA_TOTAL = "com.example.examen_1eval_Jorge_Borja.precioTotal";

    //Widgets
    private TextView txt_numeroEntradasDisponibles = null;
    private Spinner sp_dia = null;
    private EditText edt_cantidadEntradasMain = null;
    private TextView txt_precioEntradas = null;
    private TextView txt_descuentoAplicado = null;
    private TextView txt_total = null;

    //Variables
    private String dia_seleccionado = null;
    private int numeroEntradas = 0;
    private double precioEntradas = 0.0;
    private double descuento = 0.0;
    private double precioTotal = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_numeroEntradasDisponibles = (TextView) findViewById(R.id.txt_numeroEntradasDisponibles);
        sp_dia = (Spinner) findViewById(R.id.sp_dia);
        edt_cantidadEntradasMain = (EditText) findViewById(R.id.edt_cantidadEntradasMain);
        txt_precioEntradas = (TextView) findViewById(R.id.txt_precioEntradas);
        txt_descuentoAplicado = (TextView) findViewById(R.id.txt_descuento);
        txt_total = (TextView) findViewById(R.id.txt_total);


        txt_numeroEntradasDisponibles.setText(String.valueOf(entradasDisponibles));


        if(sp_dia != null){
            sp_dia.setOnItemSelectedListener(this);
            ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                    R.array.lista_item_spinner, R.layout.item_spinner);
            adapter.setDropDownViewResource (R.layout.item_spinner_seleccionado);
            sp_dia.setAdapter(adapter);
        }

    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        dia_seleccionado = adapterView.getItemAtPosition(i).toString();
        Log.i("item_diaSeleccionado", dia_seleccionado);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        dia_seleccionado = "Lunes";
        Log.i("item_diaSeleccionado", dia_seleccionado);
    }


    public void calcular_precios(View view) {

        String numeroEntradasString = String.valueOf(edt_cantidadEntradasMain.getText());

        try
        {
            numeroEntradas = Integer.valueOf(numeroEntradasString);
        }
        catch (NumberFormatException e)
        {
            edt_cantidadEntradasMain.setError("Debes poner un numero, no puede estar en blanco o ser letras");
            return;
        }

        if(numeroEntradas < 1 || numeroEntradas > entradasDisponibles)
        {
            edt_cantidadEntradasMain.setError("Debes poner un numero entre 1 y las entradas disponibles");
            return;
        }

        if(dia_seleccionado.equalsIgnoreCase("Sabado") || dia_seleccionado.equalsIgnoreCase("Domingo"))
        {
            precioEntradas = numeroEntradas * 35;
        }
        else
        {
            precioEntradas = numeroEntradas * 30;
        }

        if(numeroEntradas >= 4)
        {
            descuento = precioEntradas * 0.1;
            precioTotal = precioEntradas - descuento;
        }
        else
        {
            precioTotal = precioEntradas;
        }

        Log.i("variables", String.valueOf(precioEntradas));
        Log.i("variables", String.valueOf(descuento));
        Log.i("variables", String.valueOf(precioTotal));

        txt_precioEntradas.setText(String.valueOf(precioEntradas));
        txt_total.setText(String.valueOf(precioTotal));
        txt_descuentoAplicado.setText(String.valueOf(descuento));

    }

    public void irSegundaPantalla(View view) {
        Intent intent = new Intent(this, ActivityPagos.class);
        intent.putExtra(EXTRA_TOTAL,precioTotal);
        startActivity(intent);
    }
}