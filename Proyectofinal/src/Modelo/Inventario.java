package Modelo;

public class Inventario {
	
	int idinventario, idproducto, fecha, cantidad;
	String tipodemovimiento;
	
	public Inventario() {
		
	}
	
	public Inventario(int idinventario, int idproducto, int fecha, int cantidad, String tipodemovimiento) {
		super();
		this.idinventario = idinventario;
		this.idproducto = idproducto;
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.tipodemovimiento = tipodemovimiento;
	}
	
	public int getIdinventario() {
		return idinventario;
	}
	public void setIdinventario(int idinventario) {
		this.idinventario = idinventario;
	}
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public int getFecha() {
		return fecha;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public String getTipodemovimiento() {
		return tipodemovimiento;
	}
	public void setTipodemovimiento(String tipodemovimiento) {
		this.tipodemovimiento = tipodemovimiento;
	}
	

}
