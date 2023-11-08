package ar.edu.unlam.pb1.vivero;

public class Venta {

	private Integer id;
	private Integer unidades;
	private Planta planta;
	private Double precioUnitario; // Precio final de la planta al momento de realizar la venta

	public Venta(Integer id, Integer unidades, Planta planta, Double precioUnitario) {
		this.setId(id);
		this.setUnidades(unidades);
		this.setPlanta(planta);
		this.setPrecioUnitario(precioUnitario);
	}

	public Integer getId() {
		return id;
	}

	private void setId(Integer id) {
		this.id = id;
	}

	public Integer getUnidades() {
		return unidades;
	}

	private void setUnidades(Integer unidades) {
		this.unidades = unidades;
	}

	public Planta getPlanta() {
		return planta;
	}

	private void setPlanta(Planta planta) {
		this.planta = planta;
	}

	public Double getPrecioUnitario() {
		return precioUnitario;
	}

	private void setPrecioUnitario(Double precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	
}
