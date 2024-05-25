#!/bin/bash
# shellcheck disable=SC2164
# Установка в кубер
cd ~/IdeaProjects/sportnotifier/kubernates/helm/
helm template app ./sportnotifier/ --debug > sportnotifier_deploy.yaml
sleep 15
