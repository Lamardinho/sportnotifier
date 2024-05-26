#!/bin/bash
# shellcheck disable=SC2164
# переустановить в кубере
cd ~/IdeaProjects/sportnotifier/kubernates/helm/

sh sportnotifier_helm_uninstall.sh

sh sportnotifier_helm_install-upgrade.sh
