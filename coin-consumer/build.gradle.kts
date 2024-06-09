dependencies {
    // start coin-functions module
    implementation(project(":coin-functions"))

    // start redis dependencies
    implementation("org.springframework.boot:spring-boot-starter-data-redis")

    // docker compose auto start
    testAndDevelopmentOnly ("org.springframework.boot:spring-boot-docker-compose")

    // datatype-jsr310 start
    implementation ("com.fasterxml.jackson.datatype:jackson-datatype-jsr310")
}
