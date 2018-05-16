/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;
import java.util.Date;

/**
 *
 * @author Joyce - MeConsulte
 */
public class EventModel {
    private Date datetime;
    private int id;
    private String user;
    private String file;
    private String message;
    
    public EventModel(int id, String message, String user, String file, Date datetime){
        this.datetime = datetime;
        this.id = id;
        this.user = user;
        this.file = file;
        this.message = message;
        if(user != null) this.message = this.message.replace("<login_name>", user);
        if(file != null) this.message = this.message.replace("<arq_name>", file);
    }

    public Date getDatetime() {
        return datetime;
    }

    public int getId() {
        return id;
    }

    public String getUser() {
        return user;
    }
    
    public String getFile() {
        return file;
    }
    
    public String getMessage() {
        return message;
    }

    
    public static final int SISTEMA_INICIADO = 1001;
    public static final int SISTEMA_ENCERRADO = 1002;
    public static final int AUTENTICACAO_ETAPA_UM_INICIADA = 2001;
    public static final int AUTENTICACAO_ETAPA_UM_ENCERRADA = 2002;
    public static final int LOGIN_ACESSO_LIBERADO = 2003;
    public static final int LOGIN_ACESSO_BLOQUEADO = 2004;
    public static final int LOGIN_NAO_IDENTIFICADO = 2005;
    public static final int AUTENTICACAO_ETAPA_DOIS_INICIADA = 3001;
    public static final int AUTENTICACAO_ETAPA_DOIS_ENCERRADA = 3002;
    public static final int SENHA_OK = 3003;
    public static final int PRIMEIRO_ERRO_SENHA = 3004;
    public static final int SEGUNDO_ERRO_SENHA = 3005;
    public static final int TERCEIRO_ERRO_SENHA = 3006;
    public static final int ACESSO_BLOQUEADO_ETAPA_DOIS = 3007;
    public static final int AUTENTICACAO_ETAPA_TRES_INICIADA = 4001;
    public static final int AUTENTICACAO_ETAPA_TRES_ENCERRADA = 4002;
    public static final int CHAVE_PRIV_OK = 4003;
    public static final int CHAVE_PRIV_CAMINHO_INVALIDO = 4004;
    public static final int CHAVE_PRIV_FRASE_SECRETA_INV = 4005;
    public static final int CHAVE_PRIV_DIG_SIG_INV = 4006;
    public static final int ACESSO_BLOQUEADO_ETAPA_TRES = 4007;
    public static final int TELA_PRINCIPAL_APRESENTADA = 5001;
    public static final int OPCAO_UM_MENU_PRINCIPAL = 5002;
    public static final int OPCAO_DOIS_MENU_PRINCIPAL = 5003;
    public static final int OPCAO_TRES_MENU_PRINCIPAL = 5004;
    public static final int OPCAO_QUATRO_MENU_PRINCIPAL = 5005;
    public static final int CAD_TELA_CADASTRO_APRESENTADA = 6001;
    public static final int CAD_BOTAO_CADASTRAR_PRESSIONADO = 6002;
    public static final int CAD_SENHA_INVALIDA = 6003;
    public static final int CAD_CAMINHO_CERTIFICADO_INVALIDO = 6004;
    public static final int CAD_CONFIRMACAO_DADOS_ACEITA = 6005;
    public static final int CAD_CONFIRMACAO_DADOS_REJEITADA = 6006;
    public static final int CAD_VOLTAR_MENU_PRINCIPAL = 6007;
    public static final int ALT_TELA_ALTERACAO = 7001;
    public static final int ALT_SENHA_PESSOAL_INVALIDA = 7002;
    public static final int ALT_CAMINHO_CERTIFICADO_INVALIDAO = 7003;
    public static final int ALT_CONFIRMACAO_DADOS_ACEITA = 7004;
    public static final int ALT_CONFIRMACAO_DADOS_REJEITADA = 7005;
    public static final int ALT_VOLTAR_MENU_PRINCIPAL = 7006;
    public static final int TELA_CONSULTA_ARQUIVOS_SECRETOS = 8001;
    public static final int CONS_VOLTAR_MENU_PRINCIPAL = 8002;
    public static final int CONS_LISTAR = 8003;
    public static final int CAMINHO_PASTA_INVALIDO = 8004;
    public static final int LISTA_ARQUIVOS = 8005;
    public static final int ARQUIVO_SELECIONADO_DECRIPTACAO = 8006;
    public static final int ACESSO_PERMITIDO = 8007;
    public static final int ACESSO_NEGADO = 8008;
    public static final int ARQUIVO_DECRIPTADO = 8009;
    public static final int ARQUIVO_VERIFICADO = 8010;
    public static final int FALHA_DECRIPTACAO = 8011;
    public static final int FALHA_VERIFICACAO = 8012;
    public static final int TELA_SAIDA = 9001;
    public static final int SAIDA_NAO_LIBERADA = 9002;
    public static final int SAIR = 9003;
    public static final int SAI_VOLTAR_MENU_PRINCIPAL = 9004;
}
