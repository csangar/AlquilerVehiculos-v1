package org.iesalandalus.programacion.alquilervehiculos.modelo.dominio;



public class Turismo extends Vehiculo {
	
	private static final int FACTOR_CILINDRADA = 10; 
	private int cilindrada;
	
	public Turismo(String marca, String modelo, int cilindrada, String matricula) {
		super(marca,modelo,matricula);
		setCilindrada(cilindrada);
	}

	public Turismo(Turismo turismo) {
		super(turismo);
		this.cilindrada = turismo.cilindrada;
	}

	public int getCilindrada() {
		return cilindrada;
	}

	private void setCilindrada(int cilindrada) {
		if (cilindrada < 5000 && cilindrada > 0) {
			this.cilindrada = cilindrada;
		} else {
			throw new IllegalArgumentException("ERROR: La cilindrada no es correcta.");
		}
	}
	
	public int getFactorPrecio() {
		return cilindrada/FACTOR_CILINDRADA;
	}
	@Override
	public String toString() {
		return String.format("%s %s (%s cc) - %s", getMarca(),getModelo(),cilindrada,getMatricula());
	}
}
