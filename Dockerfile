FROM maven:3.8.4-openjdk-17 as build

WORKDIR /app

COPY pom.xml .
COPY src ./src

RUN mvn clean package

# Используйте официальный образ Java
FROM openjdk:17

# Установка рабочей директории
WORKDIR /app

# Копирование JAR файла с back-end приложением в контейнер
COPY --from=build /app/target/my_library-1.0.jar app.jar

# Копирование всех зависимостей, если они нужны
# COPY dependency-jars /app/dependency-jars

# Экспонирование порта, если back-end приложение слушает определенный порт
EXPOSE 8081

# Команда для запуска back-end приложения
CMD ["java", "-jar", "app.jar"]