package org.iesalandalus.programacion.alquilervehiculos.vista;

import javax.naming.OperationNotSupportedException;

import org.iesalandalus.programacion.alquilervehiculos.controlador.Controlador;

public abstract class Vista {

	protected Controlador controlador;

	public void setControlador(Controlador controlador) {
		if (controlador != null) {
			this.controlador = controlador;
		}
	}
	
	protected Controlador getControlador() {
		return controlador;
	}
	public abstract void terminar();

	public abstract void comenzar() throws OperationNotSupportedException;

}