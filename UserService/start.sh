#!/bin/bash

PROJECT_NAME=UserService

# Определить директорию проекта
PROJECT_DIR=/home/ubuntu/my-market/OnlineMarket/${PROJECT_NAME}

# Перейти в директорию проекта
cd ${PROJECT_DIR}
rm -R target
# Собрать проект с помощью Maven
mvn clean package -DskipTests

# Определить путь к файлу JAR
JAR_FILE=target/${PROJECT_NAME}-0.0.1-SNAPSHOT.jar

# Определить имя сервиса
SERVICE_NAME=${PROJECT_NAME}
systemctl stop ${SERVICE_NAME}
rm -R /etc/systemd/system/${SERVICE_NAME}.service
# Определить путь к файлу конфигурации
#SERVICE_CONFIG=${PROJECT_DIR}/myapp.conf

# Создать службу systemd для сервиса
echo "[Unit]
Description=My Java Spring Boot App
After=syslog.target

[Service]
User=root
WorkingDirectory=${PROJECT_DIR}
ExecStart=/usr/bin/java -jar ${JAR_FILE}
SuccessExitStatus=143
Restart=always

[Install]
WantedBy=multi-user.target" > /etc/systemd/system/${SERVICE_NAME}.service

# Обновить службу systemd и запустить сервис
systemctl daemon-reload
systemctl start ${SERVICE_NAME}
systemctl enable ${SERVICE_NAME}
