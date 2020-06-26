#!/usr/bin/env bash
CLASSPATH=Adsync-jar-with-dependencies.jar
ug=`java -cp ${CLASSPATH} com.adsync.data.generators.UsersAndGroupsWrapper`
if [ ! $? -eq 0 ]; then
    exit 1;
fi
echo $ug

##DevicesAndOrganizations will be enabled in Phase-2
#do=`java -cp ${CLASSPATH} com.adsync.data.generators.DevicesAndOrganizationsWrapper`

#if [ ! $? -eq 0 ]; then
    #exit 1;
#fi
#echo $do

###As per the latest discussion this piece of logic will be moved under gatling codebase
#java -cp ${CLASSPATH} com.adsync.data.utilities.CompressAndEncode
