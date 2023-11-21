@file:OptIn(ExperimentalCompilerApi::class)
@file:Suppress("unused")

package land.sungbin.reprod

import com.google.auto.service.AutoService
import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.compiler.plugin.CompilerPluginRegistrar
import org.jetbrains.kotlin.compiler.plugin.ExperimentalCompilerApi
import org.jetbrains.kotlin.config.CompilerConfiguration

@AutoService(CompilerPluginRegistrar::class)
class PluginRegistrar : CompilerPluginRegistrar() {
  override val supportsK2 = true
  override fun ExtensionStorage.registerExtensions(configuration: CompilerConfiguration) {
    IrGenerationExtension.registerExtension(PluginExtension())
  }
}