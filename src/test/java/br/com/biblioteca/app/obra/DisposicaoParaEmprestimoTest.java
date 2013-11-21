package br.com.biblioteca.app.obra;

import static org.junit.Assert.*;

import org.junit.Test;

public class DisposicaoParaEmprestimoTest {

	@Test
	public void testGetDescricao() {
		assertEquals("Disponível", DisposicaoParaEmprestimo.DISPONIVEL.getDescricao());
		assertEquals("Indisponível", DisposicaoParaEmprestimo.INDISPONIVEL.getDescricao());
	}

	@Test
	public void testFromString() {
		assertEquals(DisposicaoParaEmprestimo.DISPONIVEL, DisposicaoParaEmprestimo.fromString("Disponível"));
		assertEquals(DisposicaoParaEmprestimo.INDISPONIVEL, DisposicaoParaEmprestimo.fromString("Indisponível"));
	}

}
