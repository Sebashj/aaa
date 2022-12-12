package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultTreeCellEditor.DefaultTextField;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Dao.DaoDetalleV;
import Dao.DaoProducto;
import Dao.DaoProveedor;
import Dao.DaoVenta;
import Modelo.Autos;
import Modelo.DetalleV;
import Modelo.Producto;
import Modelo.Proveedor;
import Modelo.Venta;

import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vDetalleV extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtcosto;
	private JTextField txtimporte;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoDetalleV dao=new DaoDetalleV();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblDetalleV;
	ArrayList<DetalleV> lista = new ArrayList<DetalleV>();
	int fila=-1;
	DetalleV DetalleV;
	private JTextField txtcantidad;
	ArrayList<Producto> listaProducto=new ArrayList<Producto>();
	ArrayList<Proveedor> listaProveedor=new ArrayList<Proveedor>();
	ArrayList<Venta> listaVenta=new ArrayList<Venta>();
	Funciones fx = new Funciones();
	private JComboBox cboventa;
	private JComboBox cboproducto;
	private JButton btnPdf;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vDetalleV frame = new vDetalleV();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtcosto.setText("");
		txtimporte.setText("");
		txtcantidad.setText("");
	}
	public void cargarComboProducto() {
		DaoProveedor daoPro=new DaoProveedor();		
		listaProveedor=daoPro.fetchProveedor();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Proveedor Producto : listaProveedor) {
		 	model.addElement(Producto.getIdproveedor());
		}
		cboproducto.setModel(model);
	}

	public int Producto(int id) {
		for (Producto Producto : listaProducto) {
			if(DetalleV.getIdproducto()==id) {
				return DetalleV.getIdproducto();
			}
		}
		return (Integer) null;
	}
	
	public void cargarComboVenta() {
		DaoVenta daoPro=new DaoVenta();		
		listaVenta=daoPro.fetchVentas();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Venta DetalleV : listaVenta) {
		 	model.addElement(DetalleV.getNopedido());
		}
		cboventa.setModel(model);
	}
	
	public int Venta(int id) {
		for (Venta DetalleV : listaVenta) {
			if(DetalleV.getIdventa()==id) {
				return DetalleV.getIdventa();
			}
		}
		return (Integer) null;
	}
	
	public void pdf() {
		try {

			try {
				FileOutputStream archivo;				
	            File temp = new File(System.getProperty("java.io.tmpdir") + "detalleV.pdf");
	            InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/PDF/detalleV.pdf");
	            FileOutputStream flujoSalida = new FileOutputStream(temp);         
				archivo = new FileOutputStream(temp);
				Document doc = new Document();
				PdfWriter.getInstance(doc, archivo);
				doc.open();
				java.awt.Image img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logodesot.png"));
				Image img = Image.getInstance(getClass().getResource("/Img/logodesot.png"));
				img.setAlignment(Element.ALIGN_CENTER);
				img.scaleToFit(200, 200);
				doc.add(img);
			Paragraph p = new Paragraph(10);
			com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
			p.add(Chunk.NEWLINE);
			p.add("Detalle Venta");
			p.add(Chunk.NEWLINE);
			p.add(Chunk.NEWLINE);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			PdfPTable tabla = new PdfPTable(6);
			tabla.setWidthPercentage(100);
			PdfPCell c1 = new PdfPCell(new Phrase(" Iddetalle", negrita));
			PdfPCell c2 = new PdfPCell(new Phrase(" Idproducto", negrita));
			PdfPCell c3 = new PdfPCell(new Phrase(" Idventa", negrita));
			PdfPCell c4 = new PdfPCell(new Phrase(" Costo", negrita));
			PdfPCell c5 = new PdfPCell(new Phrase(" Cantidad", negrita));
			PdfPCell c6 = new PdfPCell(new Phrase(" Importe", negrita));		
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tabla.addCell(c1);
			tabla.addCell(c2);
			tabla.addCell(c3);
			tabla.addCell(c4);
			tabla.addCell(c5);
			tabla.addCell(c6);

			for (DetalleV u : lista) {
				tabla.addCell("" + u.getIddetalle());
				tabla.addCell("" + u.getIdproducto());
				tabla.addCell("" + u.getIdventa());
				tabla.addCell("" + u.getCosto());
				tabla.addCell("" + u.getCantidad());
				tabla.addCell("" + u.getImporte());

			}

			doc.add(tabla);
			Paragraph p1 = new Paragraph(10);
			p1.add(Chunk.NEWLINE);
			p1.add("NÚMERO DE REGISTRO " + lista.size());
			p1.add(Chunk.NEWLINE);
			p1.add(Chunk.NEWLINE);
			p1.setAlignment(Element.ALIGN_RIGHT);
			doc.add(p1);
			doc.close();
			archivo.close();
			Desktop.getDesktop().open(temp);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
			} catch (DocumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
			JOptionPane.showMessageDialog(null, e.getStackTrace());

		}
	}


	public vDetalleV() {
		setClosable(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vDetalleV.class.getResource("/Img/logodesot.png")));
		//setLocationRelativeTo(null);
		setTitle("DetalleV");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 921, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(73, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Venta");
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 92, 112, 23);
		contentPane.add(lblNewLabel_1);
		
		txtcosto = new JTextField();
		txtcosto.setBounds(387, 59, 150, 20);
		contentPane.add(txtcosto);
		txtcosto.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Costo");
		lblNewLabel_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(291, 56, 86, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtimporte = new JTextField();
		txtimporte.setColumns(10);
		txtimporte.setBounds(132, 129, 117, 20);
		contentPane.add(txtimporte);
		
		JLabel lblNewLabel_1_2 = new JLabel("Producto");
		lblNewLabel_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 58, 112, 23);
		contentPane.add(lblNewLabel_1_2);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cboproducto.getSelectedItem().equals("")||cboventa.getSelectedItem().equals("")||txtcosto.getText().equals("")||txtcantidad.getText().equals("")||txtimporte.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					DetalleV user=new DetalleV();
					user.setIdproducto(Integer.parseInt(cboproducto.getSelectedItem().toString()));
					user.setIdventa(Integer.parseInt(cboventa.getSelectedItem().toString()));
					user.setCosto(Integer.parseInt(txtcosto.getText()));
					user.setCantidad(Integer.parseInt(txtcantidad.getText()));
					user.setImporte(Integer.parseInt(txtimporte.getText()));
					if (dao.insertarDetalleV(user)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se agrego correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}

			}
		});
		btnAgregar.setBounds(557, 44, 138, 35);
		btnAgregar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/agreagr.jpg")), 50, 20 ));
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarDetalleV(lista.get(fila).getIddetalle())) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se elimino correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
					}
				}catch(Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}
				
			}
		});
		btnEliminar.setBounds(716, 44, 136, 35);
		btnEliminar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/eliminar.png")), 50, 20 ));
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cboproducto.getSelectedItem().equals("")||cboventa.getSelectedItem().equals("")||txtcosto.getText().equals("")||txtcantidad.getText().equals("")||txtimporte.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					DetalleV.setIdproducto(Integer.parseInt(cboproducto.getSelectedItem().toString()));
					DetalleV.setIdventa(Integer.parseInt(cboventa.getSelectedItem().toString()));
					DetalleV.setCosto(Integer.parseInt(txtcosto.getText()));
					DetalleV.setCantidad(Integer.parseInt(txtcantidad.getText()));
					DetalleV.setImporte(Integer.parseInt(txtimporte.getText()));
					if (dao.editarDetalleV(DetalleV)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch (Exception e2) {
					
				}
				
			}
		});
		btnEditar.setBounds(557, 95, 138, 35);
		btnEditar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/editar.png")), 50, 20 ));
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 218, 885, 274);
		contentPane.add(scrollPane);
		
		tblDetalleV = new JTable();
		tblDetalleV.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblDetalleV.getSelectedRow();
				DetalleV=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIddetalle());
				cboproducto.setSelectedItem(""+DetalleV.getIdproducto());
				cboventa.setSelectedItem(""+DetalleV.getIdventa());
				txtcosto.setText(""+DetalleV.getCosto());
				txtcantidad.setText(""+DetalleV.getCantidad());
				txtimporte.setText(""+DetalleV.getImporte());
				
			}
		});
		tblDetalleV.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
				{null, null, null, null},
			},
			new String[] {
				"New column", "New column", "New column", "New column"
			}
		));
		scrollPane.setViewportView(tblDetalleV);
		
		modelo.addColumn("ID");
		modelo.addColumn("Idproducto");
		modelo.addColumn("Idventa");
		modelo.addColumn("Costo");
		modelo.addColumn("Cantidad");
		modelo.addColumn("Importe");
		tblDetalleV.setModel(modelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Cantidad");
		lblNewLabel_1_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(291, 92, 86, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Importe");
		lblNewLabel_1_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(10, 126, 112, 23);
		contentPane.add(lblNewLabel_1_1_2);
		
		txtcantidad = new JTextField();
		txtcantidad.setColumns(10);
		txtcantidad.setBounds(387, 95, 150, 20);
		contentPane.add(txtcantidad);
		
		cboventa = new JComboBox();
		cboventa.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboVenta();
			}
		});
		cboventa.setBounds(132, 92, 117, 22);
		contentPane.add(cboventa);
		
		cboproducto = new JComboBox();
		cboproducto.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboProducto();
			}
		});
		cboproducto.setBounds(132, 58, 117, 22);
		contentPane.add(cboproducto);
		
		btnPdf = new JButton("pdf");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdf();
			}
		});
		btnPdf.setBounds(716, 95, 136, 35);
		btnPdf.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 50, 20 ));
		contentPane.add(btnPdf);
		refrescarTabla();
		

	}
	
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchDetalleVs();
		for(DetalleV u: lista) {
			Object o[]=new Object [6];
			o[0]=u.getIddetalle();
			o[1]=u.getIdproducto();
			o[2]=u.getIdventa();
			o[3]=u.getCosto();
			o[4]=u.getCantidad();
			o[5]=u.getImporte();
			
			modelo.addRow(o);
		}
		tblDetalleV.setModel(modelo);
	}
}