FROM node:10.15.3
WORKDIR /workdir
COPY ./client /workdir
RUN rm -rf node_modules/ && rm -f package-lock.json
RUN npm install 
RUN npm run build:production

FROM maven:3.6.1-jdk-8-alpine
WORKDIR /workdir
COPY ./server /workdir
COPY --from=0 /workdir/dist ./src/main/resources/public/
RUN mvn package