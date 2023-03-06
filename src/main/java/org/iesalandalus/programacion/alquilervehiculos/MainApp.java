package org.iesalandalus.programacion.alquilervehiculos;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.Modelo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.ModeloCascada;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class MainApp {

	public static void main(String[] args) throws OperationNotSupportedException {
		// Ánimo!!!!
		Vista vista = new Vista();
		//Modelo modelo = new ModeloCascada(FactoriaFuenteDatos.MEMORIA.crear());
		Modelo modelo = new ModeloCascada();
		Controlador controlador = new Controlador(vista, modelo);
		controlador.comenzar();
	}
}
