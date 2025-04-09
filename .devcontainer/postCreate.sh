echo "postCreate.sh"
sudo chown -R $(whoami) .
mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests -Dmaven.test.skip=true