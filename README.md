# Execução docker Banco de dados mysql e Spring boot

1º - Executar a linha abaixo no prompt para startar o banco de dados:

docker run --name mysql-standalone -e MYSQL_ROOT_PASSWORD=cal -e MYSQL_DATABASE=calcard -e MYSQL_USER=cal -e MYSQL_PASSWORD=cal -d mysql:5.6

2º - Executar a linha abaixo no prompt para startar o spring boot:

docker run -p 8080:8080 --name proposta-mysql --link mysql-standalone:mysql -d -ti michaelbferreira/back-proposta


# Para acessar a documentação Swagger:

http://localhost:8080/swagger-ui.html



Observações:
- Deverão ser executados 3 passos para startar todos os containers. O primeiro e segundo passo estão descrito acima, o terceiro passo esta neste link: https://github.com/michaelbatalha/front-proposta
- Devido ao prazo, para melhorar a solução vou implementar o docker-compose para startar todos os containers.
