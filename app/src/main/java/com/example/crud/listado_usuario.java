package com.example.crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class listado_usuario extends AppCompatActivity {


    RecyclerView recyclerView;
    ArrayList<Proveedor> listProveedor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuario);
        //Adaptar el recycler view
        listProveedor = new ArrayList<>();
        recyclerView = (RecyclerView) findViewById(R.id.listadoRecycler);
        //Le vamos a dar forma a mi recycler view
        recyclerView.setLayoutManager(new LinearLayoutManager(listado_usuario.this));
        //Llamamos a la consulta
        ConsultarUsuarios();
        //Adaptar el recycler
        AdaptadorProveedor adaptadorProveedor = new AdaptadorProveedor(listProveedor);
        recyclerView.setAdapter(adaptadorProveedor);
     }

    public void ConsultarUsuarios(){
        Proveedor proveedor = new Proveedor();
        //Guardando mi consulta en array list para mostralo en recylcer view
        listProveedor = proveedor.lista_Usuarios(listado_usuario.this);
    }


}