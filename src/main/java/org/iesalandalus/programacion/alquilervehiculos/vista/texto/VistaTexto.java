package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import java.time.LocalDate;
import java.util.Map;
import java.util.TreeMap;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Vehiculo;
import org.iesalandalus.programacion.alquilervehiculos.vista.Vista;

public class VistaTexto extends Vista {

	public VistaTexto() {
		super();
	}

	public void comenzar() throws OperationNotSupportedException {
		Accion.setVista(this);
		Accion accion;
		do{
			Consola.mostrarMenuAcciones();
			accion = Consola.elegirAccion();
			accion.ejecutar();
		}while(accion != Accion.SALIR);
	}

	public void terminar() {
		try {
			System.out.println("FIN");
		} catch (Exception e) {
			System.out.print("Error al salir del programa");
		}
	}

	public void insertarCliente() throws OperationNotSupportedException {
		try {
			controlador.insertar(Consola.leerCliente());
			System.out.println("El cliente se ha insertado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarVehiculo() throws OperationNotSupportedException {
		try {
			controlador.insertar(Consola.leerVehiculo());
			System.out.println("El turismo se ha insertado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void insertarAlquiler() throws OperationNotSupportedException {
		try {
			Alquiler alquiler = Consola.leerAlquiler();
			Cliente cliente = controlador.buscar(alquiler.getCliente());
			Vehiculo turismo = controlador.buscar(alquiler.getVehiculo());
			if (cliente == null) {
				throw new OperationNotSupportedException("El cliente no existe");
			}
			if (turismo == null) {
				throw new OperationNotSupportedException("El turismo no puede ser nulo");
			}
			controlador.insertar(new Alquiler(cliente, turismo, alquiler.getFechaAlquiler()));
			System.out.println("El alquiler se ha insertado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarCliente() {
		try {
			System.out.println(controlador.buscar(Consola.leerClienteDni()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarVehiculo() {
		try {
			System.out.println(controlador.buscar(Consola.leerVehiculoMatricula()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void buscarAlquiler() {
		try {
			System.out.println(controlador.buscar(Consola.leerAlquiler()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void modificarCliente() throws OperationNotSupportedException {
		try {
			controlador.modificar(Consola.leerClienteDni(), Consola.leerNombre(), Consola.leerTelefono());
			System.out.println("El cliente se ha modificado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void devoverAlquilerCliente() throws OperationNotSupportedException {
		try {
			controlador.devolver(Consola.leerClienteDni(), Consola.leerFechaDevolucion());
			System.out.println("El alquiler se ha devuelto de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void devoverAlquilerVehiculo() throws OperationNotSupportedException {
		try {
			controlador.devolver(Consola.leerVehiculoMatricula(), Consola.leerFechaDevolucion());
			System.out.println("El alquiler se ha devuelto de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarCliente() throws OperationNotSupportedException {
		try {
			Cliente cliente = Consola.leerClienteDni();
			controlador.borrar(cliente);
			System.out.println("El cliente se ha borrado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarVehiculo() throws OperationNotSupportedException {
		try {
			controlador.borrar(Consola.leerVehiculoMatricula());
			System.out.println("El turismo se ha borrado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void borrarAlquiler() throws OperationNotSupportedException {
		try {
			controlador.borrar(Consola.leerAlquiler());
			System.out.println("El alquiler se ha borrado de forma correcta");
		} catch (OperationNotSupportedException | NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarClientes() {
		try {
			System.out.println(controlador.getClientes());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarVehiculos() {
		try {
			System.out.println(controlador.getVehiculos());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlquiler() {
		try {
			System.out.println(controlador.getAlquileres());
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlquileresCliente() {
		try {
			System.out.println(controlador.getAlquileres(Consola.leerClienteDni()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void listarAlquileresVehiculo() {
		try {
			System.out.println(controlador.getAlquileres(Consola.leerVehiculoMatricula()));
		} catch (NullPointerException | IllegalArgumentException e) {
			System.out.println(e.getMessage());
		}
	}

	public void mostrarEstadisticasMensualesTipoVehiculo() {
		LocalDate fecha = Consola.leerMes();
		Map<TipoVehiculo, Integer> mapa = inicializarEstadisticas();
		for (Alquiler alquiler : controlador.getAlquileres()) {
			if (alquiler.getFechaAlquiler().getMonth().equals(fecha.getMonth())
					&& alquiler.getFechaAlquiler().getYear() == (fecha.getYear())) {
				if (TipoVehiculo.get(alquiler.getVehiculo()).equals(TipoVehiculo.TURISMO)) {
					mapa.put(TipoVehiculo.TURISMO, mapa.get(TipoVehiculo.TURISMO) + 1);
				}
				if (TipoVehiculo.get(alquiler.getVehiculo()).equals(TipoVehiculo.AUTOBUS)) {
					mapa.put(TipoVehiculo.AUTOBUS, mapa.get(TipoVehiculo.AUTOBUS) + 1);
				}
				if (TipoVehiculo.get(alquiler.getVehiculo()).equals(TipoVehiculo.FURGONETA)) {
					mapa.put(TipoVehiculo.FURGONETA, mapa.get(TipoVehiculo.FURGONETA) + 1);
				}
			}
		}
		System.out.println(mapa);
	}

	private Map<TipoVehiculo, Integer> inicializarEstadisticas() {
		TreeMap<TipoVehiculo, Integer> map = new TreeMap<>();
		for (TipoVehiculo vehiculo : TipoVehiculo.values()) {
			map.put(vehiculo, 0);
		}
		return map;
	}
}
