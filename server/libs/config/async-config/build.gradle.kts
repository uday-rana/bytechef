dependencies {
    implementation("org.slf4j:slf4j-api")
    implementation("org.springframework:spring-webmvc")
    implementation("org.springframework.boot:spring-boot-autoconfigure")
    implementation(project(":server:libs:core:tenant:tenant-api"))
}
