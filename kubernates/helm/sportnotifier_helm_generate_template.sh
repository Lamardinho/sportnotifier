#!/bin/bash
# shellcheck disable=SC2164
# Сформировать шаблон
cd ~/IdeaProjects/sportnotifier/kubernates/helm/
helm template app ./sportnotifier/ -f my_sportnotifier_values.yaml --debug > sportnotifier_deploy.yaml

echo "" && echo "*************** DONE ***************"
sleep 7
