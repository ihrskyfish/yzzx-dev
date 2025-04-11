echo "postCreate.sh"
sudo chown -R $(whoami) .
# usermod -aG docker vscode
mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests -Dmaven.test.skip=true