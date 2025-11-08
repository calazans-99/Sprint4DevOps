# Template PDF – Entrega Final (GS / Sprint 4)

**Grupo/Projeto:** Sprint4DevOps  
**Autores:**  
- Marcus Vinicius de Souza Calazans — RM556620  
- Lucas Abud Berbel — RM557957  

**Links:**  
- Repositório GitHub: `<cole aqui o link do repo>`  
- Vídeo (YouTube/Stream): `<cole aqui o link do vídeo>`

---

## 1) Descrição do Projeto
Aplicação Web Spring Boot com persistência em Azure SQL (PaaS), deploy em Azure App Service (Linux), telemetria via Application Insights e CI/CD via GitHub Actions.

## 2) Arquitetura
- App Service: **app-sprint4-rm556620**
- SQL Server: **sql-sprint4-rm556620**, DB: `dbsprint4`
- Application Insights: **ai-sprint4-rm556620**
- Resource Group: **rg-sprint4-rm556620**

## 3) Banco de Dados
- DDL: `sql/V1__ddl.sql` (tabelas Conta e Lancamento com FK)

## 4) CI/CD
- Build + Test + Publish Artifact + Deploy (GitHub Actions)
- *Opcional*: pipeline clássica do Azure DevOps (se adotada, incluir screenshots)

## 5) Evidências do Vídeo
- CRUD completo com **persistência mostrada** no Azure SQL após cada operação
- Telemetria no Application Insights
- Nada em localhost

## 6) Observações
- Não misturamos containers com PaaS
- Nomenclatura dos recursos padronizada pelo RM
