package com.example.appmediclass1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_registar extends AppCompatActivity {
    EditText usuariotv,pass;
    Button btn_ing;
    private FirebaseAuth mAuth;
    private EditText fech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar);
        mAuth = FirebaseAuth.getInstance();
        usuariotv=(EditText) findViewById(R.id.correo);
        pass=(EditText) findViewById(R.id.etclave);
        mAuth = FirebaseAuth.getInstance();
        //fech=(EditText)findViewById(R.id.);
    }

    public void acceso(View view) {
        String usuario=usuariotv.getText().toString();
        final String password=pass.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(usuario).matches())
        {
            usuariotv.setError("Debe ingresar un email valido");
            return;
        }
        if(password.length()<6)
        {
            pass.setError("La contraseña debe tener 6 caracteres o mas");
        }
        else if (!TextUtils.isEmpty(usuario)&&TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Debe registrar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(usuario, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(Activity_registar.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(Activity_registar.this,Activity_citas.class);
                            usuariotv.setText("");
                            pass.setText("");
                            startActivity(intent);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(Activity_registar.this, "No se pudo registar el usuario", Toast.LENGTH_SHORT).show();
                            usuariotv.setText("");
                            pass.setText("");
                        }

                        // ...
                    }
                });
    }
}
