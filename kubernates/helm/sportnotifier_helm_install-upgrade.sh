#!/bin/bash
# shellcheck disable=SC2164
# Установка в кубер
cd ~/IdeaProjects/sportnotifier/kubernates/helm/

echo "" && echo "*************** running upgrading/installation ***************"
helm upgrade -i sportnotifier ./sportnotifier/ -f my_sportnotifier_values.yaml
echo "" && echo "*************** DONE ***************"

sleep 10
