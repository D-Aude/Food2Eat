REM open with / text editor sous eclipse pour editer le code du script 
REM open with / system editor  sous eclipse pour executer le script

cd /d "%~dp0"

mysql -u root -p < init-database.sql

pause  