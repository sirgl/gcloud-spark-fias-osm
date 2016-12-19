#!/usr/bin/env bash
gcloud compute --project "high-hue-145908" instances create "instance-main" \
--zone "asia-east1-c" --machine-type "n1-standard-4" --subnet "default" \
--metadata startup-script-url=gs://fias-osm/cloud_install.sh --maintenance-policy "MIGRATE" \
--scopes default="https://www.googleapis.com/auth/cloud-platform" \
--tags "http-server","https-server" --image "/ubuntu-os-cloud/ubuntu-1404-trusty-v20161109"
--boot-disk-size "10" --boot-disk-type "pd-standard" \
--boot-disk-device-name "instance-main"
