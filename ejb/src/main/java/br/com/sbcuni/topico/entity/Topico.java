package br.com.sbcuni.topico.entity;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import br.com.sbcuni.avaliacao.entity.Avaliacao;
import br.com.sbcuni.categoria.entity.Categoria;
import br.com.sbcuni.comentario.entity.Comentario;
import br.com.sbcuni.grupoEstudo.GrupoEstudo;
import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.Util;

@Entity
@NamedQueries({
	@NamedQuery(name = "Topico.buscarTopicosPorUsuario" , query = "SELECT DISTINCT(t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge JOIN FETCH t.categorias c JOIN FETCH t.usuario u WHERE t.usuario.idUsuario = :idUsuario ORDER BY t.dtUltimaAtualizacao DESC, t.dtCriacao DESC"),
	@NamedQuery(name = "Topico.buscarTodosTopicos" , query = "SELECT DISTINCT(t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge JOIN FETCH t.categorias c JOIN FETCH t.usuario u ORDER BY t.dtUltimaAtualizacao DESC, t.dtCriacao DESC"),
	@NamedQuery(name = "Topico.buscarTopicosTituloDescricao" , query = "SELECT DISTINCT(t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge LEFT JOIN FETCH t.categorias c LEFT JOIN FETCH t.usuario u WHERE lower(t.titulo) like :titulo OR lower(t.descricao) like :descricao ORDER BY t.dtUltimaAtualizacao DESC, t.dtCriacao DESC"),
	@NamedQuery(name = "Topico.buscarTopicosGrupo", query = "SELECT DISTINCT(t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge LEFT JOIN FETCH t.categorias JOIN FETCH t.usuario u WHERE t.grupoEstudo.idGrupoEstudo =:idGrupoEstudo"),
	@NamedQuery(name = "Topico.buscarTopicosPainel", query = "SELECT DISTINCT(t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge LEFT JOIN FETCH t.categorias JOIN FETCH t.usuario u WHERE t.grupoEstudo is empty ORDER BY t.dtUltimaAtualizacao DESC, t.dtCriacao DESC"),
	@NamedQuery(name = "Topico.buscarTopicosPainelGrupo", query = "SELECT DISTINCT(t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge LEFT JOIN FETCH t.categorias JOIN FETCH t.usuario u WHERE t.grupoEstudo is empty OR t.grupoEstudo.idGrupoEstudo IN (:listaGrupos) ORDER BY t.dtUltimaAtualizacao DESC, t.dtCriacao DESC"),
	@NamedQuery(name = "Topico.buscarTopicosPorId", query = "SELECT DISTINCT(t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge LEFT JOIN FETCH t.categorias JOIN FETCH t.usuario u WHERE t.idTopico =:idTopico ORDER BY t.dtUltimaAtualizacao DESC, t.dtCriacao DESC"),
	@NamedQuery(name = "Topico.buscarTopicosMaisVisualizados", query = "SELECT DISTINCT (t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge LEFT JOIN FETCH t.categorias JOIN FETCH t.usuario u WHERE t.grupoEstudo is empty ORDER BY t.nuVisualizacoes DESC"),
	@NamedQuery(name = "Topico.buscarTopicosMaisVisualizadosGrupo", query = "SELECT DISTINCT (t) FROM Topico t LEFT JOIN FETCH t.grupoEstudo ge LEFT JOIN FETCH t.categorias JOIN FETCH t.usuario u WHERE t.grupoEstudo is empty OR t.grupoEstudo.idGrupoEstudo IN (:listaGrupos) ORDER BY t.nuVisualizacoes DESC"),
	@NamedQuery(name = "Topico.buscarNuTopicosUsuario", query = "SELECT COUNT(t) FROM Topico t WHERE t.usuario.idUsuario =:idUsuario"),
	@NamedQuery(name = "Topico.buscarTopicoNotificao", query = "SELECT DISTINCT(t) FROM Topico t JOIN FETCH t.usuario LEFT JOIN FETCH t.categorias LEFT JOIN FETCH t.grupoEstudo ORDER BY t.dtUltimaAtualizacao DESC"),
})
public class Topico implements Serializable {

	private static final long serialVersionUID = 4096848931993938523L;

	@Id
	@GeneratedValue
	@Column(name = "idTopico", length = 5, nullable = false)
	private Long idTopico;
	
	@Column(name = "titulo", length = 128, nullable = false)
	private String titulo;
	
	@Column(name = "descricao", length = 90000, nullable = false)
	private String descricao;
	
	@Column(name = "dtCriacao", length = 13, nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtCriacao;
	
	@Column(name = "dtUltimaAtualizacao", length = 13, nullable = true)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtUltimaAtualizacao;
	
	@Column(name = "nuVisualizacoes", nullable = true)
	private Integer nuVisualizacoes;
	
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY,  targetEntity = Categoria.class)
	private List<Categoria> categorias;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	@JoinColumn(name = "usuario", referencedColumnName = "idUsuario")
	private Usuario usuario;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topico", targetEntity = Comentario.class)
	private List<Comentario> comentarios;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "topicos", targetEntity = Avaliacao.class)
	private List<Avaliacao> avaliacaos;
	
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = GrupoEstudo.class, optional = true)
	private GrupoEstudo grupoEstudo;

	@Transient
	private BigInteger nuAvaliacaoPositivas;
	
	@Transient
	private BigInteger nuAvaliacaoNegativas;
	
	@Transient
	private Avaliacao avaliacaoUsuario;

	@Transient
	private Boolean marcado;
	
	@Transient
	private BigInteger nuComentariosAux;
	
	public Long getIdTopico() {
		return idTopico;
	}

	public void setIdTopico(Long idTopico) {
		this.idTopico = idTopico;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getDtUltimaAtualizacao() {
		return dtUltimaAtualizacao;
	}

	public void setDtUltimaAtualizacao(Date dtUltimaAtualizacao) {
		this.dtUltimaAtualizacao = dtUltimaAtualizacao;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Avaliacao> getAvaliacaos() {
		return avaliacaos;
	}

	public void setAvaliacaos(List<Avaliacao> avaliacaos) {
		this.avaliacaos = avaliacaos;
	}

	public Avaliacao getAvaliacaoUsuario() {
		return avaliacaoUsuario;
	}

	public void setAvaliacaoUsuario(Avaliacao avaliacaoUsuario) {
		this.avaliacaoUsuario = avaliacaoUsuario;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	public BigInteger getNuAvaliacaoPositivas() {
		return nuAvaliacaoPositivas;
	}

	public void setNuAvaliacaoPositivas(BigInteger nuAvaliacaoPositivas) {
		this.nuAvaliacaoPositivas = nuAvaliacaoPositivas;
	}

	public BigInteger getNuAvaliacaoNegativas() {
		return nuAvaliacaoNegativas;
	}

	public void setNuAvaliacaoNegativas(BigInteger nuAvaliacaoNegativas) {
		this.nuAvaliacaoNegativas = nuAvaliacaoNegativas;
	}

	public GrupoEstudo getGrupoEstudo() {
		return grupoEstudo;
	}

	public void setGrupoEstudo(GrupoEstudo grupoEstudo) {
		this.grupoEstudo = grupoEstudo;
	}

	public Date getDtCriacao() {
		return dtCriacao;
	}

	public void setDtCriacao(Date dtCriacao) {
		this.dtCriacao = dtCriacao;
	}
	
	public Integer getNuComentarios() {
		if (null != comentarios) {
			return comentarios.size();
		}
		return null;
	}

	public Integer getNuVisualizacoes() {
		return nuVisualizacoes;
	}

	public void setNuVisualizacoes(Integer nuVisualizacoes) {
		this.nuVisualizacoes = nuVisualizacoes;
	}
	
	public String getTempoTopico() {
		return Util.getDiferencaTempo(new DateTime(getDtUltimaAtualizacao()));
	}
	
	public String getTempoTopicoCriacao() {
		return Util.getDiferencaTempo(new DateTime(getDtCriacao()));
	}

	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
	}

	public BigInteger getNuComentariosAux() {
		return nuComentariosAux;
	}

	public void setNuComentariosAux(BigInteger nuComentariosAux) {
		this.nuComentariosAux = nuComentariosAux;
	}

}