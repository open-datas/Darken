#!/bin/sh
for i in /BOOT-INF/lib/*;
do CLASSPATH=$i:"$CLASSPATH";
done 
CLASSPATH=.:/BOOT-INF:$CLASSPATH
export CLASSPATH

if [ "$1" = "console" ]
then
    java -d64 -cp $CLASSPATH EurekaApplication
else
    nohup java  -d64 -cp $CLASSPATH EurekaApplication >/dev/null 2>&1 &
fi
