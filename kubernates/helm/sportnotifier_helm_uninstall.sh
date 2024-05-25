#!/bin/bash
# shellcheck disable=SC2164
# Установка в кубер
cd ~/IdeaProjects/sportnotifier/kubernates/helm/
helm uninstall sportnotifier
sleep 15
