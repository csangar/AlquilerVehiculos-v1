package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Objects;

import javax.naming.OperationNotSupportedException;

public class Alquiler {

	static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	private Cliente cliente;
	private Turismo turismo;
	private static final int PRECIO_DIA = 20;
	private LocalDate fechaAlquiler;
	private LocalDate fechaDevolucion;

	public Alquiler(Cliente cliente, Turismo turismo, LocalDate fechaAlquiler) {
		setCliente(cliente);
		setTurismo(turismo);
		setFechaAlquiler(fechaAlquiler);
	}

	public Alquiler(Alquiler alquiler) {
		if (alquiler == null) {
			throw new NullPointerException("ERROR: No es posible copiar un alquiler nulo.");
		}
		setCliente(
				new Cliente(alquiler.cliente.getNombre(), alquiler.cliente.getDni(), alquiler.cliente.getTelefono()));
		setTurismo(new Turismo(alquiler.turismo.getMarca(), alquiler.turismo.getModelo(),
				alquiler.turismo.getCilindrada(), alquiler.turismo.getMatricula()));
		setFechaAlquiler(alquiler.fechaAlquiler);
		this.fechaDevolucion = alquiler.getFechaDevolucion();
	}

	public Cliente getCliente() {
		return cliente;
	}

	private void setCliente(Cliente cliente) {
		if (cliente == null) {
			throw new NullPointerException("ERROR: El cliente no puede ser nulo.");
		}
		this.cliente = cliente;
	}

	public Vehiculo getTurismo() {
		return turismo;
	}

	private void setTurismo(Turismo turismo) {
		if (turismo == null) {
			throw new NullPointerException("ERROR: El turismo no puede ser nulo.");
		}
		this.turismo = turismo;
	}

	public LocalDate getFechaAlquiler() {
		return fechaAlquiler;
	}

	private void setFechaAlquiler(LocalDate fechaAlquiler) {
		if (fechaAlquiler == null) {
			throw new NullPointerException("ERROR: La fecha de alquiler no puede ser nula.");
		}
		if (fechaAlquiler.compareTo(LocalDate.now()) == 1) {
			throw new IllegalArgumentException("ERROR: La fecha de alquiler no puede ser futura.");
		}
		this.fechaAlquiler = fechaAlquiler;
	}

	public LocalDate getFechaDevolucion() {
		return fechaDevolucion;
	}

	private void setFechaDevolucion(LocalDate fechaDevolucion) {
		if (fechaDevolucion == null) {
			throw new NullPointerException("ERROR: La fecha de devolución no puede ser nula.");
		}
		if (fechaDevolucion.compareTo(LocalDate.now()) == 1) {
			throw new IllegalArgumentException("ERROR: La fecha de devolución no puede ser futura.");
		}
		if (fechaDevolucion.compareTo(getFechaAlquiler()) <= 0) {
			throw new IllegalArgumentException(
					"ERROR: La fecha de devolución debe ser posterior a la fecha de alquiler.");
		}

		this.fechaDevolucion = fechaDevolucion;
	}

	public void devolver(LocalDate fechaDevolucion) throws OperationNotSupportedException {
		if (getFechaDevolucion() != null) {
			throw new OperationNotSupportedException("ERROR: La devolución ya estaba registrada.");
		}
		setFechaDevolucion(fechaDevolucion);
	}

	@Override
	public String toString() {
		String cadFinal;
		if (getFechaDevolucion() != null) {
			cadFinal = cliente + " <---> " + turismo + ", " + getFechaAlquiler().format(FORMATO_FECHA) + " - "
					+ getFechaDevolucion().format(FORMATO_FECHA) + " (" + getPrecio() + "€)";
		} else {
			cadFinal = cliente + " <---> " + turismo + ", " + getFechaAlquiler().format(FORMATO_FECHA)
					+ " - Aún no devuelto (" + 0 + "€)";
		}
		return cadFinal;
	}

	public int getPrecio() {
		long nDias;
		if (getFechaDevolucion() == null) {
			nDias = 0;
		} else {
			nDias = ChronoUnit.DAYS.between(getFechaAlquiler(), getFechaDevolucion());
		}
		return (PRECIO_DIA + (turismo.getCilindrada() / 10)) * (int) nDias;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cliente, fechaAlquiler, fechaDevolucion, turismo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alquiler other = (Alquiler) obj;
		return Objects.equals(cliente, other.cliente) && Objects.equals(fechaAlquiler, other.fechaAlquiler)
				&& Objects.equals(fechaDevolucion, other.fechaDevolucion) && Objects.equals(turismo, other.turismo);
	}

}
