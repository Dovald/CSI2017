package es.uca.gii.csi16.raptor.gui;

import java.util.List;

import javax.swing.AbstractListModel;
import javax.swing.ComboBoxModel;

import es.uca.gii.csi16.raptor.data.TipoAsignatura;

public class TipoAsignaturaListModel extends AbstractListModel<TipoAsignatura> 
implements ComboBoxModel<TipoAsignatura> 
{

	private static final long serialVersionUID = 1L;
	private List<TipoAsignatura> _aData;
	private Object _selection = null;

	@Override
	public TipoAsignatura getElementAt(int iIndex) 
	{
		return _aData.get(iIndex);
	}

	@Override
	public int getSize() 
	{		
		return _aData.size();
	}

	@Override
	public Object getSelectedItem() 
	{
		return _selection;
	}

	@Override
	public void setSelectedItem(Object o) 
	{
		if (o instanceof TipoAsignatura)	
	        for (int i = 0; i < _aData.size(); i++)
	        {
	        	TipoAsignatura item = (TipoAsignatura)this.getElementAt(i);
	            if (item.getId() == ((TipoAsignatura)o).getId())
	                _selection = item;
	        }
		else
			_selection = o;
	}

	TipoAsignaturaListModel(List<TipoAsignatura> aData)
	{
		_aData = aData;	
	}
}
