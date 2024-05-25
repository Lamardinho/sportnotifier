#!/bin/bash
# shellcheck disable=SC2164
# Установка в кубер
cd ~/IdeaProjects/sportnotifier/kubernates/helm/
helm upgrade -i sportnotifier ./sportnotifier/
sleep 15
