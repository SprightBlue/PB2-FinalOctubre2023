package ar.edu.unlam.pb1.vivero;

public class Arbusto extends Planta {
	
	private final double GANANCIA_ARBUSTO = 1.6;
	 
	public Arbusto(Integer codigo, String nombre, double precio, int stock) {
		super(codigo, nombre, precio, stock);
	}

	@Override
	public Double obtenerPrecio() {
		return this.getPrecioBase()*this.GANANCIA_ARBUSTO;
	}

}
