call runcrud.bat
if "%ERRORLEVEL%" == "0" goto openbrow
echo.
echo some errors in RUNCRUD
goto fail

:openbrow
start firefox http://localhost:8080/crud/v1/task/getTasks
if "%ERRORLEVEL%" == "0" goto end
echo Cannot open the browser
goto fail

:fail
echo.
echo There were errors

:end
echo.
echo task done