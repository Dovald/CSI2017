package es.uca.gii.csi16.raptor.data;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public abstract class Entidad 
{
	private int _iId;
	private boolean _bIsDeleted = false;
	private String _sTabla;
	
	public int getId()
	{
		return _iId;
	}
	
	public boolean getIsDeleted()
	{
		return _bIsDeleted;
	}

	public Entidad(int iId, String sTabla)
	{
		_iId = iId;
		_sTabla = sTabla;
	}
	
	protected void Update(String sQuery) throws Exception
	{
		if (_bIsDeleted)
			throw new Exception("No existe");
		else
		{
			Connection con = null;
			
			try
		    {
		    	con = Data.Connection();
				con.createStatement().executeUpdate(sQuery);
		    }
			catch (SQLException ee) { throw ee; }
			finally 
			{
		        if (con != null) con.close();
			}
		}
	}
	
	public abstract void Update() throws Exception;
	
	public void Delete() throws Exception
	{	
		if (_bIsDeleted)
			throw new Exception("No existe el dato");
		else
		{
			Connection con = null;
			
			try
		    {
		    	con = Data.Connection();
				con.createStatement().executeUpdate("Delete From " + _sTabla + " where Id = " + _iId);
				_bIsDeleted = true;
		    }
			catch (SQLException ee) { throw ee; }
			finally 
			{
		        if (con != null) con.close();
			}
		}
	}
	
	protected static String Where(String[] asCampo, int[] aiTipo, Object[] aoValor)
	{
		String sResult = "";
		
		for(int i = 0; i < asCampo.length; i++)
		{
			if(aoValor[i] != null)
			{
				if(aiTipo[i] == Types.VARCHAR)
					sResult += asCampo[i] + " like " 
							+ Data.String2Sql(aoValor[i].toString(), true, true) + " AND ";
				if(aiTipo[i] == Types.INTEGER)
					sResult += asCampo[i]+ " = " + aoValor[i] + " AND ";
			}
		}	
		
		if(sResult.length() > 0)
			sResult = " WHERE " + sResult.substring(0, sResult.length()-5);
		
		return sResult;
	}
}