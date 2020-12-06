package com.cnx.launcherutility

import android.content.Context
import android.content.Intent
import android.content.pm.ResolveInfo
import android.os.Build

object LauncherUtility {

    const val PACKAGE_LAUNCHER = "com.cnx.tesseractlauncher"

    fun getAppList(context: Context): ArrayList<AppPackageInfo> {

        val installedAppList = installedApps(context)
        var appsList = ArrayList<AppPackageInfo>()

        loop@for (resolveInfo in installedAppList!!) {

            val packageName = resolveInfo!!.activityInfo.packageName
            val appInfo = context.packageManager.getApplicationInfo(packageName, 0)
            val packageInfo = context.packageManager.getPackageInfo(packageName, 0)
            val appName = context.packageManager.getApplicationLabel(appInfo).toString()
            val applicationIcon = context.packageManager.getApplicationIcon(packageName)
            val mainActivityName = resolveInfo!!.activityInfo.name
            val versionName = packageInfo.versionName
            val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                packageInfo.longVersionCode
            } else {
                packageInfo.versionCode.toLong()
            }

            when(packageName) {

                PACKAGE_LAUNCHER -> continue@loop
                else -> {
                    appsList.add(AppPackageInfo(appName, packageName, applicationIcon, mainActivityName, versionCode, versionName))
                }
            }

        }

        return appsList

    }

    fun installedApps(context: Context): List<ResolveInfo?>? {
        val main_intent = Intent(Intent.ACTION_MAIN, null)
        main_intent.addCategory(Intent.CATEGORY_LAUNCHER)
        return context.packageManager.queryIntentActivities(main_intent, 0)
    }


}