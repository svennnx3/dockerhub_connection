FROM java:8
RUN curl -L -o /mysql-connector-java-8.0.13.jar https://repo1.maven.org/maven2/mysql/mysql-connector-java/8.0.13/mysql-connector-java-8.0.13.jar
ENV CLASSPATH=/mysql-connector-java-8.0.13.jar:${CLASSPATH}
COPY ./mysql.java ./
COPY ./skrypt.sh ./skrypt.sh
RUN javac mysql.java
RUN chmod 777 ./skrypt.sh
CMD ./skrypt.sh
