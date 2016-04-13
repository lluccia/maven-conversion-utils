Utility project to help converting legacy projects to Maven.

# Build
`mvn clean package appassembler:assemble`

The executable scripts (sh and bat) will be created at `target/appassembler/bin`

# Scripts
The following script is available.

## searchJars
This script will find jar files found on the directory passed as argument and subdirectories, will calculate its checksums and search for them in Maven Central repository.



usage: `./searchJars.sh <directory>` or `searchJars.bat <directory>`

The output is written to a text file (output.txt), tab separated, in the current directory.

| Column | Description |
| --- | --- |
| FILE_PATH | jar file path |
| CHECKSUM | SHA1 file checsum |
| MAVEN_ID | Maven artifact identifier in format <groupId>:<artifactId>:<version> |
| MAVEN_SNIPPET | code snippet for pom.xml |
| TIMESTAMP | artifact timestamp |
| CLASSIFIERS | available classifiers for artifact (sources, javadoc...) |
