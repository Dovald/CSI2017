package es.uca.gii.csi16.raptor.test;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import es.uca.gii.csi16.raptor.data.Asignatura;
import es.uca.gii.csi16.raptor.data.Data;
import es.uca.gii.csi16.raptor.data.TipoAsignatura;

public class AsignaturaTest 
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
		Asignatura asignatura = new Asignatura(1);
		Assert.assertEquals(6, asignatura.getCreditos());
		Assert.assertEquals("Pociones", asignatura.getNombre());
		Assert.assertEquals(1, asignatura.getId());		
	}
	
	@Ignore
	@Test
	public void testCreate() throws Exception
	{
		Asignatura asignatura = Asignatura.Create(6, "Transformación", new TipoAsignatura(1));
		Assert.assertEquals(6, asignatura.getCreditos());
		Assert.assertEquals("Transformación", asignatura.getNombre());		 
	}
	
	@Ignore
	@Test
	public void testSelect() throws Exception
	{
		Assert.assertEquals(2, Asignatura.Select(6, null, "Obligatoria").size());
		
	}
	
	@Ignore
	@Test
	public void testUpdate() throws Exception
	{
		ArrayList<Asignatura> aAsignatura = new ArrayList<Asignatura>(Asignatura.Select(null, "%Vuelo%", "%Obligatoria%"));
		aAsignatura.get(0).setNombre("Vuelo");
		aAsignatura.get(0).Update();
		Assert.assertEquals(1, Asignatura.Select(null, "Vuelo", "Obligatoria").size());		
	}
	
	@Ignore
	@Test
	public void testDelete() throws Exception
	{
		Asignatura asignatura = new Asignatura(1);
		asignatura.Delete();
		Assert.assertEquals(true, asignatura.getIsDeleted());
	}

}
