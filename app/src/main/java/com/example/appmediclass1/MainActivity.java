package com.example.appmediclass1;

import android.net.Uri;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements Frament_registro.OnFragmentInteractionListener, Frament_ingreso.OnFragmentInteractionListener{
    Frament_ingreso Ingreso;
    Frament_registro Registro;
    Button btnreg, btnsec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ingreso = new Frament_ingreso() ;
        Registro =new Frament_registro();
        btnreg=findViewById(R.id.btnlogin);
        btnsec=findViewById(R.id.btnseccion);

        getSupportFragmentManager().beginTransaction().add(R.id.frameLayoutContainer,Registro).commit();

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    public void cambiarFragment(View view) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()){
            case R.id.btnlogin:
                fragmentTransaction.replace(R.id.frameLayoutContainer,Registro).commit();
                break;
            case R.id.btnseccion:
                fragmentTransaction.replace(R.id.frameLayoutContainer,Ingreso).commit();
                break;
        }
    }


}
