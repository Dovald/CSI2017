package es.uca.gii.csi16.raptor.data;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

public class Asignatura extends Entidad
{
	private	int _iCreditos;
	private String _sNombre;
	private TipoAsignatura _tipoAsignatura;
	
	public void setCreditos(int iCreditos){ _iCreditos = iCreditos; }
	public int getCreditos(){ return _iCreditos; }
	
	public void setNombre(String sNombre){ _sNombre = sNombre; }
	public String getNombre(){ return _sNombre; }
	
	public void setTipoAsignatura(TipoAsignatura tipoAsignatura ){ _tipoAsignatura = tipoAsignatura; }
	public TipoAsignatura getTipoAsignatura(){ return _tipoAsignatura; }

	public Asignatura(int iId) throws Exception
	{
		super(iId, "Asignatura");
		Connection con = null;
	    ResultSet rs = null;
	    
	    try
	    {
	    	con = Data.Connection();
			rs = con.createStatement().executeQuery(
				"SELECT Id, Id_TipoAsignatura, Creditos, Nombre FROM asignatura WHERE Id=" +
				iId + ";");
			rs.next();
			
			_sNombre = rs.getString("Nombre");
			_iCreditos = rs.getInt("Creditos");
			_tipoAsignatura = new TipoAsignatura(rs.getInt("Id_TipoAsignatura"), con);
		}
		catch (SQLException ee) { throw ee; }
		finally 
		{
	        if (rs != null) rs.close();
	        if (con != null) con.close();
		}
	}
	
	private Asignatura(int iId, int iCreditos, String sNombre, TipoAsignatura tipoasignatura)
	{
		super(iId, "Asignatura");
		_iCreditos = iCreditos;
		_sNombre = sNombre; 
		_tipoAsignatura = tipoasignatura;
	}

	public String toString()
	{
		return super.toString()+":"+_sNombre+":"+
				String.valueOf(_iCreditos)+":"+String.valueOf(super.getId())+":"
				+String.valueOf(_tipoAsignatura);
	}

	public static Asignatura Create(int iCreditos, String sNombre, TipoAsignatura tipoAsignatura)
			throws Exception
	{
		Connection con = null;
		
	    try
	    {
	    	con = Data.Connection();
			con.createStatement().executeUpdate("INSERT INTO asignatura "
					+ "(Creditos, Nombre, Id_TipoAsignatura)"
					+ "Values("+iCreditos+","+Data.String2Sql(sNombre, true, false)
					+","+tipoAsignatura.getId()+");");
			return new Asignatura(Data.LastId(con));
	    }
		catch (SQLException ee) { throw ee; }
		finally 
		{
	        if (con != null) con.close();
		}
	}
	
	public void Update() throws Exception
	{
		super.Update("Update Asignatura "
				+ "set Creditos =" +_iCreditos+ ", Nombre = " 
				+ Data.String2Sql(_sNombre, true, false) +
				", Id_TipoAsignatura = " +_tipoAsignatura.getId() +
				" where Id = " + super.getId());
	}
	
	public static ArrayList<Asignatura> Select(Integer iCreditos, String sNombre, String sTipoAsignatura)
			throws Exception
	{
		Connection con = null;
		ResultSet rs = null;
		ArrayList<Asignatura> aAsignatura = new ArrayList<Asignatura>();
		
		try
	    {
	    	con = Data.Connection();
	    	String sSelect = "Select * From Asignatura ";
	    	
	    	if(sTipoAsignatura != null)
	    		sSelect += " INNER JOIN TipoAsignatura ON Asignatura.Id_TipoAsignatura = "
					+ "TipoAsignatura.Id";
			rs = con.createStatement().executeQuery(sSelect + Entidad.Where(
					new String[] { "Asignatura.Nombre", "Asignatura.Creditos", "TipoAsignatura.Nombre"}, 
					new int [] { Types.VARCHAR, Types.INTEGER, Types.VARCHAR },
					new Object[] { sNombre, iCreditos, sTipoAsignatura }));
			
			while(rs.next())
				aAsignatura.add(new Asignatura(rs.getInt("Id"), rs.getInt("Creditos"), 
						rs.getString("Nombre"), new TipoAsignatura(rs.getInt("Id_tipoasignatura"), con)));
			
			return aAsignatura;			
	    }
		catch (SQLException ee) { throw ee; }
		finally 
		{
	        if (con != null) con.close();
		}
	}
}
