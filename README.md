# Desafio Compasso MS Address 💻

O microserviço de notificação está utilizando MySql como banco, RabbitMQ para comunicação assicrona com o microserviço user. A comunicação pelo RabbitMQ aqui poderia ser trocado utilizando o OpenFeign, devido que, no momento atual, a aplicação  MS User 
apenas requisita o cadastro do cep ao Ms Address sem ocorrer a validação se o cep existe.Com OpenFeign isso poderia ser contornado. 
Faltou também uma forma de MS User pegar as informações de um endereço da Ms Address, porém não tive tempo para implementação. Nesse caso teria feito FeignClient na api de ms user, pois já estão implementados os endpoints nessa api.
Não foi possível implementar os testes também, devido a falta de tempo.

### 📚Recursos Implementados

- Validações
- Comunicação Assicrona com Ms User
- MySQl
- Consumo da Api ViaCep

  
#### - PARA RODAR:

- Configure o RabbitMQ caso você não tenha configurado, a instrução está no repositório do microserviço de user, junto com o arquivo necessário.


MySql
Abre o arquivo que está no path:src/main/resources/application.yml e altere os campos {USER} E {SENHA} com um usuário válido do seu banco mysql
```
datasource:
  username:{USER}
  passowrd:{SENHA}
```
Rode também o script schema.sql contido na raiz do repositório no seu banco para criar o schema da applicação ou execute a query:
```
CREATE SCHEMA `ms_address` DEFAULT CHARACTER SET utf8 ;
```
### - Aplicação

- Necessário também que o RabbitMQ esteja rodando!

Execute no cli:
```
mvn spring-boot:run
```
