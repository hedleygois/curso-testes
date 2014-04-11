package edu.mocks.gerenciador;

import java.util.Date;
import java.util.List;

public class GerenciadorCicloVida {
	
	private GerenciadorDocumentos gerenciadorDocumentos;
	private GerenciadorEntradas gerenciadorEntradas;
	private GerenciadorSaida gerenciadorSaida;
	
	public GerenciadorCicloVida(GerenciadorDocumentos gerenciadorDocumentos, 
			GerenciadorEntradas gerenciadorEntradas, GerenciadorSaida gerenciadorSaida) {
		this.gerenciadorDocumentos = gerenciadorDocumentos;
		this.gerenciadorEntradas = gerenciadorEntradas;
		this.gerenciadorSaida = gerenciadorSaida;
	}

	public Boolean excluir(String nomeDoDetento) {
		String documentos = gerenciadorDocumentos.buscarDocumentosDo(nomeDoDetento);
		List<Date> saidas = gerenciadorSaida.buscarSaidas().Do(nomeDoDetento);
		List<Date> entradas = gerenciadorEntradas.buscar(nomeDoDetento);
		if(verificarDocumentos(documentos) && verificarSaidas(saidas) && verificarEntradas(entradas)) {
			return true;
		}
		return false;
	}

	private boolean verificarDocumentos(String documentos) {
		return documentos != null && documentos.isEmpty();
	}
	
	private boolean verificarSaidas(List<Date> saidas) {
		//O teste não garante que o código de produção é bonito. Só garante que funciona
		return saidas != null && (saidas.size() == 0);
	}
	
	private boolean verificarEntradas(List<Date> entradas) {
		return entradas != null && entradas.isEmpty();
	}

}
