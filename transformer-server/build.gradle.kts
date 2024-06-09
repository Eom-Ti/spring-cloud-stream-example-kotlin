dependencies {
    // start coin-functions module
    implementation (project(":coin-functions"))

    // docker compose auto start
    testAndDevelopmentOnly ("org.springframework.boot:spring-boot-docker-compose")

}
