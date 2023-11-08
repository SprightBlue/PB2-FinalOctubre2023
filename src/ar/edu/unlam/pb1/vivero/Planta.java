package ar.edu.unlam.pb1.vivero;

public abstract class Planta {

	private Integer codigo;
	private String nombre;
	private int stock;
	private Double precioBase;

	public Planta(Integer codigo, String nombre, double precio, int stock) {

		this.codigo = codigo;
		this.nombre = nombre;
		this.stock = stock;
		this.precioBase = precio;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public Double getPrecioBase() {
		return precioBase;
	}
	
	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	/*
	 * Cada Planta tiene un valor denominadado ganancia y este se debe
	 * multiplicar por el precio base para el calculo del precio final. Considerar ademas la/s
	 * interfaces aplicadas a cada tipo de planta, las cuales modifican el precio final de la planta
	 */

	public abstract Double obtenerPrecio();
	
	public void quitarStock(Integer stock) {
		this.stock -= stock;
	}

}
