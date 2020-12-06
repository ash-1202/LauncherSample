package com.cnx.tesseractlauncher

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.RecyclerView
import com.cnx.launcherutility.AppPackageInfo
import com.cnx.launcherutility.LauncherUtility

class LauncherItemsAdapter: RecyclerView.Adapter<LauncherItemsAdapter.LauncherItemsViewHolder>() {

    var appList: ArrayList<AppPackageInfo>

    init {
        appList = LauncherUtility.getAppList(appInstance)
    }

    class LauncherItemsViewHolder(view: View): RecyclerView.ViewHolder(view), View.OnClickListener {

        var app_name = itemView.findViewById<TextView>(R.id.tv_app_name)
        var app_Icon = itemView.findViewById<AppCompatImageView>(R.id.iv_app_icon)
        var launcher_item_view = itemView.findViewById<RelativeLayout>(R.id.launcher_item_view)
        lateinit var appPackageInfo: AppPackageInfo

        init {
            launcher_item_view.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            when (p0!!.id) {
                R.id.launcher_item_view -> {
                    appInstance.launchApp(appPackageInfo.packageName)
                }
            }
        }

        fun bindViews(appPackageInfo: AppPackageInfo) {
            app_name.text = appPackageInfo.appName
            app_Icon.setImageDrawable(appPackageInfo.appIcon)
            this.appPackageInfo = appPackageInfo
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LauncherItemsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.launcher_app_view_item, parent, false)
        return LauncherItemsViewHolder(view)
    }

    override fun onBindViewHolder(holder: LauncherItemsViewHolder, position: Int) {
        holder.bindViews(appList.get(position))
    }

    override fun getItemCount(): Int {
        return LauncherUtility.getAppList(appInstance).size
    }
}

fun Context.launchApp(packageName : String) {
    val launchIntent = packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null)
        startActivity(launchIntent)
}