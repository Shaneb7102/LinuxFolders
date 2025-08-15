FROM ubuntu:latest 
RUN apt-get -y update && apt-get install -y fortunes && apt-get install -y cowsay
ENV PATH=$PATH:/usr/games
COPY run.sh /
CMD ["bash", "/run.sh"]
