# for AMD
echo && echo STOP: && docker stop sportnotifier
echo && echo REMOVE_CONTAINER: && docker rm sportnotifier
echo && echo REMOVE_IMAGE: && docker rmi slezkin23/sportnotifier:amd64
echo && echo RUN: && docker run -it --name sportnotifier -p 8090:8090 -e SPRING_PROFILES_ACTIVE=datasource_h2,prod,ssl -d --network sportnotifier_network --restart always slezkin23/sportnotifier:amd64
echo && echo LOGS: && docker logs -f sportnotifier
