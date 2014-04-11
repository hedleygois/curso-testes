package edu.mocks.gerenciador;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class GerenciadorCicloVidaTest {
	
	@Mock private GerenciadorDocumentos gerenciadorDocumentos;
	@Mock private GerenciadorEntradas gerenciadorEntradas;
	@Mock private GerenciadorSaida gerenciadorSaidas;

	private GerenciadorCicloVida gerenciador;
	
	@Before public void
	setUp() {
		MockitoAnnotations.initMocks(this);
		gerenciador = new GerenciadorCicloVida(gerenciadorDocumentos, gerenciadorEntradas, gerenciadorSaidas);
	}
	
	@Test public void
	naoDeveExcluidCasoExistamDocumentosVinculadosAoDetento() {
		when(gerenciadorDocumentos.buscarDocumentosDo(anyString())).thenReturn("existeDocumento");
		when(gerenciadorSaidas.buscarSaidas()).thenCallRealMethod();
		
		assertFalse(gerenciador.excluir("detento"));
	}
	
	@Test public void
	nao_deve_excluir_caso_existam_saidas_do_preso() {
		List<Date> datasSaidas = new ArrayList<Date>();
		datasSaidas.add(new Date());
		List<Date> datasEntradas = Collections.emptyList();
		when(gerenciadorDocumentos.buscarDocumentosDo(anyString())).thenReturn("");
		when(gerenciadorEntradas.buscar(anyString())).thenReturn(datasEntradas);
		when(gerenciadorSaidas.buscarSaidas()).thenCallRealMethod();
		when(gerenciadorSaidas.Do(anyString())).thenReturn(datasSaidas);
		
		assertFalse(gerenciador.excluir("detento"));
	}
	
	
	@Test 
	public void naoDeveExcluirCasoExistamEntradasDoDetento() {
		List<Date> datasSaidas = Collections.emptyList();
		List<Date> datasEntradas = new ArrayList<>();
		datasEntradas.add(new Date());
		when(gerenciadorDocumentos.buscarDocumentosDo(anyString())).thenReturn("");
		when(gerenciadorSaidas.buscarSaidas()).thenCallRealMethod();
		when(gerenciadorSaidas.Do(anyString())).thenReturn(datasSaidas);
		when(gerenciadorEntradas.buscar(anyString())).thenReturn(datasEntradas);
		
		assertFalse(gerenciador.excluir("detento"));
	}
	
	@Test public void
	devePoderExcluirQuandoNaoDocumentoNemEntradaNemSaida() {
		List<Date> datasVazias = Collections.emptyList();
		when(gerenciadorDocumentos.buscarDocumentosDo(anyString())).thenReturn("");
		when(gerenciadorSaidas.buscarSaidas()).thenCallRealMethod();
		when(gerenciadorSaidas.Do(anyString())).thenReturn(datasVazias);
		when(gerenciadorEntradas.buscar(anyString())).thenReturn(datasVazias);
		
		assertTrue(gerenciador.excluir("detento"));
	}
	
}
