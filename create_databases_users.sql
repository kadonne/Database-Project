\if :{?user1} \else \set user1 'ammar' \endif
\if :{?user2} \else \set user2 'kenneth' \endif
\if :{?user3} \else  \set user3 'andy' \endif
\if :{?user_pass} \else  \set user_pass '12345' \endif

SET client_min_messages TO warning;

DROP DATABASE IF EXISTS :user1;
DROP DATABASE IF EXISTS :user2;
DROP DATABASE IF EXISTS :user3;

DROP ROLE IF EXISTS :user1;
DROP ROLE IF EXISTS :user2;
DROP ROLE IF EXISTS :user3;

CREATE USER :user1 WITH PASSWORD :'user_pass';
CREATE USER :user2 WITH PASSWORD :'user_pass';
CREATE USER :user3 WITH PASSWORD :'user_pass';

CREATE DATABASE :user1 OWNER :user1;
CREATE DATABASE :user2 OWNER :user2;
CREATE DATABASE :user3 OWNER :user3;

GRANT ALL PRIVILEGES ON DATABASE :user1 TO :user1;
GRANT ALL PRIVILEGES ON DATABASE :user2 TO :user2;
GRANT ALL PRIVILEGES ON DATABASE :user3 TO :user3;
