// mask
$(document).ready(function() {
	marcaDagua();
});
function marcaDagua() {
	$("#matricula").attr("placeholder", "Matrícula").blur();
	$("#matricula2").attr("placeholder", "Digite sua matrícula").blur();
	$("#senha").attr("placeholder", "Senha").blur();
	$("#pesquisar").attr("placeholder", "Pesquisar...").blur();
	$("#procurar").attr("placeholder", "Procurar").blur();
	$("#consultaAluno").attr("placeholder", "Digite o nome ou matrícula para procurar os alunos").blur();
	$(".comentar").attr("placeholder", "Escreva seu comentário");

	$(".nome").attr("placeholder", "Nome");
	$(".cpf").attr("placeholder", "CPF");
	$(".email").attr("placeholder", "E-mail");
	$(".telfixo").attr("placeholder", "Telefone");
	$(".telcelular").attr("placeholder", "Celular");
	$(".cidade").attr("placeholder", "Cidade");
	$(".estado").attr("placeholder", "Selecione seu estado");
	$(".sobre").attr("placeholder", "Escreva sobre você");
	
	$(".novaSenha").attr("placeholder", "Nova senha");
	$(".confirmeSenha").attr("placeholder", "Confirme a senha");
	$(".senhaAntiga").attr("placeholder", "Senha antiga");
}