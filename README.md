This repository contains code related to ADSync Data Generation Tool.

Package Overview
================
There are 3 high level packages: generators, pojos and utilities under src directory ~/AdsyncDataGeneration/src

/generators : classes with  json file creation and entity distribution logic

/pojos : Classes related to different entities for increasing the re-usability of code

/utilities : Helpers classes to create random values, compression And Encode, Csv Parser...


Usage
======
In the root directory (~/AdsyncDataGeneration) following artifacts are available

-Adsync-jar-with-dependencies.jar
  -Build this jar with mvn clean install

-config.properties
  -Referred within the tool to specify different distributions for each set for each run 

-Runner.sh
  -Run this file to create output files under /data folder. Provide the "Adsync-jar-with-dependencies.jar" in classpath in the sh file

-tenantId.csv
  -Referred within the tool to generate new sets for each tenantIds listed in the csv file.

Sample command
===============
sh Runner.sh 
