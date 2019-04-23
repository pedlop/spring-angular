FROM node:11.12.0
WORKDIR /workdir
COPY ./client /workdir
RUN npm install 
RUN npm run build:production

FROM maven:3.6.1-jdk-8-alpine
WORKDIR /workdir
COPY ./server/portal-da-transparencia /workdir
COPY --from=0 /workdir/dist ./src/main/resources/public/
RUN mvn package