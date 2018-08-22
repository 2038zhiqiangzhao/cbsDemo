@echo off
cd /d %~dp0
java -jar .\obator-2.0-SNAPSHOT.jar -configfile %cd%/obator-config-write.xml -overwrite

pause