# Desafio Compasso MS User 💻

O microserviço de user está utilizando mysql como banco, rabbitmq para comunicação assicrona com os microserviços address e notification.
No desenvolmento poderia ter feito essa comunição com o opeinfeign, porém optei pelo rabbitmq para aprender a tecnologia. Penso que o openFeign poderia ser melhor, pois na mesma requisição que envio cep para cadastro, poderia 
ser feito uma validação se o cep existe. Testes unitários foi feitos dos services e controllers, tive dificuldades em faze-los, e ainda não consegui fazer uma quantidade/qualidade melhor  devido ao tempo. 


### SWAGGER

Documentação com maiores detalhes sobre os endpoints:
```
http://localhost:8080/doc_user.html
```

### 📚Recursos Implementados

- Validações
- Exceções
- Swagger
- Jwt
- Senha criptografada
- Comunicação Assicrona com os outros microserviços
- Endpoints necessitam de authenticação

#### - PARA RODAR:

Configure o RabbitMQ

Não consegui elaborar o docker compose do rabbitmq, porém da para criar a imagem rodando
```
docker run -it --name rabbitmq_desafio -p 5672:5672 -p 15672:15672 rabbitmq:3.13-management
```
Após isso, com a container rodando faça o Login com user:guest password:guest no locahost:15672, na aba Overview, clique em import definitions, clique em import e selecione o arquivo definitions.json.

MySql
Abre o arquivo que está no path:src/main/resources/application.yml e altere os campos {USER} E {SENHA} com um usuário válido do seu banco mysql
```
datasource:
  username:{USER}
  passowrd:{SENHA}
```
Rode também o script schema.sql contido na raiz do repositório no seu banco para criar o schema da applicação ou execute a query:
```
CREATE SCHEMA `ms_user` DEFAULT CHARACTER SET utf8 ;
```
### - Aplicação
- Necessário também que o rabbitmq esteja rodando!

Execute no cli:
```
mvn spring-boot:run
```
