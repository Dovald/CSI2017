package es.uca.gii.csi16.raptor.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TipoAsignatura extends Entidad
{
	
	private String _sNombre;
	
	public void setNombre(String sNombre){ _sNombre = sNombre; }
	public String getNombre(){ return _sNombre; }

	public TipoAsignatura(int iId) throws Exception
	{
		super(iId, "Tipoasignatura");
		Connection con = null;
	    
	    try
	    {
	    	con = Data.Connection();
	    	Initialize(iId, con);
		}
		catch (Exception ee) { throw ee; }
		finally 
		{
	        if (con != null) con.close();
		}
	}
	
	public TipoAsignatura(int iId, Connection con) throws Exception
	{
		super(iId, "Tipoasignatura");
		Initialize(iId, con);
	}

	private TipoAsignatura(int iId, String sNombre)
	{
		super(iId, "Tipoasignatura");
		_sNombre = sNombre;
	}
	
	private void Initialize(int iId, Connection con) throws Exception
	{
		ResultSet rs = null;
	    
	    try
	    {
			rs = con.createStatement().executeQuery(
				"SELECT Id, Nombre FROM TipoAsignatura WHERE Id= " + iId + ";");
			rs.next();
			
			_sNombre = rs.getString("Nombre");
		}
		catch (SQLException ee) { throw ee; }
		finally 
		{
	        if (rs != null) rs.close();
		}
	}

	public String toString()
	{
		return "" + _sNombre + "";
	}
	
	public static ArrayList<TipoAsignatura> Select() throws Exception
	{
		Connection con = null;
		ResultSet rs = null;
		ArrayList<TipoAsignatura> aTipoAsignatura = new ArrayList<TipoAsignatura>();
		
		try
	    {
	    	con = Data.Connection();
			rs = con.createStatement().executeQuery("Select Id, Nombre From "
					+ "TipoAsignatura ORDER BY Nombre");
			
			while(rs.next())
				aTipoAsignatura.add(new TipoAsignatura(rs.getInt("Id"), rs.getString("Nombre")));
			
			return aTipoAsignatura;
			
	    }
		catch (SQLException ee) { throw ee; }
		finally 
		{
	        if (con != null) con.close();
		}
	}
	
	public void Update() throws Exception
	{
		super.Update("Update Tipoasignatura "
				+ "set Nombre = " 
				+ Data.String2Sql(_sNombre, true, false)+
				" where Id = " + super.getId());
	}
}

