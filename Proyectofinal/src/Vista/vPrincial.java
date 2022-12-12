package Vista;

import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import Modelo.Autos;
import Modelo.Cliente;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JDesktopPane;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JCheckBoxMenuItem;

public class vPrincial extends JFrame {

	private JPanel contentPane;
	private JDesktopPane desktopPane;
	private JLabel lblNewLabel;
	double ancho = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	double alto = Toolkit.getDefaultToolkit().getScreenSize().getWidth();
	vProducto vProducto = new vProducto();
	vEmpleado vEmpleado = new vEmpleado();
	vProveedor vProveedor = new vProveedor();
	vInventario vInventario = new vInventario();
	vVenta vVenta = new vVenta();
	vDetalleV vDetalleV = new vDetalleV();
	vCliente vCliente = new vCliente();
	vAuto vAuto = new vAuto();
	QUETWARE QUETWARE = new QUETWARE();
	Incar Incar = new Incar();
	pruebaaa pruebaaa = new pruebaaa();
	Desarrolladores Desarrolladores = new Desarrolladores();
	Funciones fx = new Funciones();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					vPrincial frame = new vPrincial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public vPrincial() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(vPrincial.class.getResource("/Img/logodesot.png")));
		setTitle("Incar");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1155, 715);
		this.setLocationRelativeTo(null);
		this.setResizable(false);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnNewMenu = new JMenu("sistema");
		menuBar.add(mnNewMenu);
		mnNewMenu.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/sistema.png")), 20, 20));

		JButton btnNewButton = new JButton("salir ");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "ADIOS");
				System.exit(0);
			}
		});
		mnNewMenu.add(btnNewButton);

		JMenu mnNewMenu_1 = new JMenu("Empleado");
		menuBar.add(mnNewMenu_1);
		mnNewMenu_1.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/emple.png")), 20, 20));

		JMenuItem mntmNewMenuItem = new JMenuItem("Empleados");
		mnNewMenu_1.add(mntmNewMenuItem);
		mntmNewMenuItem.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/empleado.png")));

		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Inventario");
		mnNewMenu_1.add(mntmNewMenuItem_1);
		mntmNewMenuItem_1.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/inventario.png")));

		JMenuItem mntmNewMenuItem_3_1 = new JMenuItem("Venta");
		mntmNewMenuItem_3_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/ven.png")));
		mntmNewMenuItem_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVenta.setVisible(true);
			}
		});
		mntmNewMenuItem_3_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_3_1.setBackground(Color.WHITE);
		mnNewMenu_1.add(mntmNewMenuItem_3_1);

		JMenuItem mntmNewMenuItem_2_1 = new JMenuItem("Proveedor");
		mntmNewMenuItem_2_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Provedor.jpg")));
		mntmNewMenuItem_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProveedor.setVisible(true);
			}
		});
		mntmNewMenuItem_2_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_2_1.setBackground(Color.WHITE);
		mnNewMenu_1.add(mntmNewMenuItem_2_1);

		JMenuItem mntmNewMenuItem_4_1 = new JMenuItem("Detalles Venta");
		mntmNewMenuItem_4_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Detallesv.png")));
		mntmNewMenuItem_4_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vDetalleV.setVisible(true);
			}
		});
		mntmNewMenuItem_4_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_4_1.setBackground(Color.WHITE);
		mnNewMenu_1.add(mntmNewMenuItem_4_1);

		JMenu mnNewMenu_4 = new JMenu("Cliente");
		menuBar.add(mnNewMenu_4);
		mnNewMenu_4.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/cli.png")), 20, 20));

		JMenuItem mntmNewMenuItem_6_1 = new JMenuItem("Cliente");
		mntmNewMenuItem_6_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Cliente.png")));
		mntmNewMenuItem_6_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCliente.setVisible(true);
			}
		});
		mntmNewMenuItem_6_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_6_1.setBackground(Color.WHITE);
		mnNewMenu_4.add(mntmNewMenuItem_6_1);

		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Autos");
		mntmNewMenuItem_7.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_7.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/silvia14.jpg")));
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vAuto.setVisible(true);
			}
		});
		mntmNewMenuItem_7.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mnNewMenu_4.add(mntmNewMenuItem_7);

		JMenuItem mntmNewMenuItem_5_1 = new JMenuItem("Producto");
		mntmNewMenuItem_5_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/producto.png")));
		mntmNewMenuItem_5_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProducto.setVisible(true);
			}
		});
		mntmNewMenuItem_5_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_5_1.setBackground(Color.WHITE);
		mnNewMenu_4.add(mntmNewMenuItem_5_1);

		JMenu mnNewMenu_2 = new JMenu("PDF");
		menuBar.add(mnNewMenu_2);
		mnNewMenu_2.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 20, 20));

		JMenuItem mntmNewMenuItem_12 = new JMenuItem("Auto.pdf");
		mntmNewMenuItem_12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vAuto.pdf();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_12);

		JMenuItem mntmNewMenuItem_11 = new JMenuItem("Cliente.pdf");
		mntmNewMenuItem_11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCliente.pdf();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_11);

		JMenuItem mntmNewMenuItem_13 = new JMenuItem("Detalles Venta.pdf");
		mntmNewMenuItem_13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vDetalleV.pdf();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_13);

		JMenuItem mntmNewMenuItem_14 = new JMenuItem("Empleados.pdf");
		mntmNewMenuItem_14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vEmpleado.pdf();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_14);

		JMenuItem mntmNewMenuItem_15 = new JMenuItem("Inventario.pdf");
		mntmNewMenuItem_15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vInventario.pdf();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_15);

		JMenuItem mntmNewMenuItem_16 = new JMenuItem("Producto.pdf");
		mntmNewMenuItem_16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProducto.pdf();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_16);

		JMenuItem mntmNewMenuItem_17 = new JMenuItem("Proveedor.pdf");
		mntmNewMenuItem_17.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProveedor.pdf();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_17);

		JMenuItem mntmNewMenuItem_18 = new JMenuItem("Venta.pdf");
		mntmNewMenuItem_18.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVenta.pdf();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_18);

		JMenu mnNewMenu_3 = new JMenu("Acerca de");
		menuBar.add(mnNewMenu_3);
		mnNewMenu_3.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pre.jpg")), 20, 20));

		JMenuItem mntmNewMenuItem_8 = new JMenuItem("QUETWARE");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				QUETWARE.setVisible(true);
			}
		});
		mntmNewMenuItem_8.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/empresalogo.png")), 20, 20));
		mntmNewMenuItem_8.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 15));
		mnNewMenu_3.add(mntmNewMenuItem_8);

		JMenuItem mntmNewMenuItem_9 = new JMenuItem("Incar");
		mntmNewMenuItem_9.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Incar.setVisible(true);
			}
		});
		mntmNewMenuItem_9.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/logodesot.png")), 20, 20));
		mntmNewMenuItem_9.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
		mnNewMenu_3.add(mntmNewMenuItem_9);

		JMenuItem mntmNewMenuItem_10 = new JMenuItem("Desarrolladores");
		mntmNewMenuItem_10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Desarrolladores.setVisible(true);
			}
		});
		mntmNewMenuItem_10.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pregunta.png")), 20, 20));
		mntmNewMenuItem_10.setFont(new Font("Segoe UI", Font.BOLD, 15));
		mnNewMenu_3.add(mntmNewMenuItem_10);

		JMenuItem mntmNewMenuItem_19 = new JMenuItem("Manual Usuario");
		mntmNewMenuItem_19.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 20, 20));
		mntmNewMenuItem_19.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					FileOutputStream archivo;
					File temp = new File(System.getProperty("java.io.tmpdir") + "Manualu.pdf");
					InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/PDF/mu.pdf");
					FileOutputStream flujoSalida = new FileOutputStream(temp);
					archivo = new FileOutputStream(temp);
					  FileWriter fw = new FileWriter(temp);
			            byte[] buffer = new byte[1024*512];
			            int control; 
			            while ((control = flujoEntrada.read(buffer)) != -1){
			                flujoSalida.write(buffer, 0, control);
			            }
					Desktop.getDesktop().open(temp);
				} catch (IOException ex) {
					System.out.println(ex);
				}
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_19);

		JMenuItem mntmNewMenuItem_20 = new JMenuItem("Manual tecnico");
		mntmNewMenuItem_20.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/pdf.png")), 20, 20));
		mntmNewMenuItem_20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					FileOutputStream archivo;
					File temp = new File(System.getProperty("java.io.tmpdir") + "Manualt.pdf");
					InputStream flujoEntrada = (InputStream) this.getClass().getResourceAsStream("/PDF/mt.pdf");
					FileOutputStream flujoSalida = new FileOutputStream(temp);
					archivo = new FileOutputStream(temp);
					  FileWriter fw = new FileWriter(temp);
			            byte[] buffer = new byte[1024*512];
			            int control; 
			            while ((control = flujoEntrada.read(buffer)) != -1){
			                flujoSalida.write(buffer, 0, control);
			            }
					Desktop.getDesktop().open(temp);
				} catch (IOException ex) {
					System.out.println(ex);
				}
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_20);
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vInventario.setVisible(true);
			}
		});
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vEmpleado.setVisible(true);
				// pruebaaa.setVisible(true);
			}
		});
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 0));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		desktopPane = new JDesktopPane();
		desktopPane.setBackground(new Color(192, 192, 192));
		desktopPane.setBounds(208, 110, 921, 533);
		contentPane.add(desktopPane);
		vProducto.setResizable(true);

		vAuto.setLocation(0, 0);
		vCliente.setLocation(0, 0);
		vDetalleV.setLocation(0, 0);
		vEmpleado.setLocation(0, 0);
		vInventario.setLocation(0, 0);
		vProducto.setLocation(0, 0);
		vProveedor.setLocation(0, 0);
		vVenta.setLocation(0, 0);
		QUETWARE.setLocation(0, 0);
		Incar.setLocation(0, 0);
		Desarrolladores.setLocation(0, 0);
		pruebaaa.setLocation(0, 0);

		vAuto.setBounds(0, 0, 921, 533);
		vCliente.setBounds(0, 0, 921, 533);
		vDetalleV.setBounds(0, 0, 921, 533);
		vEmpleado.setBounds(0, 0, 921, 533);
		vInventario.setBounds(0, 0, 921, 533);
		vProveedor.setBounds(0, 0, 921, 533);
		vProducto.setBounds(0, 0, 921, 533);
		vVenta.setBounds(0, 0, 921, 533);
		QUETWARE.setBounds(0, 0, 921, 533);
		Incar.setBounds(0, 0, 921, 533);
		Desarrolladores.setBounds(0, 0, 921, 533);
		pruebaaa.setBounds(0, 0, 921, 533);

		desktopPane.add(vAuto);
		desktopPane.add(vCliente);
		desktopPane.add(vDetalleV);
		desktopPane.add(vEmpleado);
		desktopPane.add(vInventario);
		desktopPane.add(vProveedor);
		desktopPane.add(vProducto);
		desktopPane.add(vVenta);
		desktopPane.add(QUETWARE);
		desktopPane.add(Incar);
		desktopPane.add(Desarrolladores);
		desktopPane.add(pruebaaa);

		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(21, 11, 147, 106);
		lblNewLabel.setIcon(fx.cambiar(new ImageIcon(getClass().getResource("/Img/logodesot.png")), 147, 106));
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Incar");
		lblNewLabel_1.setForeground(new Color(128, 255, 255));
		lblNewLabel_1.setFont(new Font("Baskerville Old Face", Font.BOLD, 25));
		lblNewLabel_1.setBounds(220, 15, 192, 36);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("La inovacion distinge a los lideres de los seguidores");
		lblNewLabel_2.setFont(new Font("NSimSun", Font.BOLD, 15));
		lblNewLabel_2.setForeground(new Color(255, 255, 255));
		lblNewLabel_2.setBounds(259, 60, 454, 26);
		contentPane.add(lblNewLabel_2);

		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Venta");
		mntmNewMenuItem_3.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_3.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_3.setBounds(10, 175, 188, 25);
		contentPane.add(mntmNewMenuItem_3);
		mntmNewMenuItem_3.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/vent.png")));

		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Cliente");
		mntmNewMenuItem_6.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_6.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_6.setBounds(10, 138, 188, 26);
		contentPane.add(mntmNewMenuItem_6);
		mntmNewMenuItem_6.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Cliente.png")));

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Producto");
		mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_5.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_5.setBounds(10, 248, 188, 26);
		contentPane.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/producto.png")));

		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Detalles Venta");
		mntmNewMenuItem_4.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_4.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_4.setBounds(10, 211, 188, 26);
		contentPane.add(mntmNewMenuItem_4);
		mntmNewMenuItem_4.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Detallesv.png")));

		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Proveedor");
		mntmNewMenuItem_2.setBounds(21, 612, 153, 31);
		contentPane.add(mntmNewMenuItem_2);
		mntmNewMenuItem_2.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_2.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_2.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/Provedor.jpg")));

		JMenuItem mntmNewMenuItem_7_1 = new JMenuItem("Autos");
		mntmNewMenuItem_7_1.setBackground(new Color(255, 255, 255));
		mntmNewMenuItem_7_1.setIcon(new ImageIcon(vPrincial.class.getResource("/Img/silvia14.jpg")));
		mntmNewMenuItem_7_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vAuto.setVisible(true);
			}
		});
		mntmNewMenuItem_7_1.setFont(new Font("Segoe UI", Font.BOLD, 20));
		mntmNewMenuItem_7_1.setBounds(10, 285, 188, 31);
		contentPane.add(mntmNewMenuItem_7_1);
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProveedor.setVisible(true);
			}
		});
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vDetalleV.setVisible(true);
			}
		});
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vProducto.setVisible(true);
			}
		});
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vCliente.setVisible(true);
			}
		});
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				vVenta.setVisible(true);
			}
		});
	}
}
