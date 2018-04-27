package es.uca.gii.csi16.raptor.gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmMain {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrmMain window = new FrmMain();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public FrmMain() throws ClassNotFoundException,
			InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
	{
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		initialize();
	}

	private void initialize() 
	{
		frame = new JFrame();
		frame.setTitle("Gestión del personal de Hogwarts");
		frame.setBounds(100, 100, 1000, 1000);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mitNuevo = new JMenu("Nuevo");
		menuBar.add(mitNuevo);
		
		JMenuItem mitNuevoAsignatura = new JMenuItem("Asignatura");
		mitNuevoAsignatura.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				IfrAsignatura ifrAsignatura = new IfrAsignatura(null); 
				ifrAsignatura.setBounds(10, 27, 250, 250); 
				frame.getContentPane().add(ifrAsignatura); 
				ifrAsignatura.setVisible(true);
			}
		});
		mitNuevo.add(mitNuevoAsignatura);
		
		JMenu mitBuscar = new JMenu("Buscar");
		menuBar.add(mitBuscar);
		
		JMenuItem mitBuscarAsignatura = new JMenuItem("Asignatura");
		mitBuscarAsignatura.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				IfrAsignaturas ifrAsignaturas = new IfrAsignaturas(frame);
				ifrAsignaturas.setBounds(12, 28, 700, 700);
				frame.getContentPane().add(ifrAsignaturas, 0); 
				ifrAsignaturas.setVisible(true);
			}
		});
		mitBuscar.add(mitBuscarAsignatura);
		frame.getContentPane().setLayout(null);
	}
}
