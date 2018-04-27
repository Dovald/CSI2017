package es.uca.gii.csi16.raptor.test;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uca.gii.csi16.raptor.data.Data;

public class DataTest 
{

	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		Data.LoadDriver();
	}

	@Ignore
	@Test
	public void testBoolean2Sql()
	{
		Assert.assertEquals(1, Data.Boolean2Sql(true));
		Assert.assertEquals(0, Data.Boolean2Sql(false));
	}

	@Ignore
	@Test
	public void testString2Sql()
	{
		Assert.assertEquals("hola", Data.String2Sql("hola", false, false));
		Assert.assertEquals("'hola'", Data.String2Sql("hola", true, false));
		Assert.assertEquals("%hola%", Data.String2Sql("hola", false, true));
		Assert.assertEquals("'%hola%'", Data.String2Sql("hola", true, true));
		Assert.assertEquals("O''Connell", Data.String2Sql("O'Connell", false, false));
		Assert.assertEquals("'O''Connell'", Data.String2Sql("O'Connell", true, false));
		Assert.assertEquals("%''Smith ''%", Data.String2Sql("'Smith '", false, true));
		Assert.assertEquals("'''Smith '''", Data.String2Sql("'Smith '", true, false));
		Assert.assertEquals("'%''Smith ''%'", Data.String2Sql("'Smith '", true, true));
	}
	
	@Ignore
	@Test
	public void testTableAccess() throws Exception 
	{
		Connection con = null;
        ResultSet rs = null;
        ResultSet rs2 = null;

        try 
        {        	
            con = Data.Connection();
            rs = con.createStatement().executeQuery("SELECT COUNT(Nombre) iTotal FROM asignatura;");
            rs2 = con.createStatement().executeQuery("SELECT Creditos, Nombre FROM asignatura;");        	
            int iFilas = 0;

            while (rs2.next()) 
            {
                System.out.println(rs2.getString("Creditos") + " " + rs2.getString("Nombre"));
                iFilas++;
            }
            rs.next();
            Assert.assertEquals(iFilas, rs.getInt("iTotal"));
        }
        catch (SQLException ee) { throw ee; }
        finally 
        {
            if (rs != null) rs.close();
            if (con != null) con.close();
        }
	}
}
