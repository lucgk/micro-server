cd ..
call mvn clean
@echo clean successful
call mvn archetype:create-from-project
cd target/generated-sources/archetype/
call mvn install
@echo install successful
@echo please wait about 5 min
call mvn archetype:crawl
call mvn archetype:update-local-catalog
@echo The prototype project has been successfully created, you can go desc dir execute: mvn archetype:generate    to generate project
PAUSE