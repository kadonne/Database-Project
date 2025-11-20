$user1 = $args[0]
$user2 = $args[1]
$user3 = $args[2]
$user_pass = $args[3]
$jar = Get-ChildItem -Path "." -Filter "postgresql*.jar" | Select-Object -First 1

javac -cp $jar.Name .\DatabaseRunner.java
java -cp ".;$($jar.Name)" DatabaseRunner $user1 $user2 $user3 $user_pass