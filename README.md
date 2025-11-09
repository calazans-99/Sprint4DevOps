# Sprint4DevOps (Spring Boot + Azure SQL + App Service + App Insights)

## 👥 Autores
- Marcus Vinicius de Souza Calazans — **RM556620**
- Lucas Abud Berbel — **RM557957**

## 🌐 Ambiente
Prefixo de recursos Azure: **sprint4-rm556620**
App Service esperado: **app-sprint4-rm556620**
SQL Server esperado: **sql-sprint4-rm556620**
Resource Group: **rg-sprint4-rm556620**
Application Insights: **ai-sprint4-rm556620**

Youtube: https://youtu.be/NivIaqoZQ0c

GitHub: https://github.com/calazans-99/Sprint4DevOps

Aplicação de exemplo para a GS/Sprint 4 (DevOps). Inclui CRUD de **Conta** e **Lancamento** (1:N).

## Requisitos
- Java 17, Maven 3.9+
- Azure Subscription
- Azure SQL Database, App Service (Linux), Application Insights

## Rodar local
```bash
mvn spring-boot:run
```
> Para produção, configure Connection Strings no App Service (DefaultConnection) ou as variáveis JDBC_URL/DB_USER/DB_PASS.

## Rotas
- `POST /api/contas`
- `GET /api/contas`
- `GET /api/contas/{id}`
- `PUT /api/contas/{id}`
- `DELETE /api/contas/{id}`
- `POST /api/contas/{idConta}/lancamentos`
- `GET /api/contas/{idConta}/lancamentos`
- `PUT /api/lancamentos/{id}`
- `DELETE /api/lancamentos/{id}`

## Deploy (GitHub Actions)
1. Crie o App Service (veja `infra/provision.ps1`).
2. Em **Settings → Secrets → Actions**, crie **AZUREAPPSERVICE_PUBLISHPROFILE** com o conteúdo do *Publish profile* do WebApp.
3. Faça push na branch `main` → CI roda testes e gera artefato → deploy automático.

## Banco de Dados
DDL em `sql/V1__ddl.sql` (SQL Server).

## Telemetria
Configure `APPLICATIONINSIGHTS_CONNECTION_STRING` no App Service.
