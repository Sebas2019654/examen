package com.example.crud;

public class BaseProveedor {

    public static final String TABLA_PROVEEDOR="proveedores";
    public static final String RUC="ruc ";
    public static final String NOMBRE_COMERCIAL="nombre_comercial ";
    public static final String REPRESENTANTE_LEGAL="representante_legal ";
    public static final String DIRECCION="direccion ";
    public static final String TELEFONO="telefono ";
    public static final String PRODUCTOS="productos";
    public static final String CREDITO="credito";
    public static final String FOTO="Foto ";

    public static final String CREAR_TABLA_PROVEEDOR = " CREATE TABLE "+TABLA_PROVEEDOR+" ("+ RUC+" TEXT PRIMARY KEY, " +
            " "+NOMBRE_COMERCIAL+" TEXT,"+REPRESENTANTE_LEGAL+" TEXT, "+DIRECCION+" TEXT, "+TELEFONO+" TEXT,"+PRODUCTOS+" TEXT,"+CREDITO+" TEXT, "+FOTO+" TEXT )";



}
