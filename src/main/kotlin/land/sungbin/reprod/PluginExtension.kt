@file:Suppress("OPT_IN_USAGE")

package land.sungbin.reprod

import org.jetbrains.kotlin.backend.common.extensions.IrGenerationExtension
import org.jetbrains.kotlin.backend.common.extensions.IrPluginContext
import org.jetbrains.kotlin.ir.declarations.IrModuleFragment
import org.jetbrains.kotlin.ir.types.isNullableAny
import org.jetbrains.kotlin.ir.types.isString
import org.jetbrains.kotlin.ir.util.dump
import org.jetbrains.kotlin.name.CallableId
import org.jetbrains.kotlin.name.FqName
import org.jetbrains.kotlin.name.Name

internal class PluginExtension : IrGenerationExtension {
  override fun generate(moduleFragment: IrModuleFragment, pluginContext: IrPluginContext) {
    val toStringSymbol =
      pluginContext
        .referenceFunctions(
          CallableId(
            packageName = FqName("kotlin"),
            callableName = Name.identifier("toString"),
          ),
        )
        .also { symbols ->
          println("symbols: ${symbols.joinToString()}")
        }
        .single { symbol ->
          val extensionReceiver = symbol.owner.extensionReceiverParameter

          val isValidExtensionReceiver = extensionReceiver != null && extensionReceiver.type.isNullableAny()
          val isValidReturnType = symbol.owner.returnType.isString()

          isValidExtensionReceiver && isValidReturnType
        }
    println("single: ${toStringSymbol.owner.dump()}")
  }
}
