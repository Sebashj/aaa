package Vista;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
//import javax.swinpackage Vista;

import java.awt.EventQueue;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.imageio.ImageIO;
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

import Dao.DaoAutos;
import Decoder.BASE64Decoder;
import Modelo.DetalleV;
import Modelo.Autos;
import Modelo.Cliente;
import Modelo.Proveedor;
import Modelo.Venta;

import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Color;
import java.awt.Desktop;

import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Base64;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.awt.Toolkit;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;

import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class vAuto extends JInternalFrame {

	private JPanel contentPane;
	private JLabel lblid;
	private JTextField txttipodeauto;
	private JTextField txtaño;
	private JButton btnAgregar;
	private JButton btnEliminar;
	private JButton btnEditar;
	ImageIcon imgOri = null;
	String imagenActual = "";// para imagen
	DaoAutos dao = new DaoAutos();
	private JScrollPane scrollPane;
	private JTable tblAuto;
	ArrayList<Autos> lista = new ArrayList<Autos>();
	int fila = -1;
	Autos Autos;
	private JTextField txtclindros;
	private JButton btnPdf;
	private JTextField txtmarca;
	private JButton btnimagen;
	private JLabel lblImagen;
	Funciones fx = new Funciones();
	private JTextField txtBuscar;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vAuto frame = new vAuto();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void limpiar() {
		lblid.setText("");
		txttipodeauto.setText("");
		txtaño.setText("");
		txtclindros.setText("");
	}

	public void pdf() {
		try {

			try {
				FileOutputStream archivo;				
	            File temp = new File(System.getProperty("java.io.tmpdir") + "Autos.pdf");
	            InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/PDF/Autos.pdf");
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
				PdfPTable tabla = new PdfPTable(5);
				tabla.setWidthPercentage(100);
				PdfPCell c1 = new PdfPCell(new Phrase("Idauto", negrita));
				PdfPCell c2 = new PdfPCell(new Phrase("Marca", negrita));
				PdfPCell c3 = new PdfPCell(new Phrase("Año", negrita));
				PdfPCell c4 = new PdfPCell(new Phrase("Tipodeauto", negrita));
				PdfPCell c5 = new PdfPCell(new Phrase("Cilindros()", negrita));
				c1.setHorizontalAlignment(Element.ALIGN_CENTER);
				c2.setHorizontalAlignment(Element.ALIGN_CENTER);
				c3.setHorizontalAlignment(Element.ALIGN_CENTER);
				c4.setHorizontalAlignment(Element.ALIGN_CENTER);
				c5.setHorizontalAlignment(Element.ALIGN_CENTER);
				c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
				c2.setBackgroundColor(BaseColor.LIGHT_GRAY);
				c3.setBackgroundColor(BaseColor.LIGHT_GRAY);
				c4.setBackgroundColor(BaseColor.LIGHT_GRAY);
				c5.setBackgroundColor(BaseColor.LIGHT_GRAY);
				tabla.addCell(c1);
				tabla.addCell(c2);
				tabla.addCell(c3);
				tabla.addCell(c4);
				tabla.addCell(c5);
				for (Autos pro : lista) {
					tabla.addCell("" + pro.getIdauto());
					tabla.addCell("" + pro.getMarca());
					tabla.addCell("" + pro.getAño());
					tabla.addCell("" + pro.getTipodeauto());
					tabla.addCell("" + pro.getCilindros());

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

	public vAuto() {
		setBackground(new Color(128, 0, 0));
		setClosable(true);
// setIconImage(Toolkit.getDefaultToolkit().getImage(vAuto.class.getResource("/Img/icono.jpg")));
//setLocationRelativeTo(null);
		setTitle("Auto");
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 921, 533);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 128, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Id");
		lblNewLabel.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setBounds(20, 26, 33, 23);
		contentPane.add(lblNewLabel);

		lblid = new JLabel("1");
		lblid.setBackground(new Color(255, 255, 255));
		lblid.setForeground(new Color(255, 255, 255));
		lblid.setHorizontalAlignment(SwingConstants.LEFT);
		lblid.setBorder(new LineBorder(new Color(255, 255, 255)));
		lblid.setBounds(73, 26, 86, 23);
		contentPane.add(lblid);

		JLabel lblNewLabel_1 = new JLabel("Año");
		lblNewLabel_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setBounds(10, 92, 133, 23);
		contentPane.add(lblNewLabel_1);

		txttipodeauto = new JTextField();
		txttipodeauto.setBounds(163, 124, 86, 20);
		contentPane.add(txttipodeauto);
		txttipodeauto.setColumns(10);

		JLabel lblNewLabel_1_1 = new JLabel("Tipo de Auto");
		lblNewLabel_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1.setBounds(10, 123, 133, 23);
		contentPane.add(lblNewLabel_1_1);

		txtaño = new JTextField();
		txtaño.setColumns(10);
		txtaño.setBounds(163, 93, 86, 20);
		contentPane.add(txtaño);

		JLabel lblNewLabel_1_2 = new JLabel("Marca del auto");
		lblNewLabel_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_2.setBounds(10, 58, 133, 23);
		contentPane.add(lblNewLabel_1_2);

		btnAgregar = new JButton("Agregar");
		btnAgregar.setBackground(new Color(0, 128, 255));
		btnAgregar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtmarca.getText().equals("") || txtaño.getText().equals("")
							|| txttipodeauto.getText().equals("") || txtclindros.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Autos user = new Autos();
					user.setMarca(txtmarca.getText());
					user.setAño(Integer.parseInt(txtaño.getText()));
					user.setTipodeauto(txttipodeauto.getText());
					user.setCilindros(txtclindros.getText());
					user.setImagen(imagenActual);
					if (dao.create(user)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se agrego correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}

			}
		});
		btnAgregar.setBounds(277, 17, 171, 40);
		btnAgregar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/agreagr.jpg")), 50, 20));
		contentPane.add(btnAgregar);

		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int opcion = JOptionPane.showConfirmDialog(null, "Estas seguro de eliminar");
					if (opcion == 0) {
						if (dao.delete(lista.get(fila).getIdauto())) {
							refrescarTabla();
							limpiar();
							JOptionPane.showMessageDialog(null, "Se elimino correctamente");
						} else {
							JOptionPane.showMessageDialog(null, "Error");
						}
					}
				} catch (Exception ex) {
					ex.printStackTrace();
					JOptionPane.showMessageDialog(null, "Error");
				}

			}
		});
		btnEliminar.setBounds(277, 114, 171, 40);
		btnEliminar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/eliminar.png")), 50, 20));
		contentPane.add(btnEliminar);

		btnEditar = new JButton("editar");
		btnEditar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if (txtmarca.getText().equals("") || txtaño.getText().equals("")
							|| txttipodeauto.getText().equals("") || txtclindros.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "campos vacios");
						return;
					}
					Autos.setMarca(txtmarca.getText());
					Autos.setAño(Integer.parseInt(txtaño.getText()));
					Autos.setTipodeauto(txttipodeauto.getText());
					Autos.setCilindros(txtclindros.getText());
					Autos.setImagen(imagenActual);
					if (dao.update(Autos)) {
						refrescarTabla();
						limpiar();
						JOptionPane.showMessageDialog(null, "Se edito correctamente");
					} else {
						JOptionPane.showMessageDialog(null, "Error");
					}
				} catch (Exception e2) {

				}

			}
		});
		btnEditar.setBounds(277, 68, 171, 40);
		btnEditar.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/editar.png")), 50, 20));
		contentPane.add(btnEditar);

		scrollPane = new JScrollPane();
		scrollPane.setForeground(new Color(0, 128, 192));
		scrollPane.setBackground(new Color(0, 128, 192));
		scrollPane.setBounds(10, 218, 885, 274);
		contentPane.add(scrollPane);

		tblAuto = new JTable();
		tblAuto.setGridColor(new Color(0, 128, 192));
		tblAuto.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				fila = tblAuto.getSelectedRow();
				Autos = lista.get(fila);
				lblid.setText("" + lista.get(fila).getIdauto());
				txtmarca.setText("" + Autos.getMarca());
				txtaño.setText("" + Autos.getAño());
				txttipodeauto.setText("" + Autos.getTipodeauto());
				txtclindros.setText("" + Autos.getCilindros());
				ImageIcon Img = base64ToImage(Autos.getImagen());
				java.awt.Image Image = Img.getImage(); // transform it // transform it
				java.awt.Image newimg = Image.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(),
						Image.SCALE_SMOOTH);
				ImageIcon i = new ImageIcon(newimg);
				lblImagen.setIcon(i);

			}
		});
		tblAuto.setModel(new DefaultTableModel(
				new Object[][] { { null, null, null, null }, { null, null, null, null }, { null, null, null, null },
						{ null, null, null, null }, },
				new String[] { "New column", "New column", "New column", "New column" }));
		scrollPane.setViewportView(tblAuto);

		JLabel lblNewLabel_1_1_1 = new JLabel("Cilindros");
		lblNewLabel_1_1_1.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_1.setBounds(10, 150, 133, 23);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_1_2 = new JLabel("Agregar Imagen");
		lblNewLabel_1_1_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_1_1_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_1_1_2.setBounds(492, 97, 133, 23);
		contentPane.add(lblNewLabel_1_1_2);

		txtclindros = new JTextField();
		txtclindros.setColumns(10);
		txtclindros.setBounds(163, 155, 86, 20);
		contentPane.add(txtclindros);

		btnPdf = new JButton("pdf");
		btnPdf.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pdf();
			}
		});
		btnPdf.setBounds(474, 29, 157, 52);
		btnPdf.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 50, 20));
		contentPane.add(btnPdf);

		txtmarca = new JTextField();
		txtmarca.setColumns(10);
		txtmarca.setBounds(163, 59, 86, 20);
		contentPane.add(txtmarca);

		btnimagen = new JButton("Cargar Imagen");
		btnimagen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser selector = new JFileChooser();
				FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG, PNG & GIF", "jpg", "png",
						"gif");
				selector.setFileFilter(filtroImagen);
				int r = selector.showOpenDialog(null);
				if (r == JFileChooser.APPROVE_OPTION) {
					try {
						File f = selector.getSelectedFile();
						ImageIcon Img = new ImageIcon(selector.getSelectedFile().toURL());
						imgOri = Img;
						java.awt.Image Image = Img.getImage(); // transform it
						java.awt.Image newimg = Image.getScaledInstance(lblImagen.getWidth(), lblImagen.getHeight(),
								Image.SCALE_SMOOTH);
						URL urlImage = selector.getSelectedFile().toURL();
						imagenActual = convetirImagen(urlImage);
						System.out.println("Error en BUSCAR"+imagenActual);
						lblImagen.setIcon(new ImageIcon(newimg));
					} catch (MalformedURLException ex) {
						Logger.getLogger(vAuto.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			}

		});
		btnimagen.setBounds(492, 131, 149, 23);
		contentPane.add(btnimagen);

		lblImagen = new JLabel("");
		lblImagen.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblImagen.setBounds(661, 11, 234, 196);
		contentPane.add(lblImagen);

		JLabel lblNewLabel_2 = new JLabel("Buscar");
		lblNewLabel_2.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 15));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(141, 176, 129, 35);
		lblNewLabel_2.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/lupa.png")), 50, 20));
		contentPane.add(lblNewLabel_2);

		txtBuscar = new JTextField();
		txtBuscar.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				refrescarTabla2(txtBuscar.getText().toString());

			}
		});
		txtBuscar.setBounds(247, 187, 285, 20);
		contentPane.add(txtBuscar);
		txtBuscar.setColumns(10);
		refrescarTabla();
	}

	public void refrescarTabla2(String palabra) {
		DefaultTableModel modeloTabla = new DefaultTableModel() {
			@Override // Redefinimos el método getColumnClass
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Object.class;
				case 1:
					return Object.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				case 4:
					return Object.class;
				case 5:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};
		modeloTabla.addColumn("Idauto");
		modeloTabla.addColumn("Marca");
		modeloTabla.addColumn("Año");
		modeloTabla.addColumn("Tipodeauto");
		modeloTabla.addColumn("Cilindros");
		modeloTabla.addColumn("Imagen");
		while (modeloTabla.getRowCount() > 0) {
			modeloTabla.removeRow(0);
		}
		lista = dao.fecthBuscar(palabra);
		for (Autos u : lista) {
			Object o[] = new Object[6];
			o[0] = u.getIdauto();
			o[1] = u.getMarca();
			o[2] = u.getAño();
			o[3] = u.getTipodeauto();
			o[4] = u.getCilindros();
			o[5] = u.getImagen();
			modeloTabla.addRow(o);
		}
		tblAuto.setModel(modeloTabla);
	}

	public void refrescarTabla() {
		DefaultTableModel modeloTabla = new DefaultTableModel() {
			@Override // Redefinimos el método getColumnClass
			public Class getColumnClass(int column) {
				switch (column) {
				case 0:
					return Object.class;
				case 1:
					return Object.class;
				case 2:
					return Object.class;
				case 3:
					return Object.class;
				case 4:
					return Object.class;
				case 5:
					return ImageIcon.class;
				default:
					return Object.class;
				}
			}
		};
		modeloTabla.addColumn("Idauto");
		modeloTabla.addColumn("Marca");
		modeloTabla.addColumn("Año");
		modeloTabla.addColumn("Tipodeauto");
		modeloTabla.addColumn("Cilindros");
		modeloTabla.addColumn("Imagen");
		while (modeloTabla.getRowCount() > 0) {
			modeloTabla.removeRow(0);
		}
		lista = dao.fetchAutos();
		for (Autos u : lista) {
			Object o[] = new Object[6];
			o[0] = u.getIdauto();
			o[1] = u.getMarca();
			o[2] = u.getAño();
			o[3] = u.getTipodeauto();
			o[4] = u.getCilindros();
			o[5] = base64ToImage(u.getImagen());
			modeloTabla.addRow(o);
		}
		tblAuto.setModel(modeloTabla);
	}

	public ImageIcon base64ToImage(String base64) {
		ImageIcon imagen = null;
		if (!base64.equals("")) {
			
			try {
				byte[] imageByte;
				BASE64Decoder decoder = new BASE64Decoder();
				imageByte = decoder.decodeBuffer(base64);
				ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
				BufferedImage bufferedImage = ImageIO.read(bis);
				imagen = new ImageIcon(bufferedImage);
				bis.close();
			} catch (IOException ex) {
				Logger.getLogger(vAuto.class.getName()).log(Level.SEVERE, null, ex);
			}
		}else {
			imagen=fx.cambiar(new ImageIcon(getClass().getResource("/Img/imagen.jpg")), 50, 20);
		}
		return imagen;
	}

	public String convetirImagen(URL url) {
		String base64 = "";
		try {
			BufferedImage bImage = ImageIO.read(new File(url.getPath()));
			BufferedImage img = resize(bImage, 300, 300);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ImageIO.write(img, "jpg", bos);
			byte[] data = bos.toByteArray();
			base64 = Base64.getEncoder().encodeToString(data);
		} catch (MalformedURLException ex) {
			Logger.getLogger(vAuto.class.getName()).log(Level.SEVERE, null, ex);

		} catch (IOException ex) {
			Logger.getLogger(vAuto.class.getName()).log(Level.SEVERE, null, ex);
		}
		return base64;
	}

	public BufferedImage resize(BufferedImage bufferedImage, int newW, int newH) {
		int w = bufferedImage.getWidth();
		int h = bufferedImage.getHeight();
		BufferedImage bufim = new BufferedImage(newW, newH, bufferedImage.getType());
		Graphics2D g = bufim.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		g.drawImage(bufferedImage, 0, 0, newW, newH, 0, 0, w, h, null);
		g.dispose();
		return bufim;
	}

}
