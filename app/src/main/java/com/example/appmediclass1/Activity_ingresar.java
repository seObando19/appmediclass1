package com.example.appmediclass1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Activity_ingresar extends AppCompatActivity {
    EditText correo,clave;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);
        correo=(EditText)findViewById(R.id.etcorreo);
        clave=(EditText)findViewById(R.id.etclave);
        mAuth = FirebaseAuth.getInstance();
    }

    public void inisesion(View view) {
        final String usuario=correo.getText().toString();
        String password=clave.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(usuario).matches())
        {
            correo.setError("Debe ingresar un email valido");
            return;
        }
        if(password.length()<6)
        {
            clave.setError("La contraseña debe tener 6 caracteres o mas");
        }
        else if (!TextUtils.isEmpty(usuario)&&TextUtils.isEmpty(password))
        {
            Toast.makeText(this, "Debe registrar todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }
        mAuth.signInWithEmailAndPassword(usuario, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Toast.makeText(Main3Activity_ingresar.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(Activity_ingresar.this,Activity_citas.class);
                            intent.putExtra("correo",usuario);
                            startActivity(intent);
                            correo.setText("");
                            clave.setText("");
                            finish();
                        } else {
                            Toast.makeText(Activity_ingresar.this, "No se pudo acceder al usuario", Toast.LENGTH_SHORT).show();
                            correo.setText("");
                            clave.setText("");
                        }

                        // ...
                    }
                });

    }
}
