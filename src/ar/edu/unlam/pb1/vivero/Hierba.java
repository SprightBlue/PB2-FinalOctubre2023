package ar.edu.unlam.pb1.vivero;

public class Hierba extends Planta {

	private final double gananciaHierbaMata = 1.2;

	public Hierba(Integer codigo, String nombre, double precio, int stock) {
		super(codigo, nombre, precio, stock);
	}

	@Override
	public Double obtenerPrecio() {
		return this.getPrecioBase()*this.gananciaHierbaMata;
	}
}
