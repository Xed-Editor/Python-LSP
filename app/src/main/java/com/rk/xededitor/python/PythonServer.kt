package com.rk.xededitor.python

import android.app.Activity
import android.content.Context
import com.rk.file.BuiltinFileType
import com.rk.icons.Icon
import com.rk.lsp.LspConnectionConfig
import com.rk.lsp.LspServer
import java.io.File

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
}