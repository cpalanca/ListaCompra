package org.izv.pgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btCategoria, btPagar;
    private RadioButton rbLacteos, rbCarnes, rbVerduras, rbpescados;
    private String nomCategoria;
    private TextView tvTotal;
    public static final String CATEGORY = "Categoria";
    public static int pvpCarrito;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inictComponents();
        readPreferences();
        initEvents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        readPreferences();
        tvTotal.setText(""+pvpCarrito);
    }

    private void readPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("ProductsActivity",Context.MODE_PRIVATE);
        pvpCarrito = sharedPreferences.getInt("pvpCarrito", 0);
        Log.v("xyz",""+pvpCarrito);
    }




    private void initEvents() {

        btCategoria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irActividad();
            }
        });
        tvTotal.setText(""+pvpCarrito);
        btPagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irCaja(tvTotal.getText().toString());
            }
        });
    }

    private void irCaja(String total) {
        Intent intent = new Intent(this, PayActivity.class);
        intent.putExtra("pvpTotal", total);
        startActivity(intent);
    }

    private void irActividad() {
        nomCategoria = checkSelectedItem();
        Intent intent = new Intent(this, ProductsActivity.class);
        intent.putExtra(CATEGORY, nomCategoria);
        startActivity(intent);


    }

    private String checkSelectedItem() {
        if(rbLacteos.isChecked())
            return "Lacteos";
        else
            if(rbCarnes.isChecked())
                return "Carnes";
            else
                if(rbpescados.isChecked())
                    return "Pescados";
                else
                    return "Verduras";
    }

    private void inictComponents() {
        btCategoria = findViewById(R.id.btCategoria);
        rbLacteos = findViewById(R.id.rbLacteos);
        rbCarnes = findViewById(R.id.rbCarnes);
        rbVerduras = findViewById(R.id.rbVerduras);
        rbpescados = findViewById(R.id.rbPescados);
        tvTotal = findViewById(R.id.tvTotal);
        btPagar = findViewById(R.id.btPagar);
    }
}
