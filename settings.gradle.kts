pluginManagement {
    val springBootVersion: String by settings
    val springDependencyManagementVersion: String by settings
    val kotlinVersion: String by settings

    resolutionStrategy{
        eachPlugin{
            when(requested.id.id){
                "org.springframework.boot" -> useVersion(springBootVersion)
                "io.spring.dependency-management" -> useVersion(springDependencyManagementVersion)
                "org.jetbrains.kotlin.jvm", "org.jetbrains.kotlin.plugin.spring" -> useVersion(kotlinVersion)
            }
        }
    }
}

rootProject.name = "spring-cloud-stream-example-kotlin"
include(
    ":coin-producer",
    "coin-consumer",
    "transformer-server",
    "coin-functions"
)
