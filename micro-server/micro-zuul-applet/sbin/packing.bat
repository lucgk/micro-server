@echo off
echo [INFO] Package the jar in target dir.

cd %~dp0
cd ..
call mvn clean package -Pspace_standalone -Dmaven.test.skip=true

cd sbin
pause