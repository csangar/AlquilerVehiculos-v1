package org.iesalandalus.programacion.alquilervehiculos.vista;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.annotation.processing.SupportedSourceVersion;

import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Alquiler;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Cliente;
import org.iesalandalus.programacion.alquilervehiculos.modelo.dominio.Turismo;
import org.iesalandalus.programacion.utilidades.Entrada;

public class Consola {
	private static final String PATRON_FECHA="dd/MM/yyyy";
	private static final DateTimeFormatter FORMATO_FECHA= DateTimeFormatter.ofPattern(PATRON_FECHA);
	
	private Consola() {
		
	}
	public static void mostrarCabecera(String mensaje) {
		String subrayado = "";
		System.out.println(mensaje);
		for(int i = 0; i < mensaje.length();i++) {
			subrayado = subrayado +"-";
		}
		System.out.println(subrayado);
	}
	public static void mostrarMenu() {
		mostrarCabecera("Menu de opciones disponibles:");
		for(Opcion opcion : Opcion.values()) {
			System.out.println(opcion);
		}
	}
	private static String leerCadena(String mensaje) {
		System.out.print(mensaje);
		String cadena = Entrada.cadena();
		return cadena;
	}
	private static Integer leerEntero(String mensaje) {
		System.out.print(mensaje);
		int entero = Entrada.entero();
		return entero;
	}
	private static LocalDate leerFecha(String mensaje) {
		LocalDate fecha = LocalDate.parse(leerCadena(mensaje), FORMATO_FECHA);
		return fecha;
	}
	public static Opcion elegirOpcion() {
		int opcion;
		Opcion o = null;
		do {
			opcion = leerEntero("Elige la opcion que desear realizar: ");
			o = Opcion.get(opcion);
		}while(o == null);
		return o;
	}
	public static Cliente leerCliente() {
		String nombre = leerNombre();
		String dni = leerCadena("Introduce el dni del cliente: ");
		String telefono =leerTelefono();
		return new Cliente(nombre, dni, telefono);
	}
	public static Cliente leerClienteDni() {
		String dni = leerCadena("Introduce un dni: ");
		return Cliente.getClienteConDni(dni);
	}
	public static String leerNombre() {
		String nombre = leerCadena("Introduce el nombre del cliente: ");
		return nombre;
	}
	public static String leerTelefono() {
		String telefono = leerCadena("Introduce el telefono del cliente: ");
		return telefono;
	}
	public static Turismo leerTurismo() {
		String marca = leerCadena("Introduce la marca del turismo: ");
		String modelo = leerCadena("Introduce el modelo del turismo: ");
		int cilindrada = leerEntero("Introduce la cilidrada del turismo: ");
		String matricula = leerCadena("Introduce la matricula del turismo: ");
		return new Turismo(marca, modelo, cilindrada, matricula);
	}
	public static Turismo leerTurismoMatricula() {
		String matricula = leerCadena("Introduce una matricula: ");
		return Turismo.getTurismoConMatricula(matricula);
	}
	public static Alquiler leerAlquiler() {
		Cliente cliente = leerClienteDni();
		Turismo turismo = leerTurismoMatricula();
		LocalDate fechaAlquiler = leerFecha("Introduce la fecha de alquiler: ");
		return new Alquiler(cliente, turismo, fechaAlquiler);
	}
	public static LocalDate leerFechaDevolucion() {
		LocalDate FechaDevolucion = leerFecha("Introoduce la fecha de devolucion: ");
		return FechaDevolucion;
	}
}
