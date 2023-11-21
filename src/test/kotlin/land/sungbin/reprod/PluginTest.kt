@file:OptIn(ExperimentalCompilerApi::class)

package land.sungbin.reprod

import com.tschuchort.compiletesting.KotlinCompilation
import com.tschuchort.compiletesting.SourceFile
import org.intellij.lang.annotations.Language
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.JvmTarget
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder

class PluginTest {
  @get:Rule
  val tempDir: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

  @Test
  fun debug() {
    @Language("kotlin")
    val source = """fun main() { println("Hello, World!") }"""
    kotlinCompilation(SourceFile.kotlin("main.kt", source)).compile()
  }

  private fun kotlinCompilation(vararg sourceFiles: SourceFile) =
    KotlinCompilation().apply {
      workingDir = tempDir.root
      sources = sourceFiles.asList()
      jvmTarget = JvmTarget.JVM_17.toString()
      inheritClassPath = true
      compilerPluginRegistrars = listOf(PluginRegistrar())
    }
}
