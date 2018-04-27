package es.uca.gii.csi16.raptor.gui;

import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import es.uca.gii.csi16.raptor.data.Asignatura;
import es.uca.gii.csi16.raptor.data.TipoAsignatura;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;

public class IfrAsignatura extends JInternalFrame 
{
	private static final long serialVersionUID = 1L;
	private JTextField txtCreditos;
	private JTextField txtNombre;
	private Asignatura _asignatura = null;

	public IfrAsignatura(Asignatura asignatura) 
	{
		_asignatura = asignatura;
		setClosable(true);
		setResizable(true);
		setTitle("Asignatura");
		setBounds(100, 100, 1000, 300);
		getContentPane().setLayout(null);
		
		JLabel lblCreditos = new JLabel("Créditos");
		lblCreditos.setBounds(43, 49, 46, 14);
		getContentPane().add(lblCreditos);
		
		txtCreditos = new JTextField();
		txtCreditos.setBounds(96, 46, 86, 20);
		getContentPane().add(txtCreditos);
		txtCreditos.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(43, 88, 46, 14);
		getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(96, 85, 86, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JComboBox<TipoAsignatura> cmbTipoAsignatura = new JComboBox<TipoAsignatura>();
		try 
		{
			cmbTipoAsignatura.setModel(new TipoAsignaturaListModel(TipoAsignatura.Select()));
		} catch (Exception e) 
		{
			JOptionPane.showMessageDialog(null, e.getMessage());
		} 
		cmbTipoAsignatura.setBounds(66, 121, 116, 20);
		getContentPane().add(cmbTipoAsignatura);
		
		if (_asignatura != null) 
		{						 
			String sCreditos = "" + _asignatura.getCreditos();
			txtCreditos.setText(sCreditos);
			txtNombre.setText(_asignatura.getNombre());
			cmbTipoAsignatura.getModel().setSelectedItem(_asignatura.getTipoAsignatura());
		}
		
		JButton butGuardar = new JButton("Guardar");
		butGuardar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try
				{	
					if((TipoAsignatura)cmbTipoAsignatura.getModel().getSelectedItem() == null)
						throw new Exception ("Selecciona el Tipo de Asignatura");
					if(_asignatura == null)
					{
						_asignatura = Asignatura.Create
								(Integer.parseInt(txtCreditos.getText()), txtNombre.getText(),
										(TipoAsignatura)cmbTipoAsignatura
										.getModel().getSelectedItem());
						JOptionPane.showMessageDialog(null, "Dato guardado con éxito");
					}
					else
					{
						_asignatura.setCreditos(Integer.parseInt(txtCreditos.getText()));
						_asignatura.setNombre(txtNombre.getText());
						_asignatura.setTipoAsignatura((TipoAsignatura)cmbTipoAsignatura
								.getModel().getSelectedItem());
						_asignatura.Update();
						JOptionPane.showMessageDialog(null, "Dato guardado con éxito");
					}
				}catch(NumberFormatException e) 
				{
					JOptionPane.showMessageDialog(null, "Introduce un número en Créditos");
				}
				catch(Exception e) 
				{
					JOptionPane.showMessageDialog(null, e.getMessage());
				} 		
			}
		});
		butGuardar.setBounds(43, 152, 89, 23);
		getContentPane().add(butGuardar);
	}
}
