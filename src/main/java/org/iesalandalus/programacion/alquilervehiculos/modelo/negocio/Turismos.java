package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class Turismos {
	private List<Turismo> coleccionTurismos;

	public Turismos() {
		coleccionTurismos = new ArrayList<Turismo>();
	}

	public List<Turismo> get() {
		return new ArrayList<Turismo>(coleccionTurismos);
	}
	public int getCantidad() {
		return coleccionTurismos.size();	
	}
	public void insertar(Turismo turismo) throws OperationNotSupportedException {
		if(turismo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un turismo nulo.");
		}
		if(coleccionTurismos.contains(turismo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un turismo con esa matrícula.");
		}
		if (turismo != null)
			coleccionTurismos.add(turismo);
	}
	public Turismo buscar(Vehiculo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un turismo nulo.");
		}
		if (coleccionTurismos.contains(turismo)) {
			int nuevoT = coleccionTurismos.indexOf(turismo);
			return coleccionTurismos.get(nuevoT);
		}
		return null;
	}
	public void borrar(Vehiculo turismo) throws OperationNotSupportedException {
		if(turismo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un turismo nulo.");
		}
		if(!coleccionTurismos.contains(turismo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún turismo con esa matrícula.");
		}
		coleccionTurismos.remove(turismo);
	}
}