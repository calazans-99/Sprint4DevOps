# Roteiro do Vídeo – Sprint 4 / GS (DevOps)

> "O vídeo é a evidência da entrega" — Mostrar **todas** as etapas sem cortes.

1) **Infra na Azure (PaaS)**
   - Executar `infra/provision.ps1 -RM "sprint4-rm556620"`
   - Mostrar RG, App Service, SQL e App Insights criados

2) **Pipeline CI/CD**
   - Mostrar GitHub Actions rodando:
     - Build + testes (H2)
     - Publicação do artefato
     - Deploy no App Service
   - (Se usar Azure Pipelines clássico, mostrar Build e Release com os artefatos)

3) **Aplicação em Produção**
   - Abrir `https://app-sprint4-rm556620.azurewebsites.net`
   - Executar **CRUD completo** via Postman/Thunder Client:
     - `POST /api/contas`
     - `GET /api/contas`
     - `POST /api/contas/{id}/lancamentos`
     - `GET /api/contas/{id}/lancamentos`
     - `PUT /api/lancamentos/{id}`
     - `DELETE /api/lancamentos/{id}`
   - A **cada operação**, abrir o **Azure SQL** e mostrar a persistência

4) **Telemetria**
   - Mostrar o **Application Insights** com requisições chegando (Live Metrics / Logs)

5) **Encerramento**
   - Exibir **README** do GitHub, **DDL** (`sql/V1__ddl.sql`) e **how-to**
   - Falar os **autores e RMs**
