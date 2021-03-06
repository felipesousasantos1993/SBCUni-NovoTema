package br.com.sbcuni.mensagem.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.joda.time.DateTime;

import br.com.sbcuni.usuario.entity.Usuario;
import br.com.sbcuni.util.Util;

@Entity
@NamedQueries({ @NamedQuery(name = "Mensagem.consultarRecebidas", query = "SELECT m FROM Mensagem m JOIN FETCH m.remetente WHERE  m.destinatario.idUsuario =:idDestinatario AND m.tipo =:idTipo ORDER BY m.dtEnvio DESC"),
		@NamedQuery(name = "Mensagem.consultarEnviadas", query = "SELECT m FROM Mensagem m JOIN FETCH m.remetente WHERE m.tipo =:idTipo AND m.remetente.idUsuario =:idUsuario ORDER BY m.dtEnvio DESC"),
		@NamedQuery(name = "Mensagem.consultarMensagemPorId", query = "SELECT m FROM Mensagem m JOIN FETCH m.remetente WHERE m.id =:idMensagem"),
		@NamedQuery(name = "Mensagem.pesquisa", query = "SELECT m FROM Mensagem m JOIN FETCH m.remetente WHERE lower(m.titulo) like :consulta OR lower(m.mensagem) like :consulta AND (m.destinatario.idUsuario =:idUsuario OR m.remetente.idUsuario =:idUsuario) ORDER BY m.dtEnvio DESC"),
		@NamedQuery(name = "Mensagem.consultarMensagemNotificacao", query = "SELECT m FROM Mensagem m JOIN FETCH m.remetente WHERE m.destinatario.idUsuario =:idUsuario AND (m.dtEnvio between :dtUltimoAcesso and current_timestamp)"),
		@NamedQuery(name = "Mensagem.consultarMensagemPainel", query = "SELECT m FROM Mensagem m JOIN FETCH m.remetente WHERE m.destinatario.idUsuario =:idUsuario") })
public class Mensagem {

	@Id
	@GeneratedValue
	private Long idMensagem;

	@Column(name = "titulo", length = 50, nullable = true)
	private String titulo;

	@Column(name = "mensagem", length = 1024, nullable = false)
	private String mensagem;

	@Column(name = "tipo", nullable = false)
	private Integer tipo;

	@Column(name = "dtEnvio")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dtEnvio;

	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	private Usuario remetente;

	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, targetEntity = Usuario.class)
	private Usuario destinatario;

	@Transient
	private Boolean selecionada;

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Usuario getRemetente() {
		return remetente;
	}

	public void setRemetente(Usuario remetente) {
		this.remetente = remetente;
	}

	public Boolean getSelecionada() {
		return selecionada;
	}

	public void setSelecionada(Boolean selecionada) {
		this.selecionada = selecionada;
	}

	public Integer getTipo() {
		return tipo;
	}

	public void setTipo(Integer tipo) {
		this.tipo = tipo;
	}

	public Date getDtEnvio() {
		return dtEnvio;
	}

	public void setDtEnvio(Date dtEnvio) {
		this.dtEnvio = dtEnvio;
	}

	public String getTempo() {
		return Util.getDiferencaTempo(new DateTime(getDtEnvio()));
	}

	public Usuario getDestinatario() {
		return destinatario;
	}

	public void setDestinatario(Usuario destinatario) {
		this.destinatario = destinatario;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getIdMensagem() {
		return idMensagem;
	}

	public void setIdMensagem(Long idMensagem) {
		this.idMensagem = idMensagem;
	}

}
