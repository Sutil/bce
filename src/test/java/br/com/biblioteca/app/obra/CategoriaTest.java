package br.com.biblioteca.app.obra;

import static org.junit.Assert.*;

import org.junit.Test;

public class CategoriaTest {

	@Test
	public void testGetCodigo() {
		assertEquals(Categoria.LIVRO.getCodigo(), 1);
		assertEquals(Categoria.PERIODICO.getCodigo(), 2);
		assertEquals(Categoria.ENCICLOPEDIA.getCodigo(), 3);
		assertEquals(Categoria.CD.getCodigo(), 4);
		assertEquals(Categoria.DVD.getCodigo(), 5);
		assertEquals(Categoria.BLUE_RAY.getCodigo(), 6);
		assertEquals(Categoria.FITA_CASSETE.getCodigo(), 7);
		assertEquals(Categoria.DICIONARIO.getCodigo(), 8);
		assertEquals(Categoria.MAPA.getCodigo(), 9);
	}

	@Test
	public void testGetDescricao() {
		assertEquals(Categoria.LIVRO.getDescricao(), "Livro");
	}

	@Test
	public void testGetMapvalues() {
		assertEquals(Categoria.getMapvalues().size(), 9);
	}

	@Test
	public void testFromCodigo() {
		assertEquals(Categoria.fromCodigo(9), Categoria.MAPA);
	}

}
