package rs.ac.bg.fon.nprog.mvnbiblioteka2021.biblioteka.interfejs;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rs.ac.bg.fon.nprog.mvnbiblioteka2021.biblioteka.Knjiga;

public abstract class BibliotekaInterfaceTest {

	protected BibliotekaInterface biblioteka;
	
	@Test
	void testDodajKnjigu() {
		Knjiga k=new Knjiga();
		k.setNaslov("Knjiga 1");
		
		biblioteka.dodajKnjigu(k);
		
		assertEquals(1, biblioteka.vratiSveKnjige().size());
		assertEquals(k, biblioteka.vratiSveKnjige().get(0));
	}
	@Test
	void testDodajKnjiguNull() {
		assertThrows(java.lang.NullPointerException.class, () -> biblioteka.dodajKnjigu(null));
	}
	@Test
	void testDodajKnjiguDuplikat() {
		Knjiga k=new Knjiga();
		k.setIsbn("12345");
		Knjiga k2=new Knjiga();
		k2.setIsbn("12345");
		
		biblioteka.dodajKnjigu(k);
		
		RuntimeException e=assertThrows(java.lang.RuntimeException.class,() -> biblioteka.dodajKnjigu(k2));
		assertEquals("Knjiga vec postoji",e.getMessage());
	}
	
	@Test
	void testObrisiKnjigu() {
		Knjiga k=new Knjiga();
		k.setIsbn("12345");
		
		biblioteka.dodajKnjigu(k);
		
		biblioteka.obrisiKnjigu(k);
		
		assertEquals(0, biblioteka.vratiSveKnjige().size());
	}
	@Test
	void testObrisiKnjiguNull() {
		
		
		assertThrows(java.lang.RuntimeException.class, ()->biblioteka.obrisiKnjigu(null));
	}
	
	@Test
	void testObrisiKnjiguNePostoji() {
		Knjiga k=new Knjiga();
		k.setIsbn("12345");
		biblioteka.dodajKnjigu(k);
		Knjiga k2=new Knjiga();
		k2.setIsbn("4566787");
		
		
		assertThrows(java.lang.RuntimeException.class, ()->biblioteka.obrisiKnjigu(k2));
	}
	@Test
	void testVratiSveKnjige() {
		Knjiga k=new Knjiga();
		k.setIsbn("12345");
		biblioteka.dodajKnjigu(k);
		
		List<Knjiga> knjige = biblioteka.vratiSveKnjige();
		
		assertEquals(1, knjige.size());
		assertEquals(k, knjige.get(0));
		
	}

	@Test
	void testPronadjiKnjigu() {
		Knjiga k1=new Knjiga();
		k1.setNaslov("Knjiga o dzungli");
		k1.setIsbn("1234");
		
		Knjiga k2=new Knjiga();
		k2.setNaslov("Dzungla zivota");
		k2.setIsbn("5678");
		
		Knjiga k3=new Knjiga();
		k3.setNaslov("Evgenije Onjegin");
		k3.setIsbn("91011");
	
		biblioteka.dodajKnjigu(k1);
		biblioteka.dodajKnjigu(k2);
		biblioteka.dodajKnjigu(k3);
		
		List<Knjiga> rezultat=biblioteka.pronadjiKnjigu(null, null, "zung", null);
		
		assertEquals(2,	rezultat.size());
		assertTrue(rezultat.contains(k1));
		assertTrue(rezultat.contains(k2));
		
		
	}
	@Test
	void testPronadjiKnjiguNull() {
		assertThrows(java.lang.RuntimeException.class, ()->biblioteka.pronadjiKnjigu(null,null,null,null));
	}
}
