package com.rk.xededitor.python

import android.app.Activity
import android.content.Context
import com.rk.file.BuiltinFileType
import com.rk.file.FileObject
import com.rk.icons.Icon
import com.rk.lsp.LspConnectionConfig
import com.rk.lsp.LspServer
import io.github.rosemoe.sora.event.ContentChangeEvent
import io.github.rosemoe.sora.event.Unsubscribe
import io.github.rosemoe.sora.lsp.events.EventType
import io.github.rosemoe.sora.lsp.events.diagnostics.queryDocumentDiagnostics
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.eclipse.lsp4j.DocumentDiagnosticReport
import java.io.File
import java.net.URI

class PythonServer(val lspPath: String) : LspServer() {
    override val id: String = "rk.python"
    override val languageName: String = "Python"
    override val serverName: String = "ty"
    override val supportedExtensions: List<String> = listOf("py")
    override val icon: Icon? = BuiltinFileType.PYTHON.icon

    override suspend fun isInstalled(context: Context): Boolean {
        return true
    }


    override fun install(activity: Activity) {}

    override fun uninstall(activity: Activity) {}

    override suspend fun isUpdatable(context: Context): Boolean {
        return false
    }

    override fun update(activity: Activity) {}


    override fun getConnectionConfig(): LspConnectionConfig {
        File(lspPath).setExecutable(true)
        return LspConnectionConfig.Process(arrayOf(lspPath,"server"))
    }

    override val canBeUninstalled: Boolean = false

    override suspend fun onInitialize(lspConnector: com.rk.lsp.LspConnector) {
        lspConnector.lspEditor?.requestManager?.didChangeConfiguration(
            org.eclipse.lsp4j.DidChangeConfigurationParams(
                mapOf("ty" to mapOf("diagnosticMode" to "workspace"))
            )
        )
    }

    override fun getInitializationOptions(uri: URI?): Any = mapOf("diagnosticMode" to "workspace")
}