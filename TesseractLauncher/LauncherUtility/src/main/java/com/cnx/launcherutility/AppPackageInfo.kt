package com.cnx.launcherutility

import android.graphics.drawable.Drawable

data class AppPackageInfo (val appName: String?, val packageName: String, val appIcon: Drawable,
                            val mainActivityClassName: String, val versionCode: Long, val versionName: String)