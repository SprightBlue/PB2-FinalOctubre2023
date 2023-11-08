package ar.edu.unlam.pb1.vivero;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class Vivero {
	
	/**
	 * Se deberan realizar los siguientes tests
	 * 
	 * - 1 test para el metodo agregarPlanta() que arroje la excepcion de validacion
	 * - 1 test para el metodo venderPlanta() que arroje una excepcion a eleccion
	 * - 1 test para el metodo obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta()
	 * - 1 test para el metodo obtenerReporteDePlantasAgrupadasPorTipo()
	 * - 1 test para el metodo obtenerTodasLasPlantasFlorales()
	 * - 1 test para el metodo obtenerPrecio() de la clase Planta 
	 * - 1 test para el metodo obtenerPrecio() de alguna clase que implemente la interfaz Florales en estado de floracion
	 * - 1 test para el metodo obtenerPrecio() de alguna clase que implemente la interfaz Florales en estado de produccion
	 * */

	private String nombre;

	// No se pueden registrar plantas duplicadas. 2 plantas son iguales cuando tiene
	// el mismo Id
	private Set<Planta> plantas;
	private List<Venta> ventas;
	private static Integer idGeneral;

	public Vivero(String nombre) {
		this.setNombre(nombre);
		this.plantas = new TreeSet<Planta>(new Comparator<Planta>() {

			@Override
			public int compare(Planta planta1, Planta planta2) {
				return planta1.getCodigo().compareTo(planta2.getCodigo());
			}
			
		});
		idGeneral = 0;
		this.ventas = new ArrayList<Venta>();
	}
	
	public String getNombre() {
		return nombre;
	}

	private void setNombre(String nombre) {
		this.nombre = nombre;
	}

	// No puede haber 2 plantas con el mismo Id , Si se duplica lanza una Exception
	// Planta Duplicada Exception
	public Boolean agregarPlanta(Planta planta) throws PlantaDuplicadaException {
		if(this.plantas.contains(planta)) {
			throw new PlantaDuplicadaException();
		}
		return this.plantas.add(planta);
	}

	/*
	 * Registra una venta y descuenta del stock de la planta la cantidad deseada. Si no se encuentra la planta lanza
	 * una exception Planta Inexistente. Si no hay Stock Lanza Una Exception
	 * ProdutoSinStockException
	 */
	public void venderPlanta(Integer codigoPlanta, Integer cantidadAVender) throws PlantaInexistenteException, ProductoSinStockException {
		Planta plantaBuscada = this.buscarPlanta(codigoPlanta);
		if(plantaBuscada == null) {
			throw new PlantaInexistenteException();
		}
		if(plantaBuscada.getStock() == (Integer)0) {
			throw new ProductoSinStockException();
		}
		plantaBuscada.quitarStock(cantidadAVender);
		this.ventas.add(new Venta(idGeneral++, cantidadAVender, plantaBuscada, plantaBuscada.obtenerPrecio()));
		
	}
	
	private Planta buscarPlanta(Integer codigoPlanta) {
		Planta plantaBuscada = null;
		Iterator<Planta> iterador = this.plantas.iterator();
		boolean encontrado = false;
		while(!encontrado && iterador.hasNext()) {
			Planta plantaIterada = iterador.next();
			if(plantaIterada.getCodigo().equals(codigoPlanta)) {
				plantaBuscada = plantaIterada;
				encontrado = true;
			}
		}
		return plantaBuscada;
	}

	/*
	 * Obtener un listado de todos los arboles vendidos ordenados por el total de
	 * venta (Cantidad * precioDeLaPlanta)
	 * 
	 */
	public Set<Venta> obtenerTodasLasVentasDeArbolesOrdenadosPorElValorTotalDeLaVenta() {
		TreeSet<Venta> ventasDeArboles = new TreeSet<Venta>(new Comparator<Venta>() {

			@Override
			public int compare(Venta venta1, Venta venta2) {
				if(venta1.getPrecioUnitario().compareTo(venta2.getPrecioUnitario())==0) {
					return -1;
				}else {
					return venta1.getPrecioUnitario().compareTo(venta2.getPrecioUnitario());
				}
			}
			
		});
		Iterator<Venta> iterador = this.ventas.iterator();
		while(iterador.hasNext()) {
			Venta ventaIterada = iterador.next();
			if(ventaIterada.getPlanta() instanceof Arbol) {
				ventasDeArboles.add(ventaIterada);
			}
		}
		return ventasDeArboles;
	}

	/*
	 * Obtener Un reporte de las plantas vendidas agrupados por tipo Plantas
	 * 
	 * 
	 * Ejemplo: para una key "arbol", debemos completar las plantas de este tipo
	 * 
	 */

	public Map<String, ArrayList<Venta>> obtenerReporteDePlantasAgrupadasPorTipo() {
		TreeMap<String, ArrayList<Venta>> reporteDeVentas = new TreeMap<String, ArrayList<Venta>>(new Comparator<String>() {

			@Override
			public int compare(String clave1, String clave2) {
				return clave1.compareTo(clave2);
			}
			
		});
		ArrayList<Venta> arboles = new ArrayList<Venta>();
		ArrayList<Venta> arbustos = new ArrayList<Venta>();
		ArrayList<Venta> hierbas = new ArrayList<Venta>();
		Iterator<Venta> iterador = this.ventas.iterator();
		while(iterador.hasNext()) {
			Venta ventaIterada = iterador.next();
			if(ventaIterada.getPlanta() instanceof Hierba) {
				hierbas.add(ventaIterada);
			}else if(ventaIterada.getPlanta() instanceof Arbusto) {
				arbustos.add(ventaIterada);
			}else {
				arboles.add(ventaIterada);
			}
		}
		reporteDeVentas.put("hierba", hierbas);
		reporteDeVentas.put("arbusto", arbustos);
		reporteDeVentas.put("arbol", arboles);
		return reporteDeVentas;
	}

	/**
	 * Obtener una lista de plantas que implementen la interfaz correspondiente
	 * */
	public List<Florales> obtenerTodasLasPlantasFlorales() {
		ArrayList<Florales> plantasFlorales = new ArrayList<Florales>();
		Iterator<Planta> iterador = this.plantas.iterator();
		while(iterador.hasNext()) {
			Planta plantaIterada = iterador.next();
			if(plantaIterada instanceof Florales) {
				plantasFlorales.add((Florales) plantaIterada);
			}
		}
		return plantasFlorales;
	}

}
