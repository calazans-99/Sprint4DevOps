# How-to Deploy (Azure PaaS)

1) **Provisionar recursos (PaaS)**
   - Abra o Azure Cloud Shell (PowerShell) ou local.
   - Execute `infra/provision.ps1` (edite o RM e senha).
2) **Configurar App Service**
   - Em Configuration, garanta `APPLICATIONINSIGHTS_CONNECTION_STRING` (script já seta).
   - Connection Strings → `DefaultConnection` (script já seta).
3) **Configurar GitHub Actions**
   - Pegue o *Publish Profile* do Web App.
   - Em GitHub → Settings → Secrets → Actions → **AZUREAPPSERVICE_PUBLISHPROFILE**.
4) **Fazer push na `main`**
   - CI: build+test → artefato
   - CD: deploy no App Service.
5) **Testar CRUD**
   - Use as rotas REST para demonstrar persistência.
