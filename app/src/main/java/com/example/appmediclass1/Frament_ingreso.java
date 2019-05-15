package com.example.appmediclass1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Frament_ingreso.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Frament_ingreso#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Frament_ingreso extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    EditText correo,clave;
    private FirebaseAuth mAuth;
    Button btnAcess;
    View view;

    private OnFragmentInteractionListener mListener;

    public Frament_ingreso() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Frament_ingreso.
     */
    // TODO: Rename and change types and number of parameters
    public static Frament_ingreso newInstance(String param1, String param2) {
        Frament_ingreso fragment = new Frament_ingreso();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_ingreso,container,false);
        correo=view.findViewById(R.id.etcorreo);
        clave=view.findViewById(R.id.etclave);
        mAuth = FirebaseAuth.getInstance();
        btnAcess=view.findViewById(R.id.btnacceso);
        btnAcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usuario=correo.getText().toString();
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
                    Toast.makeText(getContext(), "Debe registrar todos los campos", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAuth.signInWithEmailAndPassword(usuario, password)
                        .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    //Toast.makeText(Main3Activity_ingresar.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                                    Intent intent= new Intent(getContext(),Activity_citas.class);
                                    intent.putExtra("correo",usuario);

                                    startActivity(intent);
                                    correo.setText("");
                                    clave.setText("");

                                } else {
                                    correo.setText("");
                                    clave.setText("");
                                    Toast.makeText(getContext(), "No se pudo acceder al usuario", Toast.LENGTH_SHORT).show();
                                }

                                // ...
                            }
                        });


            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
