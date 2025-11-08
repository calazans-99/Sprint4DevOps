# Azure DevOps – Guia Completo (YAML + Clássico)

## 1) Criar Projeto e Importar Repositório
1. Acesse **Azure DevOps** → sua **Organization**.
2. **New Project** → Visibility: *Private*, Process: *Agile*.
3. Em **Repos**, importe o repositório do GitHub OU empurre o código para Azure Repos.

## 2) Service Connection (ARM)
Para o job de deploy usar a sua conta Azure com segurança:
1. Vá em **Project Settings → Service connections → New service connection**.
2. Tipo: **Azure Resource Manager**.
3. Método: **Service principal (automatic)**.
4. Scope: **Subscription** (ou Resource Group contendo o App Service).
5. Nomeie como **SC_Sprint4DevOps** (ou outro) e **salve**.

> Se usar outro nome, atualize `azureSubscription` em `azure-pipelines.yml`.

## 3) Pipeline YAML (Multistage)
1. Em **Pipelines → Create Pipeline**.
2. Escolha o repositório.
3. Selecione **Existing Azure Pipelines YAML file** e aponte para **/azure-pipelines.yml**.
4. **Run** para validar.
   - Stage **Build**: usa Java 17, roda testes H2 e publica artefato.
   - Stage **Deploy**: baixa artefato e executa **AzureWebApp@1** para o App Service Linux.

### Variáveis importantes
- `azureSubscription`: nome exato da **Service connection** criada.
- `appName`: `app-sprint4-rm556620` (ajuste se necessário).
- `packagePath`: caminho do `.jar` publicado pelo Build (mantido em `drop/`).

## 4) Configurações no Azure
- **App Service**: criado previamente (veja `infra/provision.ps1`).
- **Connection Strings**: `DefaultConnection` setada pelo script.
- **Application Insights**: `APPLICATIONINSIGHTS_CONNECTION_STRING` setado pelo script.

## 5) Pipeline Clássico (Opcional, se o professor pedir)
### Build (Classic)
- Template Maven.
- Passos: `mvn clean test package` e **Publish Artifacts** apontando para `target/` (gera `.jar`).
- **Enable continuous integration** na aba **Triggers**.

### Release (Classic)
- **New Release Pipeline** → Template **Azure App Service deployment**.
- **Artifact**: build anterior (drop).
- **Continuous deployment trigger**: ativado.
- **App Service**: selecione `app-sprint4-rm556620` e **Package or folder**: `**/*.jar`.

## 6) Teste do CI/CD
- Faça um commit na `main` → Pipeline Build roda testes e publica artefato → Stage Deploy publica no App Service.
- Acesse `https://app-sprint4-rm556620.azurewebsites.net` e execute o CRUD (use ferramentas como Thunder Client/Postman).

## 7) Dicas para o Vídeo
- Mostre **Service connection**, **Execução do Build**, **Artefato**, **Release/Deploy**, **App rodando**, **CRUD + Banco** e **Application Insights**.
