plugins {
    java
}

group = "de.quartzcraft"
version = "1.0.0"

repositories {
    mavenCentral()
    maven("https://repo.papermc.io/repository/maven-public/")
}

dependencies {
    // Es werden nur alte, stabile API-Teile benutzt. Das Plugin laeuft deshalb
    // auch auf einem 26.3-Server. Falls die Version nicht aufgeloest wird,
    // kannst du auch "26.2.build.+" oder "26.3.build.+" probieren.
    compileOnly("io.papermc.paper:paper-api:26.1.2.build.+")
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(25))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}
