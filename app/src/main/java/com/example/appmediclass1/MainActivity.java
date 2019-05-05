package com.example.appmediclass1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registar(View view) {
        Intent intent=new Intent(MainActivity.this, Activity_registar.class);
        startActivity(intent);
    }

    public void sesion(View view) {
        Intent intent=new Intent(MainActivity.this, Activity_ingresar.class);
        startActivity(intent);
    }

    public void fragment(View view) {
        Intent intent=new Intent(MainActivity.this,Activity_fragments.class);
        startActivity(intent);
    }
}
