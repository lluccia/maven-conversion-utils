# Maven Conversion Utils

Utility project to help converting legacy projects to Maven.

## Build
```sh
./mvnw clean package
```

The executable scripts (sh and bat) will be created at `target/appassembler/bin`

To test the search using the project jars
```shell
./target/appassembler/bin/searchJars.sh ./target/appassembler/repo
```

## Scripts

The following script is available.

### searchJars

This script will find jar files found on the directory passed as argument and subdirectories, will calculate its checksums and search for them in Maven Central repository.

usage: `./searchJars.sh <directory>` or `searchJars.bat <directory>`

The output is written to console, tab separated, it can be redirected to a file if desired.
e.g.:
```sh
./searchJars.sh /project_dir/libs > output.tsv
```

| Column | Description |
| --- | --- |
| FILE_PATH | jar file path |
| CHECKSUM | SHA1 file checsum |
| MAVEN_ID | Maven artifact identifier in format `<groupId>:<artifactId>:<version>` |
| MAVEN_SNIPPET | code snippet for pom.xml |
| GRADLE_SNIPPET | code snipped for gradle builds |
| TIMESTAMP | artifact timestamp |
| CLASSIFIERS | available classifiers for artifact (sources, javadoc...) |

example output:
```tsv
looking for jar files...
Found: ./target/appassembler/repo/json-20230227.jar
Found: ./target/appassembler/repo/maven-conversion-utils-0.0.4-SNAPSHOT.jar
Found 2 files...
calculating checksums...
searching maven central...
creating report...
FILE_PATH       CHECKSUM        MAVEN_ID        MAVEN_SNIPPET   GRADLE_SNIPPET  TIMESTAMP       CLASSIFIERS
./target/appassembler/repo/json-20230227.jar    7a0d4aca76513d8ce81f9b044ce8126b84809ad8        org.json:json:20230227  <dependency><groupId>org.json</groupId><artifactId>json</artifactId><version>20230227</version></dependency>       compile 'org.json:json:20230227'        1677505129000   [-sources.jar, .pom, -javadoc.jar, .jar]
./target/appassembler/repo/maven-conversion-utils-0.0.4-SNAPSHOT.jar    1f8167ff64d01c889f25a5c829bafd65ebffc6d6        No artifacts found
```

## refs

- <https://central.sonatype.org/search/rest-api-guide/>
