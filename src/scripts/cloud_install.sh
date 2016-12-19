#!/usr/bin/env bash
#USAGE: place it in fias-osm bucket, run fias-download

sudo apt-get -y install unrar-free dbview pip

export GCSFUSE_REPO=gcsfuse-xenial
echo "deb http://packages.cloud.google.com/apt $GCSFUSE_REPO main" | sudo tee /etc/apt/sources.list.d/gcsfuse.list
curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
sudo apt-get update
sudo apt-get -y install gcsfuse
sudo mkdir /mnt/fias-osm
gcsfuse fias-osm /mnt/fias-osm
sudo pip install simpledbf
wget http://fias.nalog.ru/Public/Downloads/20161114/fias_dbf.rar
unrar e -r /opt/



#export GCSFUSE_REPO=gcsfuse-`lsb-release -c -s`
#echo "deb http://packages.cloud.google.com/apt ${GCSFUSE_REPO} main" | sudo tee /etc/apt/sources.list.d/gcsfuse.list
#curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key add -
#sudo apt-get update
#sudo apt-get -y install gcsfuse
#gcsfuse fias /mnt/gcs-bucket
#cd /mnt/gcs-bucket; wget http://fias.nalog.ru/Public/Downloads/20161024/fias_dbf.rar
#unrar fias_dbf.rar