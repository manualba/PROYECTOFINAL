package Proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;

public class Actualizacion extends JFrame {

	private JPanel contentPane;
	private JTable tabla1;


	/**
	 * Create the frame.
	 */
	public Actualizacion() {
		
		Conexion c = new Conexion();
		c.Conectar();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(650, 300, 874, 511);
		contentPane = new JPanel();
		contentPane.setBackground(Color.GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnVolver = new JButton("VOLVER");
		btnVolver.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				dispose();
				Inicio i = new Inicio();
				i.setVisible(true);

				
			}
		});
		btnVolver.setBounds(10, 397, 89, 23);
		panel.add(btnVolver);
		
		JTextArea ACTUALIZAR = new JTextArea();
		ACTUALIZAR.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 17));
		ACTUALIZAR.setEditable(false);
		ACTUALIZAR.setBackground(Color.LIGHT_GRAY);
		ACTUALIZAR.setForeground(new Color(0, 128, 0));
		ACTUALIZAR.setText("ACTUALIZACION DE TROPAS");
		ACTUALIZAR.setBounds(249, 11, 298, 28);
		panel.add(ACTUALIZAR);
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		tabla1 = new JTable(modelo);
		tabla1.setEnabled(false);
		tabla1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla1.setBackground(Color.WHITE);
		tabla1.setBounds(10, 69, 438, 305);
		panel.add(tabla1);
		
		JTextArea txtrNombre = new JTextArea();
		txtrNombre.setForeground(Color.RED);
		txtrNombre.setBackground(Color.WHITE);
		txtrNombre.setEditable(false);
		txtrNombre.setText("Nombre");
		txtrNombre.setBounds(118, 46, 60, 23);
		panel.add(txtrNombre);
		
		JTextArea txtrDni = new JTextArea();
		txtrDni.setForeground(Color.RED);
		txtrDni.setBackground(Color.WHITE);
		txtrDni.setEditable(false);
		txtrDni.setText("DNI");
		txtrDni.setBounds(10, 46, 52, 23);
		panel.add(txtrDni);
		
		JTextArea TxtRango = new JTextArea();
		TxtRango.setForeground(Color.RED);
		TxtRango.setBackground(Color.WHITE);
		TxtRango.setEditable(false);
		TxtRango.setText("Rango");
		TxtRango.setBounds(230, 46, 52, 23);
		panel.add(TxtRango);
		
		JTextArea txtrCuerpo = new JTextArea();
		txtrCuerpo.setForeground(Color.RED);
		txtrCuerpo.setBackground(Color.WHITE);
		txtrCuerpo.setEditable(false);
		txtrCuerpo.setText("Cuerpo");
		txtrCuerpo.setBounds(339, 46, 52, 23);
		panel.add(txtrCuerpo);
		
		JTextArea txtrIntroduceElDni = new JTextArea();
		txtrIntroduceElDni.setFont(new Font("Monospaced", Font.PLAIN, 12));
		txtrIntroduceElDni.setBackground(Color.LIGHT_GRAY);
		txtrIntroduceElDni.setEditable(false);
		txtrIntroduceElDni.setText("Introduce el DNI para actualizar al soldado:");
		txtrIntroduceElDni.setBounds(505, 107, 311, 23);
		panel.add(txtrIntroduceElDni);
		
		JTextArea dni = new JTextArea();
		dni.setBounds(604, 129, 89, 23);
		panel.add(dni);
		
		JTextArea txtrRango_1 = new JTextArea();
		txtrRango_1.setFont(new Font("Monospaced", Font.PLAIN, 17));
		txtrRango_1.setEditable(false);
		txtrRango_1.setBackground(Color.LIGHT_GRAY);
		txtrRango_1.setText("Rango:");
		txtrRango_1.setBounds(505, 202, 64, 23);
		panel.add(txtrRango_1);
		
		JTextArea txtrCuerpo_1 = new JTextArea();
		txtrCuerpo_1.setText("Cuerpo:");
		txtrCuerpo_1.setFont(new Font("Monospaced", Font.PLAIN, 17));
		txtrCuerpo_1.setEditable(false);
		txtrCuerpo_1.setBackground(Color.LIGHT_GRAY);
		txtrCuerpo_1.setBounds(505, 236, 74, 23);
		panel.add(txtrCuerpo_1);
		
		JTextArea rango = new JTextArea();
		rango.setBounds(604, 206, 89, 23);
		panel.add(rango);
		
		JTextArea cuerpo = new JTextArea();
		cuerpo.setBounds(604, 240, 89, 23);
		panel.add(cuerpo);
		
		JButton btnNewButton = new JButton("ACTUALIZAR");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (dni.getText().length() == 8 && (cuerpo.getText().equals("Terrestre") || cuerpo.getText().equals("Naval") || cuerpo.getText().equals("Aire"))) {
					
					dispose();
					
					
					JOptionPane.showMessageDialog(null, "DATOS ACTUALIZADOS!!! ", "",JOptionPane.INFORMATION_MESSAGE);
					
					try {
	
						//System.out.println("UPDATE listado_soldados SET Rango = \"" + rango.getText() + "\", Cuerpo =\"" + cuerpo.getText() + "\" WHERE listado_soldados.DNI = " + dni.getText());
						c.EjecutarUpdate("UPDATE listado_soldados SET Rango = \"" + rango.getText() + "\", Cuerpo =\"" + cuerpo.getText() + "\" WHERE listado_soldados.DNI = " + dni.getText());
						
						
						
						
					} catch (SQLException e4) {
						
						e4.printStackTrace();
					}
					
					
					Actualizacion a = new Actualizacion();
					a.setVisible(true);

				} else {
					
					
					JOptionPane.showMessageDialog(null, "LA LIASTE!! ", "VUELVE A INTENTARLO",JOptionPane.WARNING_MESSAGE);
					
				}
				
			}
		});
		
		btnNewButton.setBounds(704, 329, 112, 45);
		panel.add(btnNewButton);
		
		modelo.addColumn("DNI");
		modelo.addColumn("Nombre");
		modelo.addColumn("Rango");
		modelo.addColumn("Cuerpo");
		
		
		ResultSet rs = Conexion.EjecutarSentencia("SELECT DNI,Nombre,Rango,Cuerpo FROM listado_soldados");

		try {
			while(rs.next()) {
				String[] filas = new String[4];
				for(int i = 0 ; i < 4 ; i++) {
					filas[i] = rs.getString(i+1);
				}
				modelo.addRow(filas);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
