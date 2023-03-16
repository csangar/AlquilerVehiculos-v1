package org.iesalandalus.programacion.alquilervehiculos.vista.texto;

import javax.naming.OperationNotSupportedException;

public enum Accion {

	
	SALIR("Salir") {
		@Override
		public void ejecutar() {
			vista.terminar();
		}
	}, INSERTAR_CLIENTE("Inserta un cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarCliente();
		}
	}, INSERTAR_VEHICULO("Inserta un vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarVehiculo();
		}
	},
	INSERTAR_ALQUILER("Inserta un alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.insertarAlquiler();
		}
	}, BUSCAR_CLIENTE("Busca un cliente") {
		@Override
		public void ejecutar() {
			vista.buscarCliente();
		}
	}, BUSCAR_VEHICULO("Busca un vehiculo") {
		@Override
		public void ejecutar() {
			vista.buscarVehiculo();
		}
	},
	BUSCAR_ALQUILER("Busca un alquiler") {
		@Override
		public void ejecutar() {
			vista.buscarAlquiler();
		}
	}, MODIFICAR_CLIENTE("Modifica un cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.modificarCliente();
		}
	},
	DEVOLVER_ALQUILER_CLIENTE("Devolve un alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.devoverAlquilerCliente();
		}
	}, DEVOLVER_ALQUILER_VEHICULO("Devolve un alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.devoverAlquilerVehiculo();
		}
	},
	BORRAR_CLIENTE("Borra un cliente") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarCliente();
		}
	}, BORRAR_VEHICULO("Borra un vehiculo") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarVehiculo();
		}
	}, BORRAR_ALQUILER("Borra un alquiler") {
		@Override
		public void ejecutar() throws OperationNotSupportedException {
			vista.borrarAlquiler();
		}
	},
	LISTAR_CLIENTES("Lista los clientes") {
		@Override
		public void ejecutar() {
			vista.listarClientes();
		}
	}, LISTAR_VEHICULOS("Lista los vehiculos") {
		@Override
		public void ejecutar() {
			vista.listarVehiculos();
		}
	},
	LISTAR_ALQUILERES("Lista los alquileres") {
		@Override
		public void ejecutar() {
			vista.listarAlquiler();
		}
	}, LISTAR_ALQUILERES_CLIENTE("Lista los alquileres de un cliente") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresCliente();
		}
	},
	LISTAR_ALQUILERES_VEHICULO("Lista los alquileres de un vehiculo") {
		@Override
		public void ejecutar() {
			vista.listarAlquileresTurismo();
		}
	},
	MOSTRAR_ESTADISTICAS_MENSUALES("Muestra las estadisticas mensuales") {
		@Override
		public void ejecutar() {
			vista.mostrarEstadisticasMensualesTipoVehiculo();
		}
	};
	private static VistaTexto vista;
	private String texto;

	static void setVista(VistaTexto vista) {
		Accion.vista = vista;
	}

	public abstract void ejecutar() throws OperationNotSupportedException;

	private static boolean esOrdinalValido(int ordinal) {
		return (ordinal >= 0 && ordinal < Accion.values().length);
	}

	public static Accion get(int ordinal) {
		if (!esOrdinalValido(ordinal)) {
			throw new IllegalArgumentException("El ordinal no es válido");
		}
		Accion opcion = null;
		if (esOrdinalValido(ordinal)) {
			opcion = Accion.values()[ordinal];
		}
		return opcion;
	}

	private Accion(String cadena) {
		this.texto = cadena;
	}

	@Override
	public String toString() {
		return String.format("%d: %s ", ordinal(), texto);
	}
}
