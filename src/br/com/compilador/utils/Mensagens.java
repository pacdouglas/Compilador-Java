/*
 * Nome Alunos:
 * Douglas Martins
 * José Ricardo Zanardo Junior
 * Rafael Madeira Medeiros Anjos
 * Rhamah Nemezio
 * 
 */
package br.com.compilador.utils;

public class Mensagens {
	
	public static final String MSG_FALTA_PROGRAM = "É preciso iniciar o código com PROGRAM";
	public static final String MSG_FALTA_NOME_PROGRAM = "É necessário definir um nome para o programa";
	public static final String MSG_FALTA_PONTOVIRGULA_NOME_PROGRAM = "É necessário inserir um ; na declaração do nome do programa.";
	public static final String MSG_FALTOU_ENDPROGR_INSERIU_TOKEN = "É necessário inserir END_PROGR e não :";
	public static final String MSG_FALTOU_ENDPROGR = "É necessário encerrar o código com END_PROGR";
	public static final String MSG_FALTOU_PONTOVIRGULA_ENDPROGR = "É necessário inserir um ; após o END_PROGR";
	public static final String SEM_END_FIM_BLOCO = "É necessário inserir um END ao fim de um bloco";
	public static final String MSG_LEXEMA_INESPERADO = "Lexema não esperado: ";
	public static final String MSG_IF_SEM_THEN = "É necessário informar um THEN ao fim de um IF";
	public static final String MSG_FALTOU_PONTOVIRGULA = "É necessário inserir um ;";
	public static final String MSG_VARIAVEL_REDECLARADA = "Variável redeclarada: ";
	public static final String MSG_DECLARE_SEM_VARIAVEL = "Variável esperada após DECLARE";
	public static final String MSG_VARIAVEL_SEM_TIPO = "Variável declarada sem tipo";
	public static final String MSG_PONTOVIRGULA_TIPO_VARIAVEL = "É necessário informar um ; após o tipo da variável";
	public static final String MSG_VARIAVEL_SEM_DECLARE = "É necessário utilizar DECLARE para declarar uma variável";
	public static final String MSG_CONDICAO_SEM_IF = "É necessáro um IF para iniciar uma condição";
	public static final String MSG_VARIAVEL_NAO_ENCONTRADA = "É necessário declarar uma variável";
	public static final String MSG_VARIAVEL_SEM_ATRIBUICAO = "É necessário atribuir um valor para a variável";
	public static final String MSG_OPERADOR_RELACAO = "É necessário informar um operador de relação";
	public static final String MSG_EXPR_PARENTESES_ABRE = "É necessário iniciar a expressão com (";
	public static final String MSG_EXPR_PARENTESES_FECHA = "É necessário encerrar a expressão com )";
	public static final String MSG_EXPR_REPETICAO_FOR_FALTANDO = "É necessário informar um FOR para o início da expressão";
	public static final String MSG_EXPR_REPETICAO_FOR_SEM_VARIAVEL_APOS_FOR = "É necessário informar uma variável após o FOR";
	public static final String MSG_EXPR_REPETICAO_FOR_TO = "É necessário informar um TO para a expressão";
	public static final String MSG_EXPR_REPETICAO_WHILE_FALTANDO = "É necessário informar um WHILE para o inicio da expressão";
	public static final String MSG_LEXICO_ERRO_NAO_TRATADO = "Erro no analisador léxico";
	public static final String MSG_LEXICO_CARACTERE_INVALIDO = "Caractere inválido";
	public static final String MSG_LEXICO_COMENTARIO_FECHAMENTO_INCORRETO = "É necessário fechar o comentário com ]";
	public static final String MSG_LEXICO_ASPAS_FECHAMENTO_FALTANDO = "É necessário informar as aspas ' para fechamento";
	public static final String MSG_LEXICO_OPERADOR_RELACIONAL_INVALIDO = "Operador relacional inválido";
	public static final String MSG_LEXICO_CARACTERE_INVALIDO_APOS_PONTO = "Caractere inválido após o ponto";
	public static final String MSG_LEXICO_CARACTERE_INVALIDO_APOS_E = "Caractere inválido após  E";
	public static final String MSG_INICIO_BLOCO_INVALIDO = "Impossível iniciar bloco com: ";
	public static final String MSG_FALTOU_TERM = "É necessário inserir um TERM";
	public static final String MSG_END_NAO_ENCONTRADO = "É necessário inserir um END";
	public static final String MSG_BEGIN_CMD_NAO_ENCONTRADO = "É necessário inserir um BEGIN ou algum simbolo de [CMD]";
	public static final String MSG_VARIAVEL_NAO_DECLARADA = "A variável não foi declarada: ";
	public static final String MSG_CMD_NAO_ENCONTRADO = "É necessário informar algum simbolo de [CMD]";
	public static final String MSG_EXP_FALTANDO = "É necessário informar algum simbolo de [EXP]";
	public static final String MSG_EXPL_FALTANDO = "É necessário informar algum simbolo de [EXPL]";
	public static final String MSG_GENFLW_FALTANDO = "É necessário informar algum simbolo de [GENFLW]";
	public static final String MSG_TERMON1_FALTANDO = "É necessário informar algum simbolo de [TERMON1]";
	public static final String MSG_EXPN1_FALTANDO = "É necessário informar algum simbolo de [EXPN1]";
	public static final String MSG_GENFLW2_FALTANDO = "É necessário informar algum simbolo de [GENFLW2]";
	public static final String MSG_EXPN_FALTANDO = "É necessário informar algum simbolo de [EXPN]";
	public static final String MSG_TERMON_FALTANDO = "É necessário informar algum simbolo de [TERMON]";
	public static final String MSG_VALN_FALTANDO = "É necessário informar algum simbolo de [VALN]";
	public static final String MSG_REP_FALTANDO = "É necessário informar algum simbolo de [REP]";
	public static final String MSG_OPERADOR_ATRIBUICAO = "É necessário informar um operador de atribuição";
	public static final String MSG_FIM_INESPERADO_ARQUIVO = "Final inesperado do arquivo!";
	public static final String MSG_LEXICO_FALTA_FECHAR_COMENTARIO = "É necessário encerrar o comentário com um ]";
	public static final String MSG_LEXICO_COMENTARIO_MAL_FECHADO = "O comentário foi encerrado de forma incorreta";
}
