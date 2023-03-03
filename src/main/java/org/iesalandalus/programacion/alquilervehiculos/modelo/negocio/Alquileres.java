package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;

public class Alquileres {
	private List<Alquiler> coleccionAlquileres;
	public Alquileres() {
		coleccionAlquileres = new ArrayList<Alquiler>();
	}
	public List<Alquiler> get() {
		return new ArrayList<Alquiler>(coleccionAlquileres);
	}
	public List<Alquiler> get(Cliente cliente){
		List<Alquiler> lista = new ArrayList<>();
		for(Alquiler alquiler : coleccionAlquileres) {
			if (cliente == alquiler.getCliente()) {
				lista.add(alquiler);
			}
		}
		return lista;
	}
	public List<Alquiler> get(Vehiculo turismo){
		List<Alquiler> lista = new ArrayList<>();
		for(Alquiler alquiler : coleccionAlquileres) {
			if (turismo == alquiler.getTurismo()) {
				lista.add(alquiler);
			}
		}
		return lista;
	}
	public int getCantidad() {
		return coleccionAlquileres.size();	
	}
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if(alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		/*
		 * alquiler = buscar(alquiler); if(alquiler != null) { throw new
		 * OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver."
		 * ); }
		 */
		comprobarAlquiler(alquiler.getCliente(), alquiler.getTurismo(), alquiler.getFechaAlquiler());
			coleccionAlquileres.add(alquiler);
	}
	private void comprobarAlquiler(Cliente cliente, Vehiculo turismo, LocalDate fechaAlquiler) throws OperationNotSupportedException {
		for(Alquiler alquiler : coleccionAlquileres) {
			if(alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			}
			if(alquiler.getTurismo().equals(turismo) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			}
			if(alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() != null) {
				if(alquiler.getFechaDevolucion().compareTo(fechaAlquiler) >= 0) {
					throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
				}
			}
			if(alquiler.getTurismo().equals(turismo) && alquiler.getFechaDevolucion() != null) {
				if(alquiler.getFechaDevolucion().compareTo(fechaAlquiler) >= 0) {
					throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
				}
			}
		}
	}
	public void devolver(Alquiler alquiler, LocalDate fechaAlquiler) throws OperationNotSupportedException {
		if(alquiler == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler nulo.");
		}
		if(!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		alquiler.devolver(alquiler.getFechaDevolucion());
	}
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if(alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if(!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(alquiler);
	}
	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		if (coleccionAlquileres.contains(alquiler)) {
			int nuevoA = coleccionAlquileres.indexOf(alquiler);
			return coleccionAlquileres.get(nuevoA);
		}
		return null;
	}
}
