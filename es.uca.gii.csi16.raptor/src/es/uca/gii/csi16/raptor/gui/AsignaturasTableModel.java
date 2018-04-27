package es.uca.gii.csi16.raptor.gui;

import java.util.ArrayList;

import javax.swing.table.AbstractTableModel;

import es.uca.gii.csi16.raptor.data.Asignatura;

public class AsignaturasTableModel extends AbstractTableModel 
{
	private static final long serialVersionUID = 1L;
	private ArrayList<Asignatura> _aData;

	@Override
	public int getColumnCount() 
	{
		return 3;
	}

	@Override
	public int getRowCount() 
	{
		return _aData.size();
	}

	@Override
	public Object getValueAt(int iRow, int iCol) 
	{
		switch(iCol)
		{
			case 0:
				return _aData.get(iRow).getCreditos();
			case 1:
				return _aData.get(iRow).getNombre();
			case 2:
				return _aData.get(iRow).getTipoAsignatura();
		}
		return new IllegalStateException("Error al obtener valores de la tabla.");
	}

	public Asignatura getData(int iRow)
	{
		return _aData.get(iRow);
	}

	AsignaturasTableModel(ArrayList<Asignatura> aData)
	{
		_aData = aData;
	}
}
