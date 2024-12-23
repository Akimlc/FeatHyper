package me.xmbest.hyper.hook.module

import android.annotation.SuppressLint
import android.util.Log
import android.view.View
import android.widget.TextView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import me.xmbest.hyper.annotations.HookMethod
import me.xmbest.hyper.annotations.HookModule
import me.xmbest.hyper.cons.SystemUiCons
import me.xmbest.hyper.base.BaseModule
import me.xmbest.hyper.utils.SPUtils
import me.xmbest.hyper.utils.XSPUtils

/**
 * systemui 模块
 * @author xmbest
 * @date 2024/09/13
 */
@HookModule("com.android.systemui")
 class SystemUiModule : BaseModule() {

    /**
     * 显示锁屏运营商名称
     * @param lpParam XC_LoadPackage.LoadPackageParam 提供 classLoader
     * @see <a href="https://www.coolapk.com/feed/57865578">锁屏显示状态栏</a>
     */
    @HookMethod(SystemUiCons.LOCK_SHOW_SIM_NAME,false)
    fun showLockSimCardName(lpParam: XC_LoadPackage.LoadPackageParam){
        logD("showLockSimCardName")
        XposedHelpers.findAndHookMethod(
            "com.android.systemui.statusbar.phone.KeyguardStatusBarView",
            lpParam.classLoader,
            "onFinishInflate",
            object : XC_MethodHook() {
                @SuppressLint("DiscouragedApi")
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)
                    Log.d(TAG,"afterHookedMethod: ")
                    param?.let {
                        val view = param.thisObject as View
                        val labelResId: Int = view.resources
                            .getIdentifier("keyguard_carrier_text", "id", "com.android.systemui")
                        val tv = view.findViewById<TextView>(labelResId)
                        if (tv.text.contains("|")){
                            tv.text = tv.text.split("|")[0]
                        }
                        tv.visibility = View.VISIBLE
                    }
                }
            }
        )
    }

    /**
     * 锁屏通知下沉
     */
    @HookMethod(SystemUiCons.LOCK_NOTIFICATION_SINK,false)
    fun lockNotificationSink(lpParam: XC_LoadPackage.LoadPackageParam){
        val notificationHeight = XSPUtils.getInt(SystemUiCons.LOCK_NOTIFICATION_SINK_PROGRESS,600)
        Log.d(TAG, "lockNotificationSink: $notificationHeight")
        XposedHelpers.findAndHookMethod(
            "com.android.keyguard.clock.KeyguardClockContainer",
            lpParam.classLoader,
            "getClockBottom",
            object :XC_MethodHook(){
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)
                    param?.result = notificationHeight


                }
            }

        )
    }

}