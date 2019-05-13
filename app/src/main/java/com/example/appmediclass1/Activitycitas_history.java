package com.example.appmediclass1;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class Activitycitas_history extends AppCompatActivity {

    ArrayList<String>listDatos;
    RecyclerView recyclercita;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitycitas_history);
        listDatos = new ArrayList<String>();
        recyclercita=findViewById(R.id.recyclerId);
        recyclercita.setLayoutManager(new LinearLayoutManager(this));
        String corr=getIntent().getStringExtra("c");
        db = FirebaseFirestore.getInstance();



        db.collection("Citas_medicas")
                .whereEqualTo("correo", corr)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {

                            listDatos = new ArrayList<String>();

                            for (QueryDocumentSnapshot document : task.getResult()) {

                                listDatos.add(document.getData().toString());
                                AdapterDatos adapter=new AdapterDatos(listDatos) ;
                                recyclercita.setAdapter(adapter);
                            }
                        } else {
                            Log.d("SOP", "Error getting documents: ", task.getException());
                            Toast.makeText(Activitycitas_history.this, "Ingreso de datos incorrecto", LENGTH_SHORT).show();

                        }
                    }
                });

        //llenarCitas();

    }

    private void llenarCitas() {
        //listaCitas.add(new Citas(""))
    }
}
