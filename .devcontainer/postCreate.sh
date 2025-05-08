echo "postCreate.sh"
sudo chown -R $(whoami) .
#show the entity files
find . -type f | grep entity | grep -v '^./target'
cat src/main/resources/sql/all.sql | mysql --database mmcc -u root -p1qazXSW@ -h dev0mysql  
cat ./src/main/resources/sql/*  | mysql --database mmcc -u root -p1qazXSW@ -h dev0mysql  
# usermod -aG docker vscode
find -type f -name '*.class'  -iname '*config*' -iname '*api*'
find -type f -path './src/*' -path '*/job/*'
git config --global user.email "ihr17867950748@126.com"
git config --global user.name "ihrskyfish"
# analyze the project
tree  --noreport -a -I '*.bcmap|*.png|*.svg|*.vue|vo|dao|dto|entity|mapper|controller|demo|impl|service|util|utils|enums/|target/|*.log|.git/|.devcontainer/|.github/|lib/|.gitignore|.idea/|.vscode/|memory-bank/|*.so|job/|config/|servlet/|test/|application*.yml|banner.txt|LICENSE|README.md|tmp?.*' --prune
mvn spring-boot:run -Dspring.profiles.active=dev -DskipTests -Dmaven.test.skip=true
