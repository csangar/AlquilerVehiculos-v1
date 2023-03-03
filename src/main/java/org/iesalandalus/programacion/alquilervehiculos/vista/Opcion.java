package org.iesalandalus.programacion.alquilervehiculos.vista;

public enum Opcion {
	
	SALIR("0:Salir"),
    INSERTAR_CLIENTE("1:Inserta un cliente"),
    INSERTAR_TURISMO("2:Inserta un turismo"),
    INSERTAR_ALQUILER("3:Inserta un alquiler"),
    BUSCAR_CLIENTE("4:Busca un cliente"),
    BUSCAR_TURISMO("5:Busca un turismo"),
    BUSCAR_ALQUILER("6:Busca un alquiler"),
    MODIFICAR_CLIENTE("7:Modifica un cliente"),
    DEVOLVER_ALQUILER("8:Devolve un alquiler"),
    BORRAR_CLIENTE("9:Borra un cliente"),
    BORRAR_TURISMO("10:Borra un turismo"),
    BORRAR_ALQUILER("11:Borra un alquiler"),
    LISTAR_CLIENTES("12:Lista los clientes"),
    LISTAR_TURISMOS("13:Lista los turismos"),
    LISTAR_ALQUILERES("14:Lista los alquileres"),
    LISTAR_ALQUILERES_CLIENTE("15:Lista los alquileres de un cliente"),
    LISTAR_ALQUILERES_TURISMO("16:Lista los alquileres de un turismo");

    private String cadena;

    private static boolean esOrdinalValido(int ordinal) {
        boolean validar = false;
        if( ordinal >= 0 && ordinal < Opcion.values().length) {
            validar = true;
        }
        return validar;
    }
    public static Opcion get(int ordinal) {
        Opcion opcion = null;
        if(esOrdinalValido(ordinal)) {
            opcion = Opcion.values()[ordinal];
        }
        return opcion;
    }
    private Opcion(String cadena) {
        this.cadena = cadena;
    }

    public String toString() {
        return cadena;
    }
}
