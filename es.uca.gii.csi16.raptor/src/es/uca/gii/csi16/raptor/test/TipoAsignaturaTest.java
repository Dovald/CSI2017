package es.uca.gii.csi16.raptor.test;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uca.gii.csi16.raptor.data.Data;
import es.uca.gii.csi16.raptor.data.TipoAsignatura;

public class TipoAsignaturaTest 
{
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{
		Data.LoadDriver();
	}
	
	@Ignore
	@Test
	public void testConstructor() throws Exception 
	{
		TipoAsignatura tipoasignatura = new TipoAsignatura(1);
		Assert.assertEquals("Obligatoria", tipoasignatura.getNombre());
	}
	
	@Ignore
	@Test
	public void testSelect() throws Exception
	{
		Assert.assertEquals(2, TipoAsignatura.Select().size());		
	}
}

