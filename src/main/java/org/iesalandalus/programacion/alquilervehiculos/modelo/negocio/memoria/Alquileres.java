package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.IAlquileres;

public class Alquileres implements IAlquileres {
	private List<Alquiler> coleccionAlquileres;

	public Alquileres() {
		coleccionAlquileres = new ArrayList<>();
	}

	@Override
	public List<Alquiler> get() {
		return new ArrayList<>(coleccionAlquileres);
	}

	@Override
	public List<Alquiler> get(Cliente cliente) {
		List<Alquiler> lista = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (cliente.equals(alquiler.getCliente())) {
				lista.add(alquiler);
			}
		}
		return lista;
	}

	@Override
	public List<Alquiler> get(Vehiculo turismo) {
		List<Alquiler> lista = new ArrayList<>();
		for (Alquiler alquiler : coleccionAlquileres) {
			if (turismo.equals(alquiler.getVehiculo())) {
				lista.add(alquiler);
			}
		}
		return lista;
	}

	@Override
	public int getCantidad() {
		return coleccionAlquileres.size();
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede insertar un alquiler nulo.");
		}
		comprobarAlquiler(alquiler.getCliente(), alquiler.getVehiculo(), alquiler.getFechaAlquiler());
		coleccionAlquileres.add(alquiler);
	}

	private void comprobarAlquiler(Cliente cliente, Vehiculo vehiculo, LocalDate fechaAlquiler)
			throws OperationNotSupportedException {
		for (Alquiler alquiler : coleccionAlquileres) {
			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene otro alquiler sin devolver.");
			}
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() == null) {
				throw new OperationNotSupportedException("ERROR: El turismo está actualmente alquilado.");
			}
			if (alquiler.getCliente().equals(cliente) && alquiler.getFechaDevolucion() != null
					&& alquiler.getFechaDevolucion().compareTo(fechaAlquiler) >= 0) {
				throw new OperationNotSupportedException("ERROR: El cliente tiene un alquiler posterior.");
			}
			if (alquiler.getVehiculo().equals(vehiculo) && alquiler.getFechaDevolucion() != null
					&& alquiler.getFechaDevolucion().compareTo(fechaAlquiler) >= 0) {
				throw new OperationNotSupportedException("ERROR: El turismo tiene un alquiler posterior.");
			}
		}
	}

	@Override
	public void devolver(Cliente cliente, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un cliente nulo.");
		}
		Alquiler alquilerAbierto = getAlquilerAbierto(cliente);
		if (alquilerAbierto == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese cliente.");
		}
		alquilerAbierto.devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Cliente cliente) {
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();
		Alquiler alquilerAbierto = null;
		while (iterador.hasNext() && alquilerAbierto == null) {
			Alquiler alquilerTemporal = iterador.next();
			if (alquilerTemporal.getCliente().equals(cliente) && alquilerTemporal.getFechaDevolucion() == null) {
				alquilerAbierto = alquilerTemporal;
			}
		}
		return alquilerAbierto;
	}

	public void devolver(Vehiculo vehiculo, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No se puede devolver un alquiler de un vehículo nulo.");
		}
		Alquiler alquilerAbierto = getAlquilerAbierto(vehiculo);
		if (alquilerAbierto == null) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler abierto para ese vehículo.");
		}
		alquilerAbierto.devolver(fechaDevolucion);
	}

	private Alquiler getAlquilerAbierto(Vehiculo vehiculo) {
		Iterator<Alquiler> iterador = coleccionAlquileres.iterator();
		Alquiler alquilerAbierto = null;
		while (iterador.hasNext() && alquilerAbierto == null) {
			Alquiler alquilerTemporal = iterador.next();
			if (alquilerTemporal.getVehiculo().equals(vehiculo) && alquilerTemporal.getFechaDevolucion() == null) {
				alquilerAbierto = alquilerTemporal;
			}
		}
		return alquilerAbierto;
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede borrar un alquiler nulo.");
		}
		if (!coleccionAlquileres.contains(alquiler)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún alquiler igual.");
		}
		coleccionAlquileres.remove(alquiler);
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede buscar un alquiler nulo.");
		}
		int nuevoA = coleccionAlquileres.indexOf(alquiler);
		if (nuevoA != -1) {
			return coleccionAlquileres.get(nuevoA);
		}
		return null;
	}
}
