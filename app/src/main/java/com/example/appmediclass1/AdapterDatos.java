package com.example.appmediclass1;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterDatos extends RecyclerView.Adapter<AdapterDatos.ViewHolderDatos> {

    ArrayList<String>listDatos;

    public AdapterDatos(ArrayList<String> listDatos) {
        this.listDatos = listDatos;
    }

    @NonNull
    @Override
    public ViewHolderDatos onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_lisactivity,null,false);
        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderDatos viewHolderDatos, int i) {
        /*viewHolderDatos.nom.setText(listDatos .get(i).getNombre());
        viewHolderDatos.esp.setText(listDatos .get(i).getEspecialista());
        viewHolderDatos.fecha.setText(listDatos .get(i).getFecha());
        viewHolderDatos.hora.setText(listDatos .get(i).getHora());
        viewHolderDatos.imagen.setImageResource(listDatos .get(i).getFoto());*/
        viewHolderDatos.asignarDatos(listDatos.get(i));
    }

    @Override
    public int getItemCount() {
        return listDatos .size();
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        ImageView imagen;
        TextView nom,esp,fecha,hora;

        public ViewHolderDatos(@NonNull View itemView) {
            super(itemView);
            imagen=itemView.findViewById(R.id.idImagen);
            nom=itemView.findViewById(R.id.idNombre);
            esp=itemView.findViewById(R.id.idespec);
            fecha=itemView.findViewById(R.id.idfecha);
            hora=itemView.findViewById(R.id.idhora);
        }

        public void asignarDatos(String datos) {
            imagen.setImageResource(0);
            nom.setText(datos);
            esp.setText(datos);
            fecha.setText(datos);
            hora.setText(datos);
        }
    }
}
