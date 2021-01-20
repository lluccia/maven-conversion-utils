Utility project to help converting legacy projects to Maven.

# Build
```sh
./mvnw clean package
```

The executable scripts (sh and bat) will be created at `target/appassembler/bin`

# Scripts
The following script is available.

## searchJars
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
