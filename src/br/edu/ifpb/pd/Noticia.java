package br.edu.ifpb.pd;

public class Noticia {
	
	private String id;
	private String autor;
	private String titulo;
	private String data;
	private String conteudo;

	public Noticia(String id, String autor, String titulo, String data, String conteudo) {
		this.id = id;
		this.autor = autor;
		this.titulo = titulo;
		this.data = data;
		this.conteudo = conteudo;		
	}
	
	public Noticia() {}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

	@Override
	public String toString() {
		return "Noticia [id=" + id + ", autor=" + autor + ", titulo=" + titulo + ", data=" + data + ", conteudo="
				+ conteudo + "]";
	}	
	
}
