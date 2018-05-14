insert into MESSAGES values(1001, 'Sistema iniciado.');
insert into MESSAGES values(1002, 'Sistema encerrado.');
insert into MESSAGES values(2001, 'Autenticação etapa 1 iniciada.');
insert into MESSAGES values(2002, 'Autenticação etapa 1 encerrada.');
insert into MESSAGES values(2003, 'Login name <login_name> identificado com acesso liberado.');
insert into MESSAGES values(2004, 'Login name <login_name> identificado com acesso bloqueado.');
insert into MESSAGES values(2005, 'Login name <login_name> não identificado.');
insert into MESSAGES values(3001, 'Autenticação etapa 2 iniciada para <login_name>.');
insert into MESSAGES values(3002, 'Autenticação etapa 2 encerrada para <login_name>.');
insert into MESSAGES values(3003, 'Senha pessoal verificada positivamente para <login_name>.');
insert into MESSAGES values(3004, 'Primeiro erro da senha pessoal contabilizado para <login_name>.');
insert into MESSAGES values(3005, 'Segundo erro da senha pessoal contabilizado para <login_name>.');
insert into MESSAGES values(3006, 'Terceiro erro da senha pessoal contabilizado para <login_name>.');
insert into MESSAGES values(3007, 'Acesso do usuario <login_name> bloqueado pela autenticação etapa 2.');
insert into MESSAGES values(4001, 'Autenticação etapa 3 iniciada para <login_name>.');
insert into MESSAGES values(4002, 'Autenticação etapa 3 encerrada para <login_name>.');
insert into MESSAGES values(4003, 'Chave privada verificada positivamente para <login_name>.');
insert into MESSAGES values(4004, 'Chave privada verificada negativamente para <login_name> (caminho inválido).');
insert into MESSAGES values(4005, 'Chave privada verificada negativamente para <login_name> (frase secreta inválida).');
insert into MESSAGES values(4006, 'Chave privada verificada negativamente para <login_name> (assinatura digital inválida).');
insert into MESSAGES values(4007, 'Acesso do usuario <login_name> bloqueado pela autenticação etapa 3.');
insert into MESSAGES values(5001, 'Tela principal apresentada para <login_name>.');
insert into MESSAGES values(5002, 'Opção 1 do menu principal selecionada por <login_name>.');
insert into MESSAGES values(5003, 'Opção 2 do menu principal selecionada por <login_name>.');
insert into MESSAGES values(5004, 'Opção 3 do menu principal selecionada por <login_name>.');
insert into MESSAGES values(5005, 'Opção 4 do menu principal selecionada por <login_name>.');
insert into MESSAGES values(6001, 'Tela de cadastro apresentada para <login_name>.');
insert into MESSAGES values(6002, 'Botão cadastrar pressionado por <login_name>.');
insert into MESSAGES values(6003, 'Senha pessoal inválida fornecida por <login_name>.');
insert into MESSAGES values(6004, 'Caminho do certificado digital inválido fornecido por <login_name>.');
insert into MESSAGES values(6005, 'Confirmação de dados aceita por <login_name>.');
insert into MESSAGES values(6006, 'Confirmação de dados rejeitada por <login_name>.');
insert into MESSAGES values(6007, 'Botão voltar de cadastro para o menu principal pressionado por <login_name>.');
insert into MESSAGES values(7001, 'Tela de alteração da senha pessoal, certificado e TAN List apresentada para <login_name>.');
insert into MESSAGES values(7002, 'Senha pessoal inválida fornecida por <login_name>.');
insert into MESSAGES values(7003, 'Caminho do certificado digital inválido fornecido por <login_name>.');
insert into MESSAGES values(7004, 'Confirmação de dados aceita por <login_name>.');
insert into MESSAGES values(7005, 'Confirmação de dados rejeitada por <login_name>.');
insert into MESSAGES values(7006, 'Botão voltar de carregamento para o menu principal pressionado por <login_name>.');
insert into MESSAGES values(8001, 'Tela de consulta de arquivos secretos apresentada para <login_name>.');
insert into MESSAGES values(8002, 'Botão voltar de consulta para o menu principal pressionado por <login_name>.');
insert into MESSAGES values(8003, 'Botão Listar de consulta pressionado por <login_name>.');
insert into MESSAGES values(8004, 'Caminho de pasta inválido fornecido por <login_name>.');
insert into MESSAGES values(8005, 'Lista de arquivos apresentada para <login_name>.');
insert into MESSAGES values(8006, 'Arquivo <arq_name> selecionado por <login_name> para decriptação.');
insert into MESSAGES values(8007, 'Acesso permitido ao arquivo <arq_name> para <login_name>.');
insert into MESSAGES values(8008, 'Acesso negado ao arquivo <arq_name> para <login_name>.');
insert into MESSAGES values(8009, 'Arquivo <arq_name> decriptado com sucesso para <login_name>.');
insert into MESSAGES values(8010, 'Arquivo <arq_name> verificado (integridade e autenticidade) com sucesso para <login_name>.');
insert into MESSAGES values(8011, 'Falha na decriptação do arquivo <arq_name> para <login_name>.');
insert into MESSAGES values(8012, 'Falha na verificação (integridade e autenticidade) do arquivo <arq_name> para <login_name>.');
insert into MESSAGES values(9001, 'Tela de saída apresentada para <login_name>.');
insert into MESSAGES values(9002, 'Saída não liberada por falta de one-time password para <login_name>.');
insert into MESSAGES values(9003, 'Botão sair pressionado por <login_name>.');
insert into MESSAGES values(9004, 'Botão voltar de sair para o menu principal pressionado por <login_name>.');

insert into UGROUPS values(0, 'Administrador');	
insert into UGROUPS values(1, 'Usuário');	

insert into USERS values('admin@inf1416.puc-rio.br', '12cf6eb9dc8a875cf9eeb39e7b6f7bd4beaf87fa', 'gw12sVZIOK', 0, 'Administrador', '-----BEGIN CERTIFICATE-----
MIID5zCCAs+gAwIBAgIBAzANBgkqhkiG9w0BAQsFADB6MQswCQYDVQQGEwJCUjEM
MAoGA1UECAwDUmlvMQwwCgYDVQQHDANSaW8xDDAKBgNVBAoMA1BVQzELMAkGA1UE
CwwCREkxEzARBgNVBAMMCkFDIElORjE0MTYxHzAdBgkqhkiG9w0BCQEWEGNhQGRp
LnB1Yy1yaW8uYnIwHhcNMTcwNDEwMTkyODM0WhcNMTgwNDEwMTkyODM0WjB3MQsw
CQYDVQQGEwJCUjEMMAoGA1UECAwDUmlvMQwwCgYDVQQKDANQVUMxCzAJBgNVBAsM
AkRJMRYwFAYDVQQDDA1BZG1pbmlzdHJhZG9yMScwJQYJKoZIhvcNAQkBFhhhZG1p
bkBpbmYxNDE2LnB1Yy1yaW8uYnIwggEiMA0GCSqGSIb3DQEBAQUAA4IBDwAwggEK
AoIBAQDDnq2WpTioReNQ3EapxCdmUt9khsS2BHf/YB7tjGILCzQegnV1swvcH+xf
d9FUjR7pORFSNvrfWKt93t3l2Dc0kCvVffh5BSnXIwwbW94O+E1Yp6pvpyflj8YI
+VLy0dNCiszHAF5ux6lRZYcrM4KiJndqeFRnqRP8zWI5O1kJJMXzCqIXwmXtfqVj
WiwXTnjU97xfQqKkmAt8Z+uxJaQxdZJBczmo/jQAIz1gx+SXA4TshU5Ra4sQYLo5
+FgAfA2vswHGXA6ba3N52wydZ2IYUJL2/YmTyfxzRnsyuqbL+hcOw6bm+g0OEIIC
7JduKpinz3BieiO15vameAJlqpedAgMBAAGjezB5MAkGA1UdEwQCMAAwLAYJYIZI
AYb4QgENBB8WHU9wZW5TU0wgR2VuZXJhdGVkIENlcnRpZmljYXRlMB0GA1UdDgQW
BBSeUNmquC0OBxDLGpUaDNxe1t2EADAfBgNVHSMEGDAWgBQRjus8Iy3raBF+Q43U
TwdIJfUrJjANBgkqhkiG9w0BAQsFAAOCAQEARLoAiIG4F59BPa4JI0jrSuf1lzKi
SOUTKqxRBVJElaI/pbuImFXi3s0Ur6BprkIab8HLGYDIIIfF/WuM3cCHrqbpLtVn
1/QZ7imyr7m/owq8AypU5koOTa9Gn21oeEnIPO3Pqh5vVrtgZYM7Fdze4RLSZbt1
0sR2bM3PmfTrDFlfk557VZa+kKaTnUKrrg04P+npa9KV8lsZnmigYQyBzRIEUZJN
JvhgjK8iOLc08HU+A2YZuPI+aPde9X6Y2KIQ/Y1sQVnm5esP1zKzLrZ0Hwa+E62l
gv1Ck3N/H4Afb3uSNha6rOBWBuc02Gtex4avclOgDVdUDCB3IzIN0CAeKA==
-----END CERTIFICATE-----
', 0, 0, 0, null);