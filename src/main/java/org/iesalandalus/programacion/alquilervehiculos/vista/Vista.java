package org.iesalandalus.programacion.alquilervehiculos.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;

public class Vista {

	private Controlador controlador;

	public void setControlador(Controlador controlador) {
		if (controlador != null) {
			this.controlador = controlador;
		}
	}

	public void comenzar() throws OperationNotSupportedException {
		Opcion opcion;
		do {
			Consola.mostrarMenu();
			opcion = Consola.elegirOpcion();
			ejecutar(opcion);
		} while (opcion != Opcion.SALIR);
	}

	public void terminar() {
		System.out.println("FIN");
	}

	private void ejecutar(Opcion opcion) throws OperationNotSupportedException {
			//while (opcion != Opcion.SALIR) {
				switch (opcion) {
				case SALIR:
					terminar();
					break;
				case INSERTAR_CLIENTE:
					insertarCliente();
					break;
				case INSERTAR_TURISMO:
					insertarTurismo();
					break;
				case INSERTAR_ALQUILER:
					insertarAlquiler();
					break;
				case BUSCAR_CLIENTE:
					buscarCliente();
					break;
				case BUSCAR_TURISMO:
					buscarTurismo();
					break;
				case BUSCAR_ALQUILER:
					buscarAlquiler();
					break;
				case MODIFICAR_CLIENTE:
					modificarCliente();
					break;
				case DEVOLVER_ALQUILER:
					devoverAlquiler();
					break;
				case BORRAR_CLIENTE:
					borrarCliente();
					break;
				case BORRAR_TURISMO:
					borrarTurismo();
					break;
				case BORRAR_ALQUILER:
					borrarAlquiler();
					break;
				case LISTAR_CLIENTES:
					listarClientes();
					break;
				case LISTAR_TURISMOS:
					listarTurismo();
					break;
				case LISTAR_ALQUILERES:
					listarAlquiler();
					break;
				case LISTAR_ALQUILERES_CLIENTE:
					listarAlquileresCliente();
					break;
				case LISTAR_ALQUILERES_TURISMO:
					listarAlquileresTurismo();
					break;
				}
		//	}
	}

	private void insertarCliente() throws OperationNotSupportedException {
		try {
			controlador.insertar(Consola.leerCliente());
			System.out.println("El cliente se ha insertado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertarTurismo() throws OperationNotSupportedException {
		try {
			controlador.insertar(Consola.leerTurismo());
			System.out.println("El turismo se ha insertado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void insertarAlquiler() throws OperationNotSupportedException {
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			Cliente cliente = controlador.buscar(alquiler.getCliente());
			Turismo turismo = controlador.buscar(alquiler.getTurismo());
			if(cliente == null) {
				throw new OperationNotSupportedException("El cliente no existe");
			}
			if(turismo == null) {
				throw new OperationNotSupportedException("El turismo no puede ser nulo");
			}
			
			controlador.insertar(new Alquiler(cliente, turismo, alquiler.getFechaAlquiler()));
			System.out.println("El alquiler se ha insertado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarCliente() {
		try {
			System.out.println(controlador.buscar(Consola.leerClienteDni()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarTurismo() {
		try {
			System.out.println(controlador.buscar(Consola.leerTurismoMatricula()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void buscarAlquiler() {
		try {
			System.out.println(controlador.buscar(Consola.leerAlquiler()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void modificarCliente() throws OperationNotSupportedException {
		try {
			controlador.modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.println("El cliente se ha modificado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void devoverAlquiler() throws OperationNotSupportedException {
		try {
			controlador.devolver(Consola.leerAlquiler(), Consola.leerFechaDevolucion());
			System.out.println("El alquiler se ha devuelto de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarCliente() throws OperationNotSupportedException {
		try {
			Cliente cliente = Consola.leerClienteDni();
			controlador.borrar(cliente);
			System.out.println("El cliente se ha borrado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarTurismo() throws OperationNotSupportedException {
		try {
			controlador.borrar(Consola.leerTurismoMatricula());
			System.out.println("El turismo se ha borrado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void borrarAlquiler() throws OperationNotSupportedException {
		try {
			controlador.borrar(Consola.leerAlquiler());
			System.out.println("El alquiler se ha borrado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarClientes() {
		try {
			System.out.println(controlador.getClientes());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarTurismo() {
		try {
			System.out.println(controlador.getTurismos());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarAlquiler() {
		try {
			System.out.println(controlador.getAlquileres());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarAlquileresCliente() {
		try {
			System.out.println(controlador.getAlquileres(Consola.leerClienteDni()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	private void listarAlquileresTurismo() {
		try {
			System.out.println(controlador.getAlquileres(Consola.leerTurismoMatricula()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}
}
