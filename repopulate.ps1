$user1    = $args[0]
$user2    = $args[1]
$user3    = $args[2]
$user_pass = $args[3]

if (-not $user1) { $user1 = "ammar" }
if (-not $user2) { $user2 = "andy" }
if (-not $user3) { $user3 = "kenneth" }
if (-not $user_pass) { $user_pass = "12345"; }

javac Populate_tables.java
java Populate_tables

$env:PGPASSWORD = $user_pass;
psql -U $user1 -d $user1 -f AZ\clear.sql -f AZ\make_tables.sql -f AZ\populate.sql;
psql -U $user2 -d $user2 -f LD\clear.sql -f LD\make_tables.sql -f LD\populate.sql;
psql -U $user3 -d $user3 -f GV\clear.sql -f GV\make_tables.sql -f GV\populate.sql;

Remove-Item Env:PGPASSWORD;

.\database_run.ps1 $user1 $user2 $user3 $user_pass