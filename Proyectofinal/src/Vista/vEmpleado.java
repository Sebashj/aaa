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

import Dao.DaoEmpleado;
import Modelo.Autos;
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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vEmpleado extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtdomocilio;
	private JTextField txttelefono;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoEmpleado dao=new DaoEmpleado();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblEmpleado;
	ArrayList<Empleado> lista = new ArrayList<Empleado>();
	int fila=-1;
	Empleado Empleado;
	Funciones fx = new Funciones();
	private JTextField txtpuesto;
	private JTextField txtpassword;
	private JButton btnpdf;
	private JLabel lblNewLabel_2;
	private JTextField textBuscar;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vEmpleado frame = new vEmpleado();
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
		txtpuesto.setText("");
		txtpassword.setText("");
	}
	
	public void pdf() {
		try {

			try {
				FileOutputStream archivo;				
	            File temp = new File(System.getProperty("java.io.tmpdir") + "Empleado.pdf");
	            InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/PDF/Empleado.pdf");
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
			p.add("Empleado");
			p.add(Chunk.NEWLINE);
			p.add(Chunk.NEWLINE);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			PdfPTable tabla = new PdfPTable(6);
			tabla.setWidthPercentage(100);
			PdfPCell c1 = new PdfPCell(new Phrase(" Idempleado", negrita));
			PdfPCell c2 = new PdfPCell(new Phrase(" Nombre", negrita));
			PdfPCell c3 = new PdfPCell(new Phrase(" Telefono", negrita));
			PdfPCell c4 = new PdfPCell(new Phrase(" Domicilio", negrita));
			PdfPCell c5 = new PdfPCell(new Phrase(" Puesto", negrita));
			PdfPCell c6 = new PdfPCell(new Phrase(" Password", negrita));	
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

			for (Empleado u : lista) {
				tabla.addCell("" + u.getIdempleado());
				tabla.addCell("" + u.getNombre());
				tabla.addCell("" + u.getTelefono());
				tabla.addCell("" + u.getDomicilio());
				tabla.addCell("" + u.getPuesto());
				tabla.addCell("" + u.getPassword());

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

	public vEmpleado() {
		setClosable(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vEmpleado.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("Empleado");
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
		
		JLabel lblNewLabel_1 = new JLabel("Telefono");
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 92, 86, 23);
		contentPane.add(lblNewLabel_1);
		
		txtdomocilio = new JTextField();
		txtdomocilio.setBounds(322, 61, 108, 20);
		contentPane.add(txtdomocilio);
		txtdomocilio.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Domicilio");
		lblNewLabel_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(226, 58, 86, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txttelefono = new JTextField();
		txttelefono.setColumns(10);
		txttelefono.setBounds(106, 95, 108, 20);
		contentPane.add(txttelefono);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 58, 86, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(106, 64, 108, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txttelefono.getText().equals("")||txtdomocilio.getText().equals("")||txtpuesto.getText().equals("")||txtpassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Empleado user=new Empleado();
					user.setNombre(txtnombre.getText());
					user.setTelefono(Integer.parseInt(txttelefono.getText()));
					user.setDomicilio(txtdomocilio.getText());
					user.setPuesto(txtpuesto.getText());
					user.setPassword(txtpassword.getText());
					if (dao.insertarEmpleado(user)) {
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
		btnAgregar.setBounds(452, 26, 145, 67);
		btnAgregar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/agreagr.jpg")), 50, 20 ));
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarEmpleado(lista.get(fila).getIdempleado())) {
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
		btnEliminar.setBounds(452, 104, 145, 67);
		btnEliminar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/eliminar.png")), 50, 20 ));
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txttelefono.getText().equals("")||txtdomocilio.getText().equals("")||txtpuesto.getText().equals("")||txtpassword.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Empleado.setNombre(txtnombre.getText());
					Empleado.setTelefono(Integer.parseInt(txttelefono.getText()));
					Empleado.setDomicilio(txtdomocilio.getText());
					Empleado.setPuesto(txtpuesto.getText());
					Empleado.setPassword(txtpassword.getText());
					if (dao.editarEmpleado(Empleado)) {
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
		btnEditar.setBounds(621, 28, 145, 65);
		btnEditar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/editar.png")), 50, 20 ));
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 230, 885, 262);
		contentPane.add(scrollPane);
		
		tblEmpleado = new JTable();
		tblEmpleado.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblEmpleado.getSelectedRow();
				Empleado=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdempleado());
				txtnombre.setText(""+Empleado.getNombre());
				txttelefono.setText(""+Empleado.getTelefono());
				txtdomocilio.setText(""+Empleado.getDomicilio());
				txtpuesto.setText(""+Empleado.getPuesto());
				txtpassword.setText(""+Empleado.getPassword());
				
			}
		});
		tblEmpleado.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblEmpleado);
		
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Telefono");
		modelo.addColumn("Domicilio");
		modelo.addColumn("Puesto");
		modelo.addColumn("Password");
		tblEmpleado.setModel(modelo);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Puesto");
		lblNewLabel_1_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(226, 92, 86, 23);
		contentPane.add(lblNewLabel_1_1_1);
		
		JLabel lblNewLabel_1_1_2 = new JLabel("Password");
		lblNewLabel_1_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_2.setBounds(10, 134, 108, 23);
		contentPane.add(lblNewLabel_1_1_2);
		
		txtpuesto = new JTextField();
		txtpuesto.setColumns(10);
		txtpuesto.setBounds(322, 95, 108, 20);
		contentPane.add(txtpuesto);
		
		txtpassword = new JTextField();
		txtpassword.setColumns(10);
		txtpassword.setBounds(128, 137, 145, 20);
		contentPane.add(txtpassword);
		
		btnpdf = new JButton("pdf");
		btnpdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdf();
			}
		});
		btnpdf.setBounds(621, 104, 145, 67);
		btnpdf.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 50, 20 ));
		contentPane.add(btnpdf);
		
		lblNewLabel_2 = new JLabel("Buscar:");
		lblNewLabel_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_2.setBounds(106, 182, 108, 39);
		lblNewLabel_2.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/lupa.png")), 50, 20 ));
		contentPane.add(lblNewLabel_2);
		
		textBuscar = new JTextField();
		textBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(textBuscar.getText().toString());
			}
		});
		textBuscar.setBounds(226, 188, 250, 31);
		contentPane.add(textBuscar);
		textBuscar.setColumns(10);
		refrescarTabla();
	}
	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista=dao.fecthBuscar(palabra);
		for(Empleado u: lista) {
			Object o[]=new Object [6];
			o[0]=u.getIdempleado();
			o[1]=u.getNombre();
			o[2]=u.getTelefono();
			o[3]=u.getDomicilio();
			o[4]=u.getPuesto();
			o[5]=u.getPassword();
			modelo.addRow(o);
		}
		tblEmpleado.setModel(modelo);
	}
	
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchEmpleados();
		for(Empleado u: lista) {
			Object o[]=new Object [6];
			o[0]=u.getIdempleado();
			o[1]=u.getNombre();
			o[2]=u.getTelefono();
			o[3]=u.getDomicilio();
			o[4]=u.getPuesto();
			o[5]=u.getPassword();
			modelo.addRow(o);
		}
		tblEmpleado.setModel(modelo);
	}
}
