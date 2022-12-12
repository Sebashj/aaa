package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import Conexion.Conexion;
import Modelo.Autos;
import Modelo.Producto;

public class DaoProducto {
	Conexion cx =null;
	
	public DaoProducto() {
		cx=new Conexion();
	}
	
	public boolean insertarProducto(Producto user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO Producto VALUES(null,?,?,?,?,?)");
			ps.setInt(1, user.getIdproveedor());
			ps.setInt(2, user.getCodigo());
			ps.setDouble(3, user.getPrecio());
			ps.setDouble(4, user.getPrecioventa());
			ps.setString(5, user.getDescripcion());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Producto> fetchProductos(){
		ArrayList<Producto> lista=new ArrayList<Producto>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Producto");
			rs=ps.executeQuery();
			while(rs.next()) {
				Producto u=new Producto();
				u.setIdproducto(rs.getInt("Idproducto"));
				u.setIdproveedor(rs.getInt("Idproveedor"));
				u.setCodigo(rs.getInt("Codigo"));
				u.setPrecio(rs.getDouble("Precio"));
				u.setPrecioventa(rs.getDouble("Precioventa"));
				u.setDescripcion(rs.getString("Descripcion"));
				lista.add(u);
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		}
		return lista;
		
	}
	
	public ArrayList<Producto> fecthBuscar(String palabra) {
		ArrayList<Producto> lista2 = new ArrayList<Producto>();
		try {
			String sql = "SELECT * FROM Producto WHERE " + "(Idproveedor LIKE ?) OR " + "(Codigo LIKE ?) OR " +"(Precio LIKE ?) OR " +"(Precioventa LIKE ?) OR " + "(Descripcion LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");
			ps.setString(5, "%" + palabra + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Producto p = new Producto();
				p.setIdproducto(rs.getInt("Idproducto"));
				p.setIdproveedor(rs.getInt("Idproveedor"));
				p.setCodigo(rs.getInt("Codigo"));
				p.setPrecio(rs.getDouble("Precio"));
				p.setPrecioventa(rs.getDouble("Precioventa"));
				p.setDescripcion(rs.getString("Descripcion"));
				lista2.add(p);
			}
			ps.close();
			ps = null;
			//cx.desconectar();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
        	JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, ex.toString());
			ex.printStackTrace();
			System.out.println("Error en BUSCAR");
		}
		return lista2;

	}

	public boolean eliminarProducto(int idproducto) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM Producto WHERE idproducto=?");
			ps.setInt(1, idproducto);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarProducto(Producto user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE Producto SET idproveedor=?, codigo=?, precio=?, precioventa=?, descripcion=? WHERE idproducto=?");
			ps.setInt(1, user.getIdproveedor());
			ps.setInt(2, user.getCodigo());
			ps.setDouble(3, user.getPrecio());
			ps.setDouble(4, user.getPrecioventa());
			ps.setString(5, user.getDescripcion());
			ps.setInt(6, user.getIdproducto());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
			return false;
		}
		
	}
}
