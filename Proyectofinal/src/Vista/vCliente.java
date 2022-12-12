package Vista;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

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
import Modelo.Autos;
import Modelo.Cliente;
import Modelo.Producto;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vCliente extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtdomocilio;
	private JTextField txttelefono;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	private JButton btnPdf;
	DaoCliente dao=new DaoCliente();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblCliente;
	ArrayList<Cliente> lista = new ArrayList<Cliente>();
	int fila=-1;
	Cliente Cliente;
	Funciones fx = new Funciones();
	private JButton btnpdf;
	private JTextField txtBuscar;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vCliente frame = new vCliente();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtdomocilio.setText("");
		txttelefono.setText("");
		txtnombre.setText("");
	}
	
	public void pdf(){
		try {
			try {
				FileOutputStream archivo;				
	            File temp = new File(System.getProperty("java.io.tmpdir") + "Cliente.pdf");
	            InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/PDF/Cliente.pdf");
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
				Font negrita = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD, BaseColor.BLACK);
				p.add(Chunk.NEWLINE);
				p.add("CATALOGO DE PROVEEDORES");
				p.add(Chunk.NEWLINE);
				p.add(Chunk.NEWLINE);
				p.setAlignment(Element.ALIGN_CENTER);
				doc.add(p);
				// Tabla de datos
				PdfPTable tabla = new PdfPTable(4);
				tabla.setWidthPercentage(100);
				PdfPCell c1 = new PdfPCell(new Phrase("Idcliente", negrita));
				PdfPCell c2 = new PdfPCell(new Phrase("Domicilio", negrita));
				PdfPCell c3 = new PdfPCell(new Phrase("Telefono", negrita));
				PdfPCell c4 = new PdfPCell(new Phrase("Nombre", negrita));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				c4.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
				c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
				c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
				c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
				tabla.addCell(c1);
				tabla.addCell(c2);
				tabla.addCell(c3);
				tabla.addCell(c4);
				for (Cliente pro : lista) {
					tabla.addCell("" + pro.getIdcliente());
					tabla.addCell("" + pro.getDomicilio());
					tabla.addCell("" + pro.getTelefono());
					tabla.addCell("" + pro.getNombre());

				}
				doc.add(tabla);
				Paragraph p1 = new Paragraph(10);
				p1.add(Chunk.NEWLINE);
				p1.add("NÚMERO DE REGISTROS: " + lista.size());
				p1.add(Chunk.NEWLINE);
				p1.add(Chunk.NEWLINE);
				p1.setAlignment(Element.ALIGN_RIGHT);
				doc.add(p1);
				doc.close();
				archivo.close();
		        flujoSalida.close();
		        flujoEntrada.close();
				Desktop.getDesktop().open(temp);
			} catch (FileNotFoundException ex) {
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
				JOptionPane.showMessageDialog(null, ex.getMessage());
				JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
				JOptionPane.showMessageDialog(null, ex.getStackTrace());

			} catch (DocumentException ex) {
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
				JOptionPane.showMessageDialog(null, ex.getMessage());
				JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
			} catch (IOException ex) {
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
				JOptionPane.showMessageDialog(null, ex.getMessage());
				JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
				JOptionPane.showMessageDialog(null, ex.getStackTrace());
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
			JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
			JOptionPane.showMessageDialog(null, e.getStackTrace());

		}
	}

	public vCliente() {
		setClosable(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vCliente.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("Cliente");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 921, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(20, 94, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 20));
		lblid.setForeground(new Color(255, 255, 255));
		lblid.setHorizontalAlignment(SwingConstants.CENTER);
		lblid.setBorder(new LineBorder(new Color(255, 255, 255)));
		lblid.setBounds(64, 73, 80, 72);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Telefono");
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(169, 102, 85, 23);
		contentPane.add(lblNewLabel_1);
		
		txtdomocilio = new JTextField();
		txtdomocilio.setBounds(264, 55, 131, 20);
		contentPane.add(txtdomocilio);
		txtdomocilio.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Domicilio");
		lblNewLabel_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 16));
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(169, 54, 85, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txttelefono = new JTextField();
		txttelefono.setColumns(10);
		txttelefono.setBounds(264, 99, 131, 20);
		contentPane.add(txttelefono);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setBounds(169, 142, 85, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(264, 143, 131, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtdomocilio.getText().equals("")||txttelefono.getText().equals("")||txtnombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Cliente user=new Cliente();
					user.setDomicilio(txtdomocilio.getText());
					user.setTelefono(Integer.parseInt(txttelefono.getText()));
					user.setNombre(txtnombre.getText());
					if (dao.insertarCliente(user)) {
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
		btnAgregar.setBounds(430, 26, 187, 49);
		btnAgregar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/agreagr.jpg")), 50, 20 ));
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarCliente(lista.get(fila).getIdcliente())) {
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
		btnEliminar.setBounds(657, 26, 187, 49);
		btnEliminar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/eliminar.png")), 50, 20 ));
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtdomocilio.getText().equals("")||txttelefono.getText().equals("")||txtnombre.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Cliente.setDomicilio(txtdomocilio.getText());
					Cliente.setTelefono(Integer.parseInt(txttelefono.getText()));
					Cliente.setNombre(txtnombre.getText());
					if (dao.editarCliente(Cliente)) {
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
		btnEditar.setBounds(430, 98, 187, 49);
		btnEditar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/editar.png")), 50, 20 ));
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 254, 875, 238);
		contentPane.add(scrollPane);
		
		tblCliente = new JTable();
		tblCliente.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblCliente.getSelectedRow();
				Cliente=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdcliente());
				txtdomocilio.setText(""+Cliente.getDomicilio());
				txttelefono.setText(""+Cliente.getTelefono());
				txtnombre.setText(""+Cliente.getNombre());
				
			}
		});
		tblCliente.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblCliente);
		
		modelo.addColumn("ID");
		modelo.addColumn("Domicilio");
		modelo.addColumn("Telefono");
		modelo.addColumn("Nombre");
		tblCliente.setModel(modelo);
		
		btnpdf = new JButton("pdf");
		btnpdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdf();
			}
		});
		btnpdf.setBounds(659, 98, 185, 47);
		btnpdf.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 50, 20 ));
		contentPane.add(btnpdf);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());
			}
		});
		txtBuscar.setBounds(210, 220, 407, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Buscar:");
		lblNewLabel_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBackground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(53, 214, 142, 29);
		lblNewLabel_2.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/lupa.png")), 50, 20 ));
		contentPane.add(lblNewLabel_2);
		refrescarTabla();
	}
	
	
	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista=dao.fecthBuscar(palabra);
		for(Cliente u: lista) {
				Object o[]=new Object [4];
				o[0]=u.getIdcliente();
				o[1]=u.getDomicilio();
				o[2]=u.getTelefono();
				o[3]=u.getNombre();
				modelo.addRow(o);
		}
		tblCliente.setModel(modelo);
	}
	
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchClientes();
		for(Cliente u: lista) {
			Object o[]=new Object [4];
			o[0]=u.getIdcliente();
			o[1]=u.getDomicilio();
			o[2]=u.getTelefono();
			o[3]=u.getNombre();
			modelo.addRow(o);
		}
		tblCliente.setModel(modelo);
	}
}
