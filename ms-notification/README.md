# Desafio Compasso MS Notification 💻

O microserviço de notificação está utilizando MongoDb como banco, RabbitMQ para comunicação assicrona com o microserviços user. Essa comunicação com RabbitMQ até faz sentido aqui, pois não há nenhum retorno no momento a MS user, então achei interessante utilizar ela aqui.
Faltou desenvolver testes unitários para essa aplicação, porém não foi possível devido a falta de tempo.


### 📚Recursos Implementados

- Validações
- Comunicação Assicrona com Ms User
- MongoDB
#### - PARA RODAR:

- Configure o RabbitMQ caso você não tenha configurado, a instrução está no repositório do microserviço de user, juntos com o arquivo necessário.


- Mongo

Crie conexão com o MongoDB na porta:

```
localhost:27017/?authSource=teste
```
Ou altere o o arquivo src/main/resources/application.yml e {conexao} por uma conexão válida 
```
mongo:
  uri:{conexao}
```
### - Aplicação

- Necessário também que o RabbitMQ esteja rodando!

Execute no cli:
```
mvn spring-boot:run
```
