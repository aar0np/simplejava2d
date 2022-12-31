# simplejava2d
Research for "Astraflight"

Based on RyiSnow's video series on YouTube:

https://www.youtube.com/watch?v=om59cwR7psI&list=PL_QPQmz5C6WUF-pOQDsbsKbaBZqXj4qSq&index=1

## Pre-requisites:

To port this project successfully between computers, (in Eclipse) I had to add the `res/` package to the project "source" as shown in the screenshot below:

<img src="images/res.png?raw=true" width="400px"/>

## To Run:

From inside Eclipse or another IDE, import as a "new Maven proiject", and run it.

From the command line, simply build with Maven and invoke with Java/JAR:

```bash
mvn clean install
java -jar target/simplejava2d-0.0.1-SNAPSHOT-jar-with-dependencies.jar
```

