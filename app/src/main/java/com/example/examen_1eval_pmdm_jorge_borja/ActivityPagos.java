package com.example.examen_1eval_pmdm_jorge_borja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityPagos extends AppCompatActivity {

    private TextView txt_totalMain = null;
    private TextView txt_comision = null;
    private TextView txt_totalFinal = null;
    private RadioButton rb_tarjeta = null;
    private RadioButton rb_paypal = null;



    private double precioTotal = 0.0;
    private double precioFinal = 0.0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagos);
        txt_totalMain = (TextView) findViewById(R.id.txt_totalMain);
        txt_comision = (TextView) findViewById(R.id.txt_comision);
        txt_totalFinal = (TextView) findViewById(R.id.txt_totalFinal);
        rb_tarjeta = (RadioButton) findViewById(R.id.rb_tarjeta);
        rb_paypal = (RadioButton) findViewById(R.id.rb_paypal);

        Intent intent = getIntent();
        if (intent != null)
        {
            precioTotal = (Double) intent.getSerializableExtra(MainActivity.EXTRA_TOTAL);
        }

        txt_totalMain.setText(String.valueOf(precioTotal));
    }

    public void comision(View view){
        RadioButton boton = (RadioButton) view;
        if(boton.isChecked()) {
            switch (boton.getId()) {
                case R.id.rb_tarjeta:
                    precioFinal = precioTotal;
                    txt_comision.setText("0");
                    txt_totalFinal.setText(String.valueOf(precioFinal));
                    break;
                case R.id.rb_paypal:
                    precioFinal = precioTotal + 2;
                    txt_comision.setText("2");
                    txt_totalFinal.setText(String.valueOf(precioFinal));
                    break;
            }
        }

    }

    public void pagoExitoso(View view) {
        Toast.makeText(this, "Pago realizado correctamente",Toast.LENGTH_SHORT).show();

    }
}