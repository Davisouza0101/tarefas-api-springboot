# API de Tarefas (Spring Boot + JPA + MySQL)

API RESTful para gerenciamento de tarefas.

## Tecnologias
- Java 17
- Spring Boot 3 (Web, Validation)
- Spring Data JPA
- MySQL (MySQL Connector/J)
- Maven

## Como executar
1. Configure o MySQL local e ajuste `spring.datasource.username` e `spring.datasource.password` em `src/main/resources/application.properties`.
2. Garanta que a base `tarefas_db` possa ser criada (a URL já usa `createDatabaseIfNotExist=true`).
3. Compile e rode:
   ```bash
   ./mvnw spring-boot:run
   ```
   ou, se não estiver usando o wrapper:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints
- `POST /api/tarefas` — cria tarefa
- `GET /api/tarefas` — lista tarefas
- `GET /api/tarefas/{id}` — busca por id
- `PUT /api/tarefas/{id}` — atualiza tarefa
- `DELETE /api/tarefas/{id}` — remove tarefa

### Exemplo de corpo (POST/PUT)
```json
{
  "nome": "Desenvolvimento da API",
  "dataEntrega": "2025-12-12",
  "responsavel": "SEU_NOME SEU_RU"
}
```

## Postman
Importe a coleção `postman/Tarefas-API-Collection.json` e o ambiente `postman/Tarefas-API-Env.json`.
Depois, preencha as variáveis `firstName` e `ru` no ambiente para que os exemplos atendam ao critério de evidência.

## Evidências (dicas rápidas)
- Teste 1: Executar `POST Criar Tarefa (com Nome + RU)` e conferir em `GET Listar Tarefas`.
- Teste 2: `GET Listar Tarefas` deve exibir o registro com seu nome e RU.
- Teste 3: `PUT Atualizar Tarefa (Nome + RU)` e verificar mudança no `GET`.
- Teste 4: `DELETE Remover Tarefa (Nome + RU)` e verificar ausência no `GET`.

## Observações
- O campo `dataEntrega` usa o formato `yyyy-MM-dd`.
- A API valida campos obrigatórios (nome, dataEntrega, responsavel).
