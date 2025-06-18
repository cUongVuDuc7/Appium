FROM alpine
RUN apk add openjdk17
ENV PATH $PATH:/usr/lib/jvm/java-17-openjdk/bin
WORKDIR /usr/share/demoDocker
ENTRYPOINT date
# entrypoint sẽ ghi đè lên cmd
CMD ["echo", "Hello world!"]
