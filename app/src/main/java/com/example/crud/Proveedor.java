package com.example.crud;

import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;

public class Proveedor {
    private String ruc;
    private String nombre_comercial;
    private String representante_legal;
    private String direccion;
    private String telefono;
    private String productos;
    private String credito;
    private String foto;
    ArrayList<Proveedor> list;

    public Proveedor() {
    }

    public Proveedor(String ruc, String nombre_comercial, String representante_legal, String direccion, String telefono, String productos, String credito) {
        this.ruc = ruc;
        this.nombre_comercial = nombre_comercial;
        this.representante_legal = representante_legal;
        this.direccion = direccion;
        this.telefono = telefono;
        this.productos = productos;
        this.credito = credito;
        this.foto = foto;
    }

    public String getRuc() {
        return ruc;
    }

    public void setRuc(String ruc) {
        this.ruc = ruc;
    }

    public String getNombre_comercial() {
        return nombre_comercial;
    }

    public void setNombre_comercial(String nombre_comercial) {
        this.nombre_comercial = nombre_comercial;
    }

    public String getRepresentante_legal() {
        return representante_legal;
    }

    public void setRepresentante_legal(String representante_legal) {
        this.representante_legal = representante_legal;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getProductos() {
        return productos;
    }

    public void setProductos(String productos) {
        this.productos = productos;
    }

    public String getCredito() {
        return credito;
    }

    public void setCredito(String credito) {
        this.credito = credito;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    //Insert
    public void CrearUsuario(Context context){
        DbHelper db = new DbHelper(context);
        String nosql = "INSERT INTO "+ BaseProveedor.TABLA_PROVEEDOR+" ("+ BaseProveedor.RUC+", " +
                ""+ BaseProveedor.RUC+", " +
                ""+ BaseProveedor.NOMBRE_COMERCIAL+", " +
                ""+ BaseProveedor.REPRESENTANTE_LEGAL+", " +
                ""+ BaseProveedor.DIRECCION+", " +
                ""+ BaseProveedor.TELEFONO+", " +
                ""+ BaseProveedor.PRODUCTOS+", " +
                ""+ BaseProveedor.CREDITO+", " +
                ""+ BaseProveedor.FOTO+") " +
                " VALUES ('"+getRuc()+"','"+getNombre_comercial()+"','"+getRepresentante_legal()+"','"+getDireccion()+"', '"+getTelefono()+"','"+getProductos()+"','"+getCredito()+"','"+getFoto()+"')";
        db.noQuery(nosql);
        db.close();
    }

    //Mostrar Datos Usuario dentro de la base
    public ArrayList<Proveedor> lista_Usuarios(Context context){
        DbHelper db = new DbHelper(context);
        String sql = "SELECT * FROM "+ BaseProveedor.TABLA_PROVEEDOR+";";
        list = new ArrayList<Proveedor>();
        Cursor cursor = db.query(sql);

        while (cursor.moveToNext()){
            Proveedor proveedorActual = new Proveedor();
            proveedorActual.setRuc("ruc: "+cursor.getString(0));
            proveedorActual.setNombre_comercial("nombre_comercial: "+cursor.getString(1));
            proveedorActual.setRepresentante_legal("representante_legal: "+cursor.getString(2));
            proveedorActual.setDireccion("direccion: "+cursor.getString(3));
            proveedorActual.setTelefono("telefono: "+cursor.getString(4));
            proveedorActual.setProductos("productos: "+cursor.getString(5));
            proveedorActual.setCredito("credito: "+cursor.getString(6));
            proveedorActual.setFoto(cursor.getString(7));
            list.add(proveedorActual);
            db.close();
        }
        return list;
    }

    //Buscar
    public void buscarUsuario(Context context, Proveedor proveedor, String id){
        DbHelper dbHelper = new DbHelper(context);
        String sql = "SELECT * FROM "+ BaseProveedor.TABLA_PROVEEDOR+" WHERE ruc='"+ruc+"';";
        Cursor cursor = dbHelper.query(sql);
        while (cursor.moveToNext()){
            proveedor.setRuc(cursor.getString(0));
            proveedor.setNombre_comercial(cursor.getString(1));
            proveedor.setRepresentante_legal(cursor.getString(2));
            proveedor.setDireccion(cursor.getString(3));
            proveedor.setTelefono(cursor.getString(4));
            proveedor.setProductos(cursor.getString(5));
            proveedor.setCredito(cursor.getString(6));
            proveedor.setFoto(cursor.getString(7));
            dbHelper.close();
        }
    }

    //Actualizar
    public void ActualizarUsuario(Context context, String ruc){
        DbHelper  dbHelper = new DbHelper(context);
        String nosql = "UPDATE "+ BaseProveedor.TABLA_PROVEEDOR+" SET " +
                " nombre_comercial='"+getNombre_comercial()+"', " +
                " representante_legal='"+getRepresentante_legal()+"', " +
                " direccion='"+getDireccion()+"'," +
                " telefono='"+getTelefono()+"', " +
                " productos='"+getProductos()+"', " +
                " credito='"+getCredito()+"', " +

                " Foto='"+getFoto()+"' " +
                " WHERE ruc='"+ruc+"';";
        dbHelper.noQuery(nosql);
        dbHelper.close();
    }

    //Eliminar
    public void EliminarUsuario(Context context, String ruc){
        DbHelper dbHelper = new DbHelper(context);
        String nosql = "DELETE FROM "+ BaseProveedor.TABLA_PROVEEDOR+" WHERE ruc='"+ruc+"';";
        dbHelper.noQuery(nosql);
        dbHelper.close();
    }




}
