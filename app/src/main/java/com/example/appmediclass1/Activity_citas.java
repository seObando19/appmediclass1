package com.example.appmediclass1;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Activity_citas extends AppCompatActivity {
    private static final String CERO ="0";
    private static final String DOS_PUNTOS=":";
    private static final String BARRA="/";
    Calendar calendar=Calendar.getInstance();
    DatePickerDialog datePickerDialog;
    TimePickerDialog timePickerDialog;
    private EditText fech,hora;
    Spinner especialidaad;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_citas);
        fech=(EditText)findViewById(R.id.fecha);
        hora=(EditText)findViewById(R.id.hora);
        especialidaad=(Spinner)findViewById(R.id.spinner_especialidad);

        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(this,R.array.combo_especialistas,android.R.layout.simple_spinner_item);

        especialidaad.setAdapter(adapter);
        setupActionBar();

    }
    private void setupActionBar()
    {
        ActionBar actionBar=getSupportActionBar();
        if(actionBar != null)
        {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Atras");
        }
    }

    public void datepicker(View view) {

        int year=calendar.get(Calendar.YEAR);
        int month=calendar.get(Calendar.MONTH);
        final int day=calendar.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(
                Activity_citas.this,R.style.Dialogtheme, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fech.setText(year + "/" + month + "/" + day);
            }
        },year,month,day);
        datePickerDialog.show();
    }

    public void timepicker(View view) {
        final int hour=calendar.get(Calendar.HOUR_OF_DAY);
        final int minutes=calendar.get(Calendar.MINUTE);
        timePickerDialog=new TimePickerDialog(
                Activity_citas.this,R.style.Dialogtheme, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                String AM_PM;
                if(hourOfDay < 12) {
                    AM_PM = "a.m.";
                } else {
                    AM_PM = "p.m.";
                }
                hora.setText(hour + DOS_PUNTOS + minutes + " " + AM_PM);
            }
        }, hour,minutes,false);
        timePickerDialog.show();
    }

    public void asignar_Cita(View view) {
        db = FirebaseFirestore.getInstance();
        String espc=(especialidaad.getSelectedItem().toString());
        String f=(fech.getText().toString());
        String h=(hora.getText().toString());
        final String user=getIntent().getStringExtra("correo");
        // Add a new document with a generated id.
        Map<String, Object> data = new HashMap<>();
        data.put("correo", user);
        data.put("especialista",espc);
        data.put("fecha",f);
        data.put("hora",h);

        db.collection("Citas_medicas")
                .add(data)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                        Toast.makeText(Activity_citas.this, "Ingreso exitoso", Toast.LENGTH_SHORT).show();
                        fech.setText("");
                        hora.setText("");
                        especialidaad.setSelection(0);
                        Intent intent=new Intent(Activity_citas.this,Activitycitas_history.class);
                        intent.putExtra("correo",user);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                        Toast.makeText(Activity_citas.this, "Ingreso erroneo", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
