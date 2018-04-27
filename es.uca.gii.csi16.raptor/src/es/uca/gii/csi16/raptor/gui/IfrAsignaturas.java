package es.uca.gii.csi16.raptor.gui;

import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import es.uca.gii.csi16.raptor.data.Asignatura;
import es.uca.gii.csi16.raptor.data.TipoAsignatura;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComboBox;

public class IfrAsignaturas extends JInternalFrame 
{
	private static final long serialVersionUID = 1L;
	private JTextField txtCreditos;
	private JTextField txtNombre;
	private JTable tabResult;
	private Container pnlParent;

	public IfrAsignaturas(JFrame frame) 
	{ 
		setClosable(true);
		setResizable(true);
		setTitle("Asignaturas");
		setBounds(100, 100, 678, 518);
		pnlParent = frame;
		
		JPanel panel = new JPanel();
		getContentPane().add(panel, BorderLayout.NORTH);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		JLabel lblCreditos = new JLabel("Créditos");
		panel.add(lblCreditos);
		
		txtCreditos = new JTextField();
		panel.add(txtCreditos);
		txtCreditos.setColumns(10);
		
		JLabel lblNombre = new JLabel("Nombre");
		panel.add(lblNombre);
		
		txtNombre = new JTextField();
		panel.add(txtNombre);
		txtNombre.setColumns(10);
		
		JComboBox<TipoAsignatura> cmbTipoAsignatura = new JComboBox<TipoAsignatura>();
		try 
		{
			cmbTipoAsignatura.setModel(new TipoAsignaturaListModel(TipoAsignatura.Select()));
		} catch (Exception e1) 
		{
			JOptionPane.showMessageDialog(null, e1.getMessage());
		} 
		cmbTipoAsignatura.setEditable(true);
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					Integer iCreditos = txtCreditos.getText().isEmpty() ? null 
							: Integer.parseInt(txtCreditos.getText());
					String sNombre = txtNombre.getText().isEmpty() ? null 
							: (txtNombre.getText());
					String stipoAsignatura = cmbTipoAsignatura.getEditor().getItem().toString();

					tabResult.setModel(new AsignaturasTableModel(Asignatura.Select(
						iCreditos, sNombre, stipoAsignatura)));
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, e.getMessage());
				}
			}
		});
		
		JLabel lblTipoAsignatura = new JLabel("Tipo Asignatura");
		panel.add(lblTipoAsignatura);
		
		panel.add(cmbTipoAsignatura);
		panel.add(btnBuscar);
		
		tabResult = new JTable();
		tabResult.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{  
				if (e.getClickCount() == 2) 
				{ 
					int iRow = ((JTable) e.getSource()).getSelectedRow();
					Asignatura asignatura = ((AsignaturasTableModel)tabResult.getModel()).getData(iRow);
					if (asignatura != null)
					{
						IfrAsignatura ifrAsignatura = new IfrAsignatura(asignatura);
						ifrAsignatura.setBounds(10, 27, 300, 300);
						pnlParent.add(ifrAsignatura, 0);
						ifrAsignatura.setVisible(true);
					}
				}
			}
		});
		getContentPane().add(tabResult, BorderLayout.CENTER);
	}
}
