package Proyecto;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import javax.swing.JTabbedPane;
import javax.swing.JTable;

public class Bajas extends JFrame {

	private JPanel contentPane;
	private JTable tabla1;


	/**
	 * Create the frame.
	 */
	public Bajas() {
		
		Conexion c = new Conexion();
		c.Conectar();
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(650, 300, 783, 496);
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
		
		JTextArea BAJAS = new JTextArea();
		BAJAS.setFont(new Font("Copperplate Gothic Light", Font.BOLD, 21));
		BAJAS.setEditable(false);
		BAJAS.setBackground(Color.LIGHT_GRAY);
		BAJAS.setForeground(new Color(0, 128, 0));
		BAJAS.setText("BAJAS");
		BAJAS.setBounds(329, 11, 108, 28);
		panel.add(BAJAS);
		
		
		DefaultTableModel modelo = new DefaultTableModel();
		tabla1 = new JTable(modelo);
		tabla1.setEnabled(false);
		tabla1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tabla1.setBackground(Color.WHITE);
		tabla1.setBounds(10, 69, 373, 305);
		panel.add(tabla1);
		
		JTextArea txtrNombre = new JTextArea();
		txtrNombre.setForeground(Color.RED);
		txtrNombre.setBackground(Color.WHITE);
		txtrNombre.setEditable(false);
		txtrNombre.setText("Nombre");
		txtrNombre.setBounds(10, 46, 188, 23);
		panel.add(txtrNombre);
		
		JTextArea txtrDni = new JTextArea();
		txtrDni.setForeground(Color.RED);
		txtrDni.setBackground(Color.WHITE);
		txtrDni.setEditable(false);
		txtrDni.setText("DNI");
		txtrDni.setBounds(197, 46, 186, 23);
		panel.add(txtrDni);
		
		JTextArea dni = new JTextArea();
		dni.setBounds(488, 194, 152, 23);
		panel.add(dni);
		
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				if (dni.getText().length() == 8) {
					
					dispose();
					
					int respuesta = JOptionPane.showConfirmDialog(null, "De verdad quieres dar de baja a este soldado? ", "Dando de BAJA...",JOptionPane.YES_NO_OPTION);		
					
					 if (respuesta==JOptionPane.NO_OPTION) {
						 dispose();
						 Bajas b = new Bajas();
							b.setVisible(true);
						 
					 }else if(respuesta==JOptionPane.YES_OPTION) {
					 
					try {
	
						//System.out.println("DELETE FROM listado_soldados WHERE DNI = " + dni.getText());
						JOptionPane.showMessageDialog(null, "Soldado dado de BAJA!! ", "Eliminando datos...",JOptionPane.OK_OPTION);
						c.EjecutarUpdate("DELETE FROM listado_soldados WHERE DNI = " + dni.getText());
						
						
						
					} catch (SQLException e3) {
						
						e3.printStackTrace();
					}
					
					
					dispose();
					Bajas b = new Bajas();
					b.setVisible(true);

				} else {
					
					
					JOptionPane.showMessageDialog(null, "El DNI no existe!!! ", "VUELVE A INTENTARLO",JOptionPane.WARNING_MESSAGE);
					
					}
				}
				
				
			}
			
		});
		btnEliminar.setBounds(624, 242, 89, 54);
		panel.add(btnEliminar);
		
		JTextArea txtrIntroduceElDni = new JTextArea();
		txtrIntroduceElDni.setForeground(Color.RED);
		txtrIntroduceElDni.setBackground(Color.LIGHT_GRAY);
		txtrIntroduceElDni.setEditable(false);
		txtrIntroduceElDni.setText("Introduce el DNI de la persona ");
		txtrIntroduceElDni.setBounds(444, 139, 269, 23);
		panel.add(txtrIntroduceElDni);
		
		JTextArea txtrALaQue = new JTextArea();
		txtrALaQue.setForeground(Color.RED);
		txtrALaQue.setBackground(Color.LIGHT_GRAY);
		txtrALaQue.setEditable(false);
		txtrALaQue.setText("a la que quieres dar de baja:");
		txtrALaQue.setBounds(478, 160, 269, 23);
		panel.add(txtrALaQue);
	
		modelo.addColumn("Nombre");
		modelo.addColumn("DNI");
		
		
		
		ResultSet rs = Conexion.EjecutarSentencia("SELECT Nombre,DNI FROM listado_soldados");

		try {
			while(rs.next()) {
				String[] filas = new String[2];
				for(int i = 0 ; i < 2 ; i++) {
					filas[i] = rs.getString(i+1);
				}
				modelo.addRow(filas);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
