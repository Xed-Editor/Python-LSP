package com.rk.xededitor.python

import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.annotation.Keep
import com.rk.extension.ActivityProvider
import com.rk.extension.ExtensionAPI
import com.rk.extension.ExtensionContext
import com.rk.file.child
import com.rk.lsp.LspRegistry
import com.rk.utils.dialog
import com.rk.utils.logInfo
import java.io.File

@Keep
@Suppress("unused")
class Main(context: ExtensionContext) : ExtensionAPI(context) {

    var pythonServer: PythonServer? = null

    override fun onExtensionLoaded() {
        val pluginPath = context.extension.installPath
        logInfo(pluginPath)

        val abis = Build.SUPPORTED_ABIS
        val arch = when (Build.SUPPORTED_ABIS.firstOrNull()) {
            "arm64-v8a" -> "arm64-v8a"
            "armeabi-v7a" -> "armeabi-v7a"
            "x86_64" -> "x86_64"
            else -> null
        }

        if (arch == null){
            val activity = ActivityProvider.currentActivity
            if (activity != null){
                dialog(
                    activity = activity,
                    title = "Python LSP",
                    msg = "This extension is not supported on your device (ABI ${abis.firstOrNull()}"
                )
            }

            return
        }

        val binPath = File(pluginPath).child("bin").child(arch).child("ty")

        pythonServer = PythonServer(binPath.absolutePath).also {
            LspRegistry.registerServer(it)
        }

    }

    private fun dispose() {
        pythonServer?.let {
            LspRegistry.unregisterServer(it)
        }
    }

    override fun onInstalled() {

    }

    override fun onUpdated() {
        dispose()
    }

    override fun onUninstalled() {
        dispose()
    }

    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle?
    ) {
        // Will most likely never be called, as activities are usually created before the extensions are loaded
    }

    override fun onActivityDestroyed(activity: Activity) {}

    override fun onActivityPaused(activity: Activity) {}

    override fun onActivityResumed(activity: Activity) {}

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

    override fun onActivityStarted(activity: Activity) {}

    override fun onActivityStopped(activity: Activity) {}
}