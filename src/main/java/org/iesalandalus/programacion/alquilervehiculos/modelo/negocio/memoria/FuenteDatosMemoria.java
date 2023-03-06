package org.iesalandalus.programacion.alquilervehiculos.modelo.negocio.memoria;

public class FuenteDatosMemoria implements IFuenteDatos {

		@Override
		public IClientes crearClientes() {
			return new Clientes();
		}
		
		@Override
		public IVehiculos crearVehiculos() {
			return new Vehiculos();
		}
		
		@Override
		public IAlquileres crearAlquileres() {
			return new Alquileres();
		}
}
