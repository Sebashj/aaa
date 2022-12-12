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

import Dao.DaoCliente;
import Dao.DaoEmpleado;
import Dao.DaoVenta;
import Modelo.Autos;
import Modelo.Cliente;
import Modelo.Empleado;
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

public class vVenta extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtlugar;
	private JTextField txtmonto;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoVenta dao=new DaoVenta();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblVenta;
	ArrayList<Venta> lista = new ArrayList<Venta>();
	int fila=-1;
	Venta Venta;
	private JTextField txtfecha;
	ArrayList<Cliente> listaCliente=new ArrayList<Cliente>();
	ArrayList<Empleado> listaEmpleado=new ArrayList<Empleado>();
	private JComboBox cboempleado;
	private JComboBox cbocliente;
	private JLabel lblNewLabel_1_1_3;
	private JTextField txtnopedido;
	private JButton btnPdf;
	private JLabel lblNewLabel_2;
	private JTextField txtBuscar;
	DefaultTableModel model = new DefaultTableModel();
	Funciones fx = new Funciones();
	ArrayList<Venta> listaVenta = null;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vVenta frame = new vVenta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtlugar.setText("");
		txtmonto.setText("");
		txtfecha.setText("");
	}
	public void cargarComboEmpleado() {
		DaoEmpleado daoPro=new DaoEmpleado();		
		listaEmpleado=daoPro.fetchEmpleados();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Empleado Venta : listaEmpleado) {
		 	model.addElement(Venta.getIdempleado());
		}
		cboempleado.setModel(model);
	}
	
	public String Empleado(int id) {
		for (Empleado Empleado : listaEmpleado) {
			if(Empleado.getIdempleado()==id) {
				return Empleado.getNombre();
			}
		}
		return null;
	}
	public void cargarComboCliente() {
		DaoCliente daoPro=new DaoCliente();		
		listaCliente=daoPro.fetchClientes();
		DefaultComboBoxModel model=new DefaultComboBoxModel();
		for (Cliente Venta : listaCliente) {
		 	model.addElement(Venta.getIdcliente());
		}
		cbocliente.setModel(model);
	}
	
	public String Cliente(int id) {
		for (Cliente Cliente : listaCliente) {
			if(Cliente.getIdcliente()==id) {
				return Cliente.getNombre();
			}
		}
		return  null;
	}
	public void pdf() {
		try {

			try {
				FileOutputStream archivo;				
	            File temp = new File(System.getProperty("java.io.tmpdir") + "venta.pdf");
	            InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/PDF/venta.pdf");
	            FileOutputStream flujoSalida = new FileOutputStream(temp);         
				archivo = new FileOutputStream(temp);
				Document doc = new Document();
				PdfWriter.getInstance(doc, archivo);
				doc.open();
			java.awt.Image Img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logodesot.jpg"));
			Image Img = Image.getInstance(getClass().getResource("/Img/logodesot.jpg"));
			//°
			Img.setAlignment(Element.ALIGN_CENTER);
			Img.scaleToFit(200, 200);
			doc.add(Img);
			Paragraph p = new Paragraph(10);
			com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
			p.add(Chunk.NEWLINE);
			p.add("Venta");
			p.add(Chunk.NEWLINE);
			p.add(Chunk.NEWLINE);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			PdfPTable tabla = new PdfPTable(7);
			tabla.setWidthPercentage(100);
			PdfPCell c1 = new PdfPCell(new Phrase(" Idventa", negrita));
			PdfPCell c2 = new PdfPCell(new Phrase(" Idcliente", negrita));
			PdfPCell c3 = new PdfPCell(new Phrase(" Idempleado", negrita));
			PdfPCell c4 = new PdfPCell(new Phrase(" Lugar", negrita));
			PdfPCell c5 = new PdfPCell(new Phrase(" Fechao", negrita));
			PdfPCell c6 = new PdfPCell(new Phrase(" Monto", negrita));
			PdfPCell c7 = new PdfPCell(new Phrase(" Nopedido", negrita));		
			c1.setHorizontalAlignment(Element.ALIGN_CENTER);
			c2.setHorizontalAlignment(Element.ALIGN_CENTER);
			c3.setHorizontalAlignment(Element.ALIGN_CENTER);
			c4.setHorizontalAlignment(Element.ALIGN_CENTER);
			c5.setHorizontalAlignment(Element.ALIGN_CENTER);
			c6.setHorizontalAlignment(Element.ALIGN_CENTER);
			c7.setHorizontalAlignment(Element.ALIGN_CENTER);
			c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c6.setBackgroundColor(BaseColor.LIGHT_GRAY);
			c7.setBackgroundColor(BaseColor.LIGHT_GRAY);
			tabla.addCell(c1);
			tabla.addCell(c2);
			tabla.addCell(c3);
			tabla.addCell(c4);
			tabla.addCell(c5);
			tabla.addCell(c6);
			tabla.addCell(c7);

			for (Venta u : lista) {
				tabla.addCell("" + u.getIdventa());
				tabla.addCell("" + u.getIdcliente());
				tabla.addCell("" + u.getIdempleado());
				tabla.addCell("" + u.getLugar());
				tabla.addCell("" + u.getFecha());
				tabla.addCell("" + u.getMonto());
				tabla.addCell("" + u.getNopedido());

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


	public vVenta() {
		setClosable(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vVenta.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("Venta");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 921, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(73, 26, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Empleado");
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 106, 96, 23);
		contentPane.add(lblNewLabel_1);
		
		txtlugar = new JTextField();
		txtlugar.setBounds(116, 164, 107, 20);
		contentPane.add(txtlugar);
		txtlugar.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Lugar");
		lblNewLabel_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(10, 161, 96, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtmonto = new JTextField();
		txtmonto.setColumns(10);
		txtmonto.setBounds(354, 95, 133, 20);
		contentPane.add(txtmonto);
		
		JLabel lblNewLabel_1_2 = new JLabel("Cliente");
		lblNewLabel_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 58, 96, 23);
		contentPane.add(lblNewLabel_1_2);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cbocliente.getSelectedItem().equals("")||cboempleado.getSelectedItem().equals("")||txtlugar.getText().equals("")||txtfecha.getText().equals("")||txtmonto.getText().equals("")||txtnopedido.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Venta user=new Venta();
					user.setIdcliente(cbocliente.getSelectedItem().toString());
					user.setIdempleado(cboempleado.getSelectedItem().toString());
					user.setLugar(txtlugar.getText());
					user.setFecha(txtfecha.getText());
					user.setMonto(Integer.parseInt(txtmonto.getText()));
					user.setNopedido(Integer.parseInt(txtnopedido.getText()));
					if (dao.insertarVenta(user)) {
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
		btnAgregar.setBounds(520, 26, 146, 55);
		btnAgregar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/agreagr.jpg")), 50, 20 ));
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarVenta(lista.get(fila).getIdventa())) {
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
		btnEliminar.setBounds(695, 26, 146, 55);
		btnEliminar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/eliminar.png")), 50, 20 ));
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(cbocliente.getSelectedItem().equals("")||cboempleado.getSelectedItem().equals("")||txtlugar.getText().equals("")||txtfecha.getText().equals("")||txtmonto.getText().equals("")||txtnopedido.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Venta.setIdcliente(cbocliente.getSelectedItem().toString());
					Venta.setIdempleado(cboempleado.getSelectedItem().toString());
					Venta.setLugar(txtlugar.getText());
					Venta.setFecha(txtfecha.getText());
					Venta.setMonto(Integer.parseInt(txtmonto.getText()));
					Venta.setNopedido(Integer.parseInt(txtnopedido.getText()));
					if (dao.editarVenta(Venta)) {
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
		btnEditar.setBounds(520, 92, 146, 55);
		btnEditar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/editar.png")), 50, 20 ));
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 247, 875, 245);
		contentPane.add(scrollPane);
		
		tblVenta = new JTable();
		tblVenta.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblVenta.getSelectedRow();
				Venta=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdventa());
				cbocliente.setSelectedItem(""+Venta.getIdcliente());
				cboempleado.setSelectedItem(""+Venta.getIdempleado());
				txtlugar.setText(""+Venta.getLugar());
				txtfecha.setText(""+Venta.getFecha());
				txtmonto.setText(""+Venta.getMonto());
				txtnopedido.setText(""+Venta.getNopedido());
				
			}
		});
		tblVenta.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblVenta);
		
		modelo.addColumn("ID");
		modelo.addColumn("Idcliente");
		modelo.addColumn("Idempleado");
		modelo.addColumn("Lugar");
		modelo.addColumn("Fecha");
		modelo.addColumn("Monto");
		modelo.addColumn("Nopedido");
		tblVenta.setModel(modelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Fecha");
		lblNewLabel_1_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(258, 40, 86, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Monto");
		lblNewLabel_1_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(248, 92, 96, 23);
		contentPane.add(lblNewLabel_1_1_2);
		
		txtfecha = new JTextField();
		txtfecha.setColumns(10);
		txtfecha.setBounds(354, 43, 133, 20);
		contentPane.add(txtfecha);
		
		cboempleado = new JComboBox();
		cboempleado.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboEmpleado();
			}
		});
		cboempleado.setBounds(116, 108, 107, 22);
		contentPane.add(cboempleado);
		
		cbocliente = new JComboBox();
		cbocliente.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				cargarComboCliente();
			}
		});
		cbocliente.setBounds(116, 60, 107, 22);
		contentPane.add(cbocliente);
		
		lblNewLabel_1_1_3 = new JLabel("No. Pedido");
		lblNewLabel_1_1_3.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_3.setBounds(248, 138, 96, 23);
		contentPane.add(lblNewLabel_1_1_3);
		
		txtnopedido = new JTextField();
		txtnopedido.setColumns(10);
		txtnopedido.setBounds(354, 141, 133, 20);
		contentPane.add(txtnopedido);
		
		btnPdf = new JButton("pdf");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdf();
			}
		});
		btnPdf.setBounds(695, 92, 146, 52);
		btnPdf.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 50, 20 ));
		contentPane.add(btnPdf);
		
		lblNewLabel_2 = new JLabel("Buscar");
		lblNewLabel_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_2.setBounds(303, 172, 119, 33);
		lblNewLabel_2.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/lupa.png")), 50, 20 ));
		contentPane.add(lblNewLabel_2);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());
			}
		});
		txtBuscar.setBounds(217, 216, 300, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		refrescarTabla();
	}
	
	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista=dao.fecthBuscar(palabra);
		for(Venta u: lista) {
			Object o[]=new Object [7];
			o[0]=u.getIdventa();
			o[1]=u.getIdcliente();
			o[2]=u.getIdempleado();
			o[3]=u.getLugar();
			o[4]=u.getFecha();
			o[5]=u.getMonto();
			o[6]=u.getNopedido();
			
			modelo.addRow(o);
		}
		tblVenta.setModel(modelo);
	}
	
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchVentas();
		for(Venta u: lista) {
			Object o[]=new Object [7];
			o[0]=u.getIdventa();
			o[1]=u.getIdcliente();
			o[2]=u.getIdempleado();
			o[3]=u.getLugar();
			o[4]=u.getFecha();
			o[5]=u.getMonto();
			o[6]=u.getNopedido();
			
			modelo.addRow(o);
		}
		tblVenta.setModel(modelo);
	}

}