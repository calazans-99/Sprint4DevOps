# Provisionamento Azure para Sprint4DevOps (substitua RM conforme necessário)
param(
  [string]$RM = "sprint4-sprint4-rm556620",
  [string]$Location = "brazilsouth",
  [string]$Sku = "B1"
)

$rg = "rg-sprint4-$RM"
$plan = "plan-sprint4-$RM"
$web = "app-sprint4-$RM"
$sqlsrv = "sql-sprint4-$RM"
$sqldb = "dbsprint4"
$admin = "sqladminuser"
$pass = "Senha@12345!"  # troque

az group create -n $rg -l $Location

az monitor app-insights component create -g $rg -l $Location -a "ai-sprint4-$RM"

az appservice plan create -n $plan -g $rg -l $Location --sku $Sku --is-linux
az webapp create -g $rg -p $plan -n $web --runtime "JAVA:17-java17"

az sql server create -g $rg -n $sqlsrv -l $Location -u $admin -p $pass
az sql db create -g $rg -s $sqlsrv -n $sqldb -e GeneralPurpose -f Gen5 -c 2 -z 5GB

$myip = (Invoke-RestMethod -Uri "https://ifconfig.me")
az sql server firewall-rule create -g $rg -s $sqlsrv -n allow-myip --start-ip-address $myip --end-ip-address $myip

$conn = "Server=tcp:$sqlsrv.database.windows.net,1433;Database=$sqldb;User ID=$admin;Password=$pass;Encrypt=true;Connection Timeout=30;"
az webapp config connection-string set -g $rg -n $web --settings DefaultConnection="$conn" --connection-string-type SQLAzure

$aics = az monitor app-insights component show -a "ai-sprint4-$RM" -g $rg --query connectionString -o tsv
az webapp config appsettings set -g $rg -n $web --settings APPLICATIONINSIGHTS_CONNECTION_STRING="$aics"
