package br.com.sbcuni.constantes;

import java.io.Serializable;

public class Tela implements Serializable {

	private static final long serialVersionUID = -1230412332202812219L;
	
	public static final String LOGIN = "/principal/login.jsf";
	
	// TOPICO
	public static final String NOVO_TOPICO = "novoTopico";
	public static final String TESTE = "teste";
	public static final String ATUALIZAR_TOPICO = "atualizarTopico";
	public static final String VISUALIZAR_TOPICO = "visualizarTopico";
	public static final String ATUALIZAR_TOPICO_PATH = "/paginas/topico/atualizarTopico.jsf";
	public static final String VISUALIZAR_TOPICO_PATH = "/paginas/topico/visualizarTopico.jsf";
	public static final String MEUS_TOPICOS = "meusTopicos";
	public static final String MEUS_TOPICOS_LOGIN = "/paginas/topico/meusTopicos.jsf";
	public static final String ULTIMOS_TOPICOS_LOGIN = "/paginas/topico/ultimosTopicos.jsf";
	
	// USUARIO
	public static final String DETALHAR_USUARIO = "detalharUsuario";
	public static final String CADASTRAR_USUARIO = "cadastrarUsuario";
	public static final String CONSULTAR_USUARIO = "consultarUsuario";
	public static final String ALTERAR_USUARIO = "alterarUsuario";
	public static final String EXCLUIR_USUARIO = "excluirUsuario";
	public static final String LISTAR_USUARIO = "listarUsuario";
	
	public static final String DETALHAR = "detalhar";
	public static final String LISTA = "lista";
	public static final String ALTERAR = "alterar";
	public static final String CONSULTAR = "consultar";
	public static final String EXCLUIR = "excluir";
	
	public static final String CADASTRAR_USUARIO_MENU = "/paginas/usuario/cadastrarUsuario.jsf";
	public static final String DETALHAR_USUARIO_MENU = "/paginas/usuario/detalharUsuario.jsf";
	public static final String CONSULTAR_ALTERAR_USUARIO_MENU = "/paginas/usuario/consultarAlterarUsuario.jsf";
	public static final String CONSULTAR_USUARIO_MENU = "/paginas/usuario/consultarUsuario.jsf";
	public static final String CONSULTAR_EXCLUIR_USUARIO_MENU = "/paginas/usuario/consultarExcluirUsuario.jsf";
	public static final String PEFIL_PATH = "/paginas/usuario/perfil.jsf";
	public static final String MUDAR_SENHA_PATH = "/paginas/usuario/mudarSenha.jsf";

	// GRUPO DE ESTUDO
	public static final String MEUS_GRUPOS = "meusGrupos";
	public static final String DETALHE_GRUPO_ESTUDO = "detalheGrupoEstudo";
	public static final String DETALHE_GRUPO_ESTUDO_PATH = "/paginas/grupoEstudo/detalheGrupoEstudo.jsf";
	public static final String NOVO_TOPICO_GRUPO_ESTUDO = "novoTopicoGrupoEstudo";
	public static final String INCLUIR_ALUNO_PATH = "/paginas/grupoEstudo/incluirAlunos.jsf";

	//EMAIL
	public static final String CAIXA_ENTRADA = "/paginas/mensagens/email.jsf";
	
	//PESQUISA
	public static final String RESULTADO_PESQUISA_PATH = "/paginas/pesquisa/resultadoPesquisa.jsf";

	//SEGURANÇA
	public static final String SEM_PERMISSAO = "/paginas/seguranca/semPermissao.jsf";
	
	//PAINEL
	public static final String PAINEL_PRINCIPAL = "/paginas/inicio/painelPrincipal.jsf";
	
	public static final String TOPICOS_CATEGORIAS = "/paginas/categorias/topicosCategoria.jsf";
	public static final String MANTER_CATEGORIA = "/paginas/categorias/manterCategorias.jsf";
}
