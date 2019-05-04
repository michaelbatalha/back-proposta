##FROM ubuntu
##EXPOSE 8080
##RUN apt-get update
##RUN apt-get install -y maven git
##RUN git clone https://github.com/michaelbatalha/PROJETO_TESTE.git
##RUN cd /PROJETO_TESTE && mvn clean install && java -jar /PROJETO_TESTE/target/propostaCredito-0.0.1-SNAPSHOT.jar

#FROM openjdk:8
#COPY ./target/propostaCredito-0.0.1-SNAPSHOT.jar propostaCredito-0.0.1-SNAPSHOT.jar
#CMD ["java","-jar","propostaCredito-0.0.1-SNAPSHOT.jar"]

FROM openjdk:8
ADD target/propostacredito.jar propostacredito.jar
ENV JAVA_TOOL_OPTIONS -Dfile.encoding=UTF8
ENV LANG C.UTF-8
EXPOSE 8080
ENTRYPOINT ["java","-jar","propostacredito.jar"]