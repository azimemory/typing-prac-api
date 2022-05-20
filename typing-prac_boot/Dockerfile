FROM azimemory/init-wallet
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
ENV SPRING_DATASOURCE_URL="jdbc:oracle:thin:@db20220510092408_high?TNS_ADMIN=/usr/oracle_wallet/Wallet_DB20220510092408"
ENTRYPOINT ["java","-jar","/app.jar"]