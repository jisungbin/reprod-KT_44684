plugins {
  kotlin("jvm") version "2.0.0-Beta1"
  id("com.google.devtools.ksp") version "2.0.0-Beta1-1.0.14"
}

repositories {
  mavenCentral()
}

tasks.withType<Test>().configureEach {
  useJUnitPlatform()
  outputs.upToDateWhen { false }
}

dependencies {
  compileOnly("org.jetbrains.kotlin:kotlin-compiler-embeddable:2.0.0-Beta1")

  implementation("com.google.auto.service:auto-service-annotations:1.0.1")
  ksp("dev.zacsweers.autoservice:auto-service-ksp:1.1.0")

  testImplementation("dev.zacsweers.kctfork:ksp:0.5.0-alpha01")
  testImplementation("junit:junit:4.13.2")
  testRuntimeOnly("org.junit.vintage:junit-vintage-engine:5.10.1")
}