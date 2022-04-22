package com.example.crud;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class EditarProveedor extends AppCompatActivity {

    EditText txtruc, txtnombrecomercial, txtrepresentantelegal, txtdireccion, txttelefono, txtproductos, txtcredito, txtBuscar;
    ImageView imgFoto;
    Button btnActualizar, btnBuscar, btnEliminar;
    ImageButton btnCargarFoto;
    String imagenBase64="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        BuscarUsuario();
        SeleccionarFoto();
        ActualizarUsuario();
        EliminarUsuario();
    }


    public void BuscarUsuario(){
        btnBuscar = findViewById(R.id.btnBuscar);
        txtBuscar = findViewById(R.id.editTextBuscarID);
        txtruc = findViewById(R.id.editTextRucEdit);
        txtnombrecomercial = findViewById(R.id.editTextNombreComercialEDit);
        txtrepresentantelegal = findViewById(R.id.editTextRepresentanteEdit);
        txtdireccion = findViewById(R.id.editTextDireccionEdit);
        txttelefono = findViewById(R.id.editTextTelefonoEdit);
        txtproductos = findViewById(R.id.editTextProductosEdit);
        txtcredito = findViewById(R.id.editTextCreditosEdit2);
        imgFoto = findViewById(R.id.imgFotoUsuarioEdit);
        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Proveedor proveedorBuscar = new Proveedor();
                proveedorBuscar.buscarUsuario(EditarProveedor.this, proveedorBuscar, txtBuscar.getText().toString());
                txtruc.setText(proveedorBuscar.getRuc());
                txtnombrecomercial.setText(proveedorBuscar.getNombre_comercial());
                txtrepresentantelegal.setText(proveedorBuscar.getRepresentante_legal());
                txtdireccion.setText(proveedorBuscar.getDireccion());
                txttelefono.setText(proveedorBuscar.getTelefono());
                txtproductos.setText(proveedorBuscar.getProductos());
                txtcredito.setText(proveedorBuscar.getCredito());
                //Decodificamos la foto subida a la base de datos
                byte[] bytes = Base64.decode(proveedorBuscar.getFoto(), Base64.DEFAULT);
                //Mapeado de la foto
                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                imgFoto.setImageBitmap(bitmap);
            }
        });
    }


    public void ActualizarUsuario(){
        btnActualizar = findViewById(R.id.btnActualizar);
        txtruc = findViewById(R.id.editTextRucEdit);
        txtnombrecomercial = findViewById(R.id.editTextNombreComercialEDit);
        txtrepresentantelegal = findViewById(R.id.editTextRepresentanteEdit);
        txtdireccion = findViewById(R.id.editTextDireccionEdit);
        txttelefono = findViewById(R.id.editTextTelefonoEdit3);
        txtproductos = findViewById(R.id.editTextProductosEdit);
        txtcredito = findViewById(R.id.editTextCreditosEdit2);
        imgFoto = findViewById(R.id.imgFotoUsuarioEdit);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Proveedor proveedorActual = new Proveedor();
                proveedorActual.setRuc(txtruc.getText().toString());
                proveedorActual.setNombre_comercial(txtnombrecomercial.getText().toString());
                proveedorActual.setRepresentante_legal(txtrepresentantelegal.getText().toString());
                proveedorActual.setDireccion(txtdireccion.getText().toString());
                proveedorActual.setTelefono(txttelefono.getText().toString());
                proveedorActual.setProductos(txtproductos.getText().toString());
                proveedorActual.setCredito(txtcredito.getText().toString());
                proveedorActual.setFoto(imagenBase64);
                proveedorActual.ActualizarUsuario(EditarProveedor.this, txtruc.getText().toString());
                Intent intent = new Intent(EditarProveedor.this, listado_usuario.class);
                startActivity(intent);
            }
        });
    }


    public String Base64Imagen(Uri image){
        String Base64s="";
        try {
            //Iniciamos el mapeo de la imagen
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), image);
            //Iniciamos el array de bytes
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            //Comprimimos bitmap
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
            //Iniciamos el array de bytes
            byte[] bytes = stream.toByteArray();
            //Obtenemos el base64
            Base64s = Base64.encodeToString(bytes, Base64.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Base64s;
    }

    public void SeleccionarFoto(){
        btnCargarFoto = findViewById(R.id.BtnCArgarFotoEdit);
        imgFoto = findViewById(R.id.imgFotoUsuarioEdit);
        btnCargarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent.createChooser(intent, "Seleccion Foto"), 10);
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Uri imageuri = data.getData();
            imgFoto.setImageURI(imageuri);
            imagenBase64 = Base64Imagen(imageuri);
        }
    }


    public void EliminarUsuario() {
        btnEliminar = findViewById(R.id.btnEliminar);
        txtruc = findViewById(R.id.editTextRucEdit);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Proveedor proveedorEliminar = new Proveedor();
                proveedorEliminar.EliminarUsuario(EditarProveedor.this, txtruc.getText().toString());
                Toast.makeText(EditarProveedor.this, "Elimino Correctamente", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(EditarProveedor.this, listado_usuario.class);
                startActivity(intent);
            }
        });
    }

}