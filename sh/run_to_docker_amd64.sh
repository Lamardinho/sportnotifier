# for AMD
echo && echo STOP: && docker stop sportnotifier
echo && echo REMOVE_CONTAINER: && docker rm sportnotifier
echo && echo REMOVE_IMAGE: && docker rmi slezkin23/sportnotifier
echo && echo RUN: && docker run -it --name sportnotifier -p 8090:8090 -e SPRING_PROFILES_ACTIVE=local,datasource_h2,ssl -d --network sportnotifier_network --restart always slezkin23/sportnotifier
echo && echo LOGS: && docker logs -f sportnotifier
