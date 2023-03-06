package org.iesalandalus.programacion.alquilervehiculos.modelo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria.Vehiculos;

public class ModeloCascada extends Modelo {
	public ModeloCascada() {
		super();
		comenzar();
	}

	@Override
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		clientes.insertar(cliente);
	}

	@Override
	public void insertar(Vehiculo vehiculo) throws OperationNotSupportedException {
		vehiculos.insertar(vehiculo);
	}

	@Override
	public void insertar(Alquiler alquiler) throws OperationNotSupportedException {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No se puede realizar un alquiler nulo.");
		}
		if (buscar(alquiler.getCliente()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el cliente del alquiler.");
		}
		if (buscar(alquiler.getVehiculo()) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el turismo del alquiler.");
		}
		alquileres.insertar(alquiler);
	}

	@Override
	public Cliente buscar(Cliente cliente) {
		return clientes.buscar(cliente);
	}

	@Override
	public Vehiculo buscar(Vehiculo turismo) {
		return vehiculos.buscar(turismo);
	}

	@Override
	public Alquiler buscar(Alquiler alquiler) {
		return alquileres.buscar(alquiler);
	}

	@Override
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		clientes.modificar(cliente, nombre, telefono);
	}

	@Override
	public void devolver(Alquiler alquiler, LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (alquileres.buscar(alquiler) == null) {
			throw new OperationNotSupportedException("ERROR: No existe el alquiler a devolver.");
		}
		alquiler.devolver(fechaDevolucion);
	}

	@Override
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		for (Alquiler alquiler : alquileres.get(cliente)) {
			alquileres.borrar(alquiler);
		}
		clientes.borrar(cliente);
	}

	@Override
	public void borrar(Vehiculo turismo) throws OperationNotSupportedException {
		for(Alquiler alquiler : alquileres.get(turismo)) {
			alquileres.borrar(alquiler);
		}
		vehiculos.borrar(turismo);
	}

	@Override
	public void borrar(Alquiler alquiler) throws OperationNotSupportedException {
		alquileres.borrar(alquiler);
	}

	@Override
	public List<Cliente> getListaClientes() {
		List<Cliente> lista = new ArrayList<>();
		for (Cliente cliente : clientes.get()) {
			lista.add(new Cliente(cliente));
		}
		return lista;
	}

	@Override
	public List<Vehiculo> getListaVehiculos() {
		List<Vehiculo> lista = new ArrayList<>();
		for (Vehiculo turismo : vehiculos.get()) {
			lista.add(new Vehiculo(vehiculo));
		}
		return lista;
	}

	@Override
	public List<Alquiler> getListaAlquileres() {
		List<Alquiler> lista = new ArrayList<>();
		for (Alquiler alquiler : alquileres.get()) {
			lista.add(new Alquiler(alquiler));
		}
		return lista;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Cliente cliente) {
		List<Alquiler> lista = new ArrayList<>();
		for (Alquiler alquiler : alquileres.get(cliente)) {
			lista.add(new Alquiler(alquiler));
		}
		return lista;
	}

	@Override
	public List<Alquiler> getListaAlquileres(Vehiculo turismo) {
		List<Alquiler> lista = new ArrayList<>();
		for (Alquiler alquiler : alquileres.get(turismo)) {
			lista.add(new Alquiler(alquiler));
		}
		return lista;
	}
}
