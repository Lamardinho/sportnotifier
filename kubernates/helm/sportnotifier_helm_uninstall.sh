#!/bin/bash
# shellcheck disable=SC2164
# Удалить из кубера
cd ~/IdeaProjects/sportnotifier/kubernates/helm/
helm uninstall sportnotifier

echo "" && echo "*************** DONE ***************"
sleep 1
