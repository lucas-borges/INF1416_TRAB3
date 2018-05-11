/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author Joyce - MeConsulte
 */
public class EventsModel {
    
    public EventsModel(int event_id, int user_id){
        this.datetime = new Timestamp(System.currentTimeMillis());
        this.event_id = event_id;
        this.user_id = user_id;
    }

    /**
     * @return the datetime
     */
    public Timestamp getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the event_id
     */
    public int getEvent_id() {
        return event_id;
    }

    /**
     * @param event_id the event_id to set
     */
    public void setEvent_id(int event_id) {
        this.event_id = event_id;
    }

    /**
     * @return the user_id
     */
    public int getUser_id() {
        return user_id;
    }

    /**
     * @param user_id the user_id to set
     */
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    private Timestamp datetime;
    private int event_id;
    private int user_id;
    
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
    public static final int SENHA_NAO_OK = 3004;
    public static final int PRIMEIRO_ERRO_SENHA = 3005;
    public static final int SEGUNDO_ERRO_SENHA = 3006;
    public static final int TERCEIRO_ERRO_SENHA = 3007;
    public static final int ACESSO_BLOQUEADO_ETAPA_DOIS = 3008;
    public static final int AUTENTICACAO_ETAPA_TRES_INICIADA = 4001;
    public static final int AUTENTICACAO_ETAPA_TRES_ENCERRADA = 3002;
    public static final int SENHA_UNICA_VEZ_OK = 4003;
    public static final int PRIMEIRO_ERRO_SENHA_UNICA_VEZ = 4004;
    public static final int SEGUNDO_ERRO_SENHA_UNICA_VEZ = 4005;
    public static final int TERCEIRO_ERRO_SENHA_UNICA_VEZ = 4006;
    public static final int ACESSO_BLOQUEADO_ETAPA_TRES = 4009;
    public static final int TELA_PRINCIPAL_APRESENTADA = 5001;
    public static final int OPCAO_UM_MENU_PRINCIPAL = 5002;
    public static final int OPCAO_DOIS_MENU_PRINCIPAL = 5003;
    public static final int OPCAO_TRES_MENU_PRINCIPAL = 5004;
    public static final int OPCAO_QUATRO_MENU_PRINCIPAL = 5005;
    public static final int TELA_CADASTRO_APRESENTADA = 6001;
    public static final int BOTAO_CADASTRAR_PRESSIONADO = 6002;
    public static final int CAMINHO_CERTIFICADO_INVALIDO = 6003;
    public static final int CONFIRMACAO_DADOS_ACEITA = 6004;
    public static final int CONFIRMACAO_DADOS_REJEITADA = 6005;
    public static final int CAD_VOLTAR_MENU_PRINCIPAL = 6006;
    public static final int TELA_CARREGAMENTO_CHAVE_PRIVADA = 7001;
    public static final int CAMINHO_CHAVE_PRIVADA_INVALIDO = 7002;
    public static final int FRASE_SECRETA_INVALIDA = 7003;
    public static final int ERRO_VALIDACAO_CHAVE_PRIVADA = 7004;
    public static final int CHAVE_PRIVADA_OK = 7005;
    public static final int CAR_VOLTAR_MENU_PRINCIPAL = 7006;
    public static final int TELA_CONSULTA_ARQUIVOS_SECRETOS = 8001;
    public static final int CONS_VOLTAR_MENU_PRINCIPAL = 8002;
    public static final int CONS_LISTAR = 8003;
    public static final int CAMINHO_PASTA_INVALIDO = 8006;
    public static final int LISTA_ARQUIVOS = 8007;
    public static final int ARQUIVO_SELECIONADO_DECRIPTACAO = 8008;
    public static final int ARQUIVO_DECRIPTADO = 8009;
    public static final int ARQUIVO_VERIFICADO = 8010;
    public static final int FALHA_DECRIPTACAO = 8011;
    public static final int FALHA_VERIFICACAO = 8012;
    public static final int TELA_SAIDA = 9001;
    public static final int SAIR = 9002;
    public static final int SAI_VOLTAR_MENU_PRINCIPAL = 9003;
}
