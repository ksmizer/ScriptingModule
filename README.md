# ScriptingModule
Module for Ignition to communicate with the Collector database

## Requirements
1. [An Ignition Gateway v7.9.0+](https://inductiveautomation.com/downloads/ignition)
2. [OpenJDK 11.0.1+](https://developers.redhat.com/products/openjdk/overview/)
3. [Maven 3.2.1+](https://maven.apache.org/download.cgi)
4. [OpenSSL](https://wiki.openssl.org/index.php/Binaries) (Only required for signing)

## Build
From the repo folder, run:
```
mvn package
```

## Installation
File Location: `./lego-module-build/target/*.modl`

Install in Ignition Gateway

## Signing the module
Run the batch file on the command line replacing the variables in the curly braces with your alias/password. (`sign.bat {{Alias}} {{Password}}`)

Ex:* `sign.bat thisIsMyAlias thisIsMyPassword`

_* Make sure to follow the prompts_