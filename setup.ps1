$user1    = $args[0]
$user2    = $args[1]
$user3    = $args[2]
$user_pass = $args[3]

if (Test-Path Env:PGPASSWORD) { Remove-Item Env:PGPASSWORD; }

if (-not $user1) { $user1 = "ammar" }
if (-not $user2) { $user2 = "andy" }
if (-not $user3) { $user3 = "kenneth" }
if (-not $user_pass) { $user_pass = "12345"; }

psql -U postgres --set user1=$user1 --set user2=$user2 --set user3=$user3 --set user_pass=$user_pass -f create_databases_users.sql
.\repopulate.ps1 $user1 $user2 $user3 $user_pass