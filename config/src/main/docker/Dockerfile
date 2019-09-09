FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD comparison.jar comparison.jar
ADD entrypoint.sh /opt/entrypoint.sh
RUN chmod 777 /opt/entrypoint.sh
RUN jar -xvf comparison.jar
RUN chmod 777 /BOOT-INF/classes/startup.sh
WORKDIR /BOOT-INF/classes
ENTRYPOINT ["./startup.sh", "console"]
EXPOSE 7047



#FROM openjdk:8-jdk-alpine
#VOLUME /tmp
#ADD BOOT-INF /BOOT-INF
#ADD META-INF /META-INF
#ADD org /org
#ADD entrypoint.sh /opt/entrypoint.sh
#RUN chmod 777 /opt/entrypoint.sh
#RUN chmod 777 /BOOT-INF/classes/startup.sh
#WORKDIR /BOOT-INF/classes
#ENTRYPOINT ["./startup.sh", "console"]
#EXPOSE 7047
#ENV TZ=Asia/Shanghai

#基于基础版打镜像
#FROM springboot/comparison:0.0
#ADD classes /BOOT-INF/classes