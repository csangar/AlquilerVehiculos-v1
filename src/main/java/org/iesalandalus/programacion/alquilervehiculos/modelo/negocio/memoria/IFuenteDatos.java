package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

public interface IFuenteDatos {

	IClientes crearClientes();

	IVehiculos crearVehiculos();

	IAlquileres crearAlquileres();

}