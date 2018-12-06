FROM java:8
RUN mkdir -p /usr/src/myapp
COPY ./mysql-connector-java-5.1.6-bin.jar /usr/src/myapp
COPY ./mysql.java /usr/src/myapp
WORKDIR /usr/src/myapp
RUN javac mysql.java
CMD ["java","-classpath","mysql-connector-java-5.1.6-bin.jar:.","mysql"]
