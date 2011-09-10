deployment instruction
1) run 'mvn clean package' to generate the war
2) deployment it to jetty.
3) -Dgroupbuy.api.server=http://localhost:8080  as startup parameter

startup script example
nohup java -Dgroupbuy.api.server=http://localhost:8000 -jar start.jar > ./jetty_web_ui.log &