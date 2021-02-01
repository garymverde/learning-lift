This creation of this sample project, per ExploringLift, should have been a simple matter of running the following maven command:

mvn archetype:generate -U \
-DarchetypeGroupId=net.liftweb \
-DarchetypeArtifactId=lift-archetype-blank \
-DarchetypeVersion=2.0 \
-DarchetypeRepository=http://scala-tools.org/repo-releases \
-DgroupId=demo.helloworld \
-DartifactId=helloworld \
-Dversion=1.0-SNAPSHOT

Perhaps this could would run in the days of Java 6, but it certainly doesn't run with Java 8 - the oldest thing that you can find these days.  I tried to update the command to something a bit newer:

mvn archetype:generate -U \
       -DarchetypeGroupId=net.liftweb \
       -DarchetypeArtifactId=lift-archetype-blank_2.10 \
       -DarchetypeVersion=2.5 \
       -DarchetypeRepository=https://oss.sonatype.org/content/repositories/releases \
       -DgroupId=demo.helloworld \
       -DartifactId=helloworld \
       -Dversion=1.0-SNAPSHOT
       
The resulting pom file is broken and the application does not build.  I've committed the generated pom file in this repo as the first version and subsequently committed the versions required to get it working.
