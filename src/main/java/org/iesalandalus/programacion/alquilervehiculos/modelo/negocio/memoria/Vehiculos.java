package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class Vehiculos implements IVehiculos {
	private List<Vehiculo> coleccionVehiculos;

	public Vehiculos() {
		coleccionVehiculos = new ArrayList<Vehiculo>();
	}

	@Override
	public List<Vehiculo> get() {
		return new ArrayList<Vehiculo>(coleccionVehiculos);
	}
	@Override
	public int getCantidad() {
		return coleccionVehiculos.size();	
	}
	public void insertar(Vehiculo turismo) throws OperationNotSupportedException {
		if(turismo == null) {
			throw new NullPointerException("ERROR: No se puede insertar un vehículo nulo.");
		}
		if(coleccionVehiculos.contains(turismo)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un vehículo con esa matrícula.");
		}
		if (turismo != null)
			coleccionVehiculos.add(turismo);
	}
	@Override
	public Vehiculo buscar(Vehiculo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: No se puede buscar un vehículo nulo.");
		}
		if (coleccionVehiculos.contains(turismo)) {
			int nuevoT = coleccionVehiculos.indexOf(turismo);
			return coleccionVehiculos.get(nuevoT);
		}
		return null;
	}
	@Override
	public void borrar(Vehiculo turismo) throws OperationNotSupportedException {
		if(turismo == null) {
			throw new NullPointerException("ERROR: No se puede borrar un vehículo nulo.");
		}
		if(!coleccionVehiculos.contains(turismo)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún vehículo con esa matrícula.");
		}
		coleccionVehiculos.remove(turismo);
	}
}