package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio;

import java.util.ArrayList;
import java.util.List;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;

public class Clientes {
	private List<Cliente> coleccionClientes;

	public Clientes() {
		coleccionClientes = new ArrayList<Cliente>();
	}

	public List<Cliente> get() {
		return new ArrayList<Cliente>(coleccionClientes);
	}
	public int getCantidad() {
		return coleccionClientes.size();	
	}
	public void insertar(Cliente cliente) throws OperationNotSupportedException {
		if(cliente == null) {
			throw new NullPointerException("ERROR: No se puede insertar un cliente nulo.");
		}
		if(coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: Ya existe un cliente con ese DNI.");
		}
		if (cliente != null)
			coleccionClientes.add(cliente);
	}
	public Cliente buscar(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede buscar un cliente nulo.");
		}
		if (coleccionClientes.contains(cliente)) {
			int nuevoC = coleccionClientes.indexOf(cliente);
			return coleccionClientes.get(nuevoC);
		}
		return null;
	}
	public void borrar(Cliente cliente) throws OperationNotSupportedException {
		if(cliente == null) {
			throw new NullPointerException("ERROR: No se puede borrar un cliente nulo.");
		}
		if(!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		coleccionClientes.remove(cliente);
	}
	public void modificar(Cliente cliente, String nombre, String telefono) throws OperationNotSupportedException {
		if (cliente == null) {
			throw new NullPointerException("ERROR: No se puede modificar un cliente nulo.");
		}
		if(!coleccionClientes.contains(cliente)) {
			throw new OperationNotSupportedException("ERROR: No existe ningún cliente con ese DNI.");
		}
		if(telefono!= null) {
			if(!telefono.isBlank()) {
				buscar(cliente).setTelefono(telefono);
			}
		}
		if(nombre!= null) {
			if(!nombre.isBlank()) {
				buscar(cliente).setNombre(nombre);
			}
		}
	}
}
