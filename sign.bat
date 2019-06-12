echo off
set arg1=%1
set arg2=%2
shift
shift
CALL mvn package
cd %~dp0/module-signer
CALL mvn package
CALL keytool -genkey -noprompt -alias %arg1% -dname "CN=Aera Energy, OU=CS - Design and Development, O=Aera, L=Bakersfield, ST=California, C=US correct?" -keyalg RSA -keypass %arg2% -storepass %arg2% -keystore keystore.jks -validity 3650
CALL keytool -export -alias %arg1% -storepass %arg2% -file certificate.crt -keystore keystore.jks -keypass %arg2%
CALL keytool -import -v -trustcacerts -alias %arg1% -file certificate.crt -keystore cacerts.jks -keypass %arg2% -storepass %arg2%
CALL keytool -export -alias %arg1% -storepass %arg2% -file certificateChain.p7b -keystore cacerts.jks -keypass %arg2%
CALL java -jar target/module-signer-1.0.0-jar-with-dependencies.jar -keystore=keystore.jks -keystore-pwd=%arg2% -alias=%arg1% -alias-pwd=%arg2% -chain=certificateChain.p7b -module-in=../scripting-module-build/target/Scripting-Module-unsigned.modl -module-out=../scripting-module-build/target/Scripting-Module-signed.modl
del *.crt
del *.jks
del *.p7b