package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;

import java.util.Objects;

public abstract class Vehiculo {

	private static final String ER_MARCA = "([A-Z][a-z]+([ -]?[A-Z][a-z]+)?)|[A-Z]+";
	private static final String ER_MATRICULA = "[0-9]{4}[^AEIOU|^a-z]{3}";
	private String marca;
	private String modelo;
	private String matricula;

	public Vehiculo(String marca, String modelo, String matricula) {
		setMarca(marca);
		setModelo(modelo);
		setMatricula(matricula);
	}
	
	public Vehiculo(Vehiculo vehiculo) {
		if (vehiculo == null) {
			throw new NullPointerException("ERROR: No es posible copiar un vehículo nulo.");
		}
		setMarca(vehiculo.marca);
		setMatricula(vehiculo.matricula);
		setModelo(vehiculo.modelo);
	}

	public static Vehiculo copiar(Vehiculo vehiculo) {
		Vehiculo vehiculoCopiado = null;
		if(vehiculo instanceof Turismo turismo) {
			vehiculoCopiado = new Turismo(turismo);
		}/*Hacer lo mismo con autobus y furgoneta cuanso esten las clases creadas*/
		return vehiculoCopiado;
	}
	public static Vehiculo getVehiculoConMatricula(String matricula) {
		return new Turismo("Seat", "León", 90, matricula);
	}
	
	public abstract int getFactorPrecio();
	
	public String getMarca() {
		return marca;
	}

	protected void setMarca(String marca) {
		if (marca == null) {
			throw new NullPointerException("ERROR: La marca no puede ser nula.");
		}
		if (!marca.matches(ER_MARCA)) {
			throw new IllegalArgumentException("ERROR: La marca no tiene un formato válido.");
		}
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	protected void setModelo(String modelo) {
		if (modelo == null) {
			throw new NullPointerException("ERROR: El modelo no puede ser nulo.");
		}
		if (modelo.isBlank()) {
			throw new IllegalArgumentException("ERROR: El modelo no puede estar en blanco.");
		}
		this.modelo = modelo;
	}

	public String getMatricula() {
		return matricula;
	}

	protected void setMatricula(String matricula) {
		if (matricula == null) {
			throw new NullPointerException("ERROR: La matrícula no puede ser nula.");
		}
		if (!matricula.matches(ER_MATRICULA)) {
			throw new IllegalArgumentException("ERROR: La matrícula no tiene un formato válido.");
		}
		this.matricula = matricula;
	}

	@Override
	public int hashCode() {
		return Objects.hash(matricula);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Vehiculo))
			return false;
		Vehiculo other = (Vehiculo) obj;
		return Objects.equals(matricula, other.matricula);
	}

}