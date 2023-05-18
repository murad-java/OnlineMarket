#!/bin/bash
sudo systemctl stop EurekaService.service
sudo systemctl stop GateWay.service
sudo systemctl stop nginx.service

sudo rm -R /var/www/html/dist
cd ui_service
sudo rm -R dist
sudo npm install
sudo npm run build
sudo cp -R dist /var/www/html
sudo systemctl start nginx

sudo sh ./EurekaService/start.sh
sudo sh ./GateWay/start.sh
