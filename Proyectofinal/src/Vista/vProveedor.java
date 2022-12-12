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

import Dao.DaoProveedor;
import Modelo.Autos;
import Modelo.Producto;
import Modelo.Proveedor;

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

public class vProveedor extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txtdescripcion;
	private JTextField txtcontacto;
	private JTextField txtnombre;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	DaoProveedor dao=new DaoProveedor();
	DefaultTableModel modelo=new DefaultTableModel();
	private JScrollPane scrollPane;
	private JTable tblProveedor;
	ArrayList<Proveedor> lista = new ArrayList<Proveedor>();
	Funciones fx = new Funciones();
	int fila=-1;
	Proveedor Proveedor;
	private JButton btnPdf;
	private JLabel lblNewLabel_2;
	private JTextField txtBuscar;
	

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vProveedor frame = new vProveedor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void limpiar() {
		lblid.setText("");
		txtdescripcion.setText("");
		txtcontacto.setText("");
		txtnombre.setText("");
	}
	
	public void pdf() {
		try {

			try {
				FileOutputStream archivo;				
	            File temp = new File(System.getProperty("java.io.tmpdir") + "Proveedor.pdf");
	            InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/PDF/Proveedor.pdf");
	            FileOutputStream flujoSalida = new FileOutputStream(temp);         
				archivo = new FileOutputStream(temp);
				Document doc = new Document();
				PdfWriter.getInstance(doc, archivo);
				doc.open();
			java.awt.Image Img2 = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Img/logodesot.jpg"));
			Image Img = Image.getInstance(getClass().getResource("/Img/logodesot.jpg"));
			Img.setAlignment(Element.ALIGN_CENTER);
            Img.scaleToFit(200, 200);
			doc.add(Img);
			Paragraph p = new Paragraph(10);
			com.itextpdf.text.Font negrita = new com.itextpdf.text.Font(com.itextpdf.text.Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD, BaseColor.BLACK);
			p.add(Chunk.NEWLINE);
			p.add("Proveedor");
			p.add(Chunk.NEWLINE);
			p.add(Chunk.NEWLINE);
			p.setAlignment(Element.ALIGN_CENTER);
			doc.add(p);
			PdfPTable tabla = new PdfPTable(4);
			tabla.setWidthPercentage(100);
			PdfPCell c1 = new PdfPCell(new Phrase(" Idproveedor", negrita));
			PdfPCell c2 = new PdfPCell(new Phrase(" Nombreproveedor", negrita));
			PdfPCell c3 = new PdfPCell(new Phrase(" Contacto", negrita));
			PdfPCell c4 = new PdfPCell(new Phrase(" Descripcion", negrita));		
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

			for (Proveedor u : lista) {
				tabla.addCell("" + u.getIdproveedor());
				tabla.addCell("" + u.getNombreproveedor());
				tabla.addCell("" + u.getContacto());
				tabla.addCell("" + u.getDescripcion());

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

	public vProveedor() {
		setClosable(true);
		//setIconImage(Toolkit.getDefaultToolkit().getImage(vProveedor.class.getResource("/Img/icono.jpg")));
		//setLocationRelativeTo(null);
		setTitle("Proveedor");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 921, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel.setBounds(22, 42, 33, 23);
		contentPane.add(lblNewLabel);
		
		lblid = new JLabel("1");
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblid.setBounds(75, 44, 86, 23);
		contentPane.add(lblid);
		
		JLabel lblNewLabel_1 = new JLabel("Contacto");
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1.setBounds(10, 126, 104, 23);
		contentPane.add(lblNewLabel_1);
		
		txtdescripcion = new JTextField();
		txtdescripcion.setBounds(328, 45, 129, 20);
		contentPane.add(txtdescripcion);
		txtdescripcion.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Descripcion");
		lblNewLabel_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(212, 42, 86, 23);
		contentPane.add(lblNewLabel_1_1);
		
		txtcontacto = new JTextField();
		txtcontacto.setColumns(10);
		txtcontacto.setBounds(124, 129, 129, 20);
		contentPane.add(txtcontacto);
		
		JLabel lblNewLabel_1_2 = new JLabel("Nombre");
		lblNewLabel_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(10, 89, 104, 23);
		contentPane.add(lblNewLabel_1_2);
		
		txtnombre = new JTextField();
		txtnombre.setColumns(10);
		txtnombre.setBounds(124, 92, 129, 20);
		contentPane.add(txtnombre);
		
		btnAgregar = new JButton("Agregar");
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txtcontacto.getText().equals("")||txtdescripcion.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Proveedor user=new Proveedor();
					user.setNombreproveedor(txtnombre.getText());
					user.setContacto(Integer.parseInt(txtcontacto.getText()));
					user.setDescripcion(txtdescripcion.getText());
					if (dao.insertarProveedor(user)) {
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
		btnAgregar.setBounds(497, 28, 140, 55);
		btnAgregar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/agreagr.jpg")), 50, 20 ));
		contentPane.add(btnAgregar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion =JOptionPane.showConfirmDialog(null , "Estas seguro de eliminar");
					if(opcion==0) {
					if (dao.eliminarProveedor(lista.get(fila).getIdproveedor())) {
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
		btnEliminar.setBounds(658, 28, 140, 55);
		btnEliminar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/eliminar.png")), 50, 20 ));
		contentPane.add(btnEliminar);
		
		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(txtnombre.getText().equals("")||txtcontacto.getText().equals("")||txtdescripcion.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Proveedor.setNombreproveedor(txtnombre.getText());
					Proveedor.setContacto(Integer.parseInt(txtcontacto.getText()));
					Proveedor.setDescripcion(txtdescripcion.getText());
					if (dao.editarProveedor(Proveedor)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					}else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				}catch (Exception e2) {
					e2.printStackTrace();
				}
				
			}
		});
		btnEditar.setBounds(497, 94, 140, 55);
		btnEditar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/editar.png")), 50, 20 ));
		contentPane.add(btnEditar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 246, 885, 246);
		contentPane.add(scrollPane);
		
		tblProveedor = new JTable();
		tblProveedor.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila=tblProveedor.getSelectedRow();
				Proveedor=lista.get(fila);
				lblid.setText(""+lista.get(fila).getIdproveedor());
				txtnombre.setText(""+Proveedor.getNombreproveedor());
				txtcontacto.setText(""+Proveedor.getContacto());
				txtdescripcion.setText(""+Proveedor.getDescripcion());
				
				
			}
		});
		tblProveedor.setModel(new DefaultTableModel(
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
		scrollPane.setViewportView(tblProveedor);
		
		modelo.addColumn("ID");
		modelo.addColumn("Nombre");
		modelo.addColumn("Contacto");
		modelo.addColumn("Descripcion");
		tblProveedor.setModel(modelo);
		
		btnPdf = new JButton("Pdf");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdf();
			}
		});
		btnPdf.setBounds(658, 92, 140, 55);
		btnPdf.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 50, 20 ));
		contentPane.add(btnPdf);
		
		lblNewLabel_2 = new JLabel("Buscar:");
		lblNewLabel_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_2.setBounds(303, 145, 154, 45);
		lblNewLabel_2.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/lupa.png")), 50, 20 ));
		contentPane.add(lblNewLabel_2);
		
		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());
			}
		});
		txtBuscar.setBounds(212, 201, 324, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		refrescarTabla();
	}
	
	public void refrescarTabla2(String palabra) {
		while (modelo.getRowCount() > 0) {
			modelo.removeRow(0);
		}
		lista=dao.fecthBuscar(palabra);
		for(Proveedor u: lista) {
			Object o[]=new Object [4];
			o[0]=u.getIdproveedor();
			o[1]=u.getNombreproveedor();
			o[2]=u.getContacto();
			o[3]=u.getDescripcion();
			modelo.addRow(o);
		}
		tblProveedor.setModel(modelo);
	}
	
	public void refrescarTabla() {
		while(modelo.getRowCount()>0) {
		modelo.removeRow(0);
		}
		lista=dao.fetchProveedor();
		for(Proveedor u: lista) {
			Object o[]=new Object [4];
			o[0]=u.getIdproveedor();
			o[1]=u.getNombreproveedor();
			o[2]=u.getContacto();
			o[3]=u.getDescripcion();
			modelo.addRow(o);
		}
		tblProveedor.setModel(modelo);
	}
}
