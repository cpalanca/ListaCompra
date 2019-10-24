package org.izv.pgc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class PayActivity extends AppCompatActivity {

    private TextView tvPvpTotal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        tvPvpTotal = findViewById(R.id.tvPvpTotal);
        tvPvpTotal.setText(getIntent().getStringExtra("pvpTotal"));
        resetPreferrences();
    }

    private void resetPreferrences(){

        SharedPreferences prefs = getSharedPreferences("ProductsActivity",
                Context.MODE_PRIVATE);

        SharedPreferences.Editor editor = prefs.edit();

        editor.putInt("pvpCarrito", 0);
        editor.commit();

        Log.v("xyz",""+prefs.getInt("pvpCarrito", 0));


    }

}
