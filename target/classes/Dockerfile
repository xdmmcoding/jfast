FROM java:8
VOLUME /tmp/docker/sdemo
ADD springbootdemo-1.0.war /sdemo.war
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/sdemo.war"]