package org.izv.pgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ProductsActivity extends AppCompatActivity {

    private int[] pvpLacteos = {1,2,3};
    private int[] pvpCarnes = {2,3,4};
    private int[] pvpPescados = {3,4,5};
    private int[] pvpVerduras = {4,5,6};
    private Spinner spinnerCategoria;
    private TextView pvpVenta;
    private String nomCategoria;
    private Button btAddCarrito;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        nomCategoria = intent.getStringExtra(MainActivity.CATEGORY);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.tvCategoria);
        textView.setText("Seleccione los productos de la categoria "+nomCategoria);

        Spinner spinnerCategoria = findViewById(R.id.spinnerCategoria);
        if(nomCategoria.equalsIgnoreCase("Lacteos")) {
            ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.Lacteos , android.R.layout.simple_spinner_dropdown_item);
            spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinnerCategoria.setAdapter(spinner_adapter);
        } else{
            if(nomCategoria.equalsIgnoreCase("Carnes")) {
                ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.Carnes , android.R.layout.simple_spinner_dropdown_item);
                spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategoria.setAdapter(spinner_adapter);
            } else{
                if(nomCategoria.equalsIgnoreCase("Pescados")) {
                    ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.Pescados , android.R.layout.simple_spinner_dropdown_item);
                    spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategoria.setAdapter(spinner_adapter);
                } else{
                    ArrayAdapter spinner_adapter = ArrayAdapter.createFromResource( this, R.array.Verduras , android.R.layout.simple_spinner_dropdown_item);
                    spinner_adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategoria.setAdapter(spinner_adapter);
                }
            }
        }

        initComponents();
        initEvents();


    }

    private void savePreferrences(int pvpProducto){
        SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
         int pvp = sharedPref.getInt("pvpCarrito", 0);
        editor.putInt("pvpCarrito", pvp+pvpProducto);
        MainActivity.pvpCarrito=pvp+pvpProducto;
        editor.commit();
    }

    private void initEvents() {
        if (nomCategoria.equalsIgnoreCase("Lacteos")) {
            pvpVenta.setText(""+pvpLacteos[0]);
        } else {
            if (nomCategoria.equalsIgnoreCase("Carnes")) {
                pvpVenta.setText(""+pvpCarnes[0]);
            } else {
                if (nomCategoria.equalsIgnoreCase("Pescados")) {
                    pvpVenta.setText(""+pvpPescados[0]);
                } else {
                    pvpVenta.setText(""+pvpVerduras[0]);
                }

            }
        }

        spinnerCategoria.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (nomCategoria.equalsIgnoreCase("Lacteos")) {
                    pvpVenta.setText(""+pvpLacteos[position]);
                } else {
                    if (nomCategoria.equalsIgnoreCase("Carnes")) {
                        pvpVenta.setText(""+pvpCarnes[position]);
                    } else {
                        if (nomCategoria.equalsIgnoreCase("Pescados")) {
                            pvpVenta.setText(""+pvpPescados[position]);
                        } else {
                            pvpVenta.setText(""+pvpVerduras[position]);
                        }

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        btAddCarrito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                savePreferrences(Integer.parseInt(pvpVenta.getText().toString()));
                Log.v("xyz",""+MainActivity.pvpCarrito);
            }
        });
    }

    private void initComponents() {
        spinnerCategoria = findViewById(R.id.spinnerCategoria);
        pvpVenta = findViewById(R.id.pvpVenta);
        btAddCarrito = findViewById(R.id.btAddCarrito);
    }


}
