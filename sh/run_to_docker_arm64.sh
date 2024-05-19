# for for ARM
echo && echo STOP: && sudo docker stop sportnotifier
echo && echo REMOVE_CONTAINER: && sudo docker rm sportnotifier
echo && echo REMOVE_IMAGE: && sudo docker rmi slezkin23/sportnotifier
echo && echo RUN: && sudo docker run -it --name sportnotifier -p 8090:8090 -e SPRING_PROFILES_ACTIVE=prod,datasource_h2,ssl -d --network sportnotifier_network --restart always slezkin23/sportnotifier
echo && echo LOGS: && sudo docker logs -f sportnotifier
