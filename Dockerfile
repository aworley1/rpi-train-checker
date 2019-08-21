FROM arm32v7/adoptopenjdk:hotspot-bionic
COPY build/libs/rpi-train-checker-all.jar /usr/rpi-train-checker/rpi-train-checker-all.jar
WORKDIR /usr/rpi-train-checker/
CMD ["java", "-jar", "rpi-train-checker-all.jar"]