package me.xmbest.hyper.hook.module

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.util.Log
import android.view.View
import android.widget.TextView
import de.robv.android.xposed.XC_MethodHook
import de.robv.android.xposed.XposedBridge
import de.robv.android.xposed.XposedHelpers
import de.robv.android.xposed.callbacks.XC_LoadPackage
import de.robv.android.xposed.callbacks.XC_LoadPackage.LoadPackageParam
import me.xmbest.hyper.annotations.HookMethod
import me.xmbest.hyper.annotations.HookModule
import me.xmbest.hyper.cons.SystemUiCons
import me.xmbest.hyper.base.BaseModule
import me.xmbest.hyper.utils.XSPUtils
import org.json.JSONObject

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
                    Log.d(TAG, "afterHookedMethod: ")
                    param?.let {
                        val view = param.thisObject as View
                        val labelResId: Int = view.resources
                            .getIdentifier("keyguard_carrier_text", "id", "com.android.systemui")
                        val tv = view.findViewById<TextView>(labelResId)
                        Log.d(TAG,"tv.text = " + tv.text)
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
     * 去除锁屏第一行信息
     */
    @HookMethod(SystemUiCons.LOCK_REMOVE_FIRST_INFO, false)
    fun removeLockFirstInfo(lpParam: LoadPackageParam) {
        XposedHelpers.findAndHookMethod(
            "com.miui.clock.MiuiClockController",
            lpParam.classLoader,
            "getClockInfoJson",
            Boolean::class.java,
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)
                    val clockInfoJson = param?.result
                    if (clockInfoJson != null) {
                        val jsonObject = JSONObject(clockInfoJson.toString())
                        val clockInfo = jsonObject.getJSONObject("clockInfo")
                        //修改数据
                        clockInfo.put("classicLine1", 0)
                        param.result = jsonObject.toString()
                        XposedBridge.log("RemoveFirstInfo: Hook Success")
                    }
                }
            }
        )
    }

    /**
     * 锁屏通知下沉
     */
    @HookMethod(SystemUiCons.LOCK_NOTIFICATION_SINK, false)
    fun lockNotificationSink(lpParam: LoadPackageParam) {
        val notificationHeight = XSPUtils.getInt(SystemUiCons.LOCK_NOTIFICATION_SINK_PROGRESS, 1400)
        Log.d(TAG, "lockNotificationSink: $notificationHeight")
        XposedHelpers.findAndHookMethod(
            "com.android.keyguard.clock.KeyguardClockContainer",
            lpParam.classLoader,
            "getClockBottom",
            object : XC_MethodHook() {
                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)
                    param?.result = notificationHeight
                }
            }

        )
    }

    /**
     * 修改锁屏时钟字体
     */
    @HookMethod(SystemUiCons.LOCK_TIME_FONT, false)
    fun lockFont(lpParam: LoadPackageParam) {
        val MiuiTextGlassViewClass =
            XposedHelpers.findClass("com.miui.clock.MiuiTextGlassView", lpParam.classLoader)
        XposedHelpers.findAndHookMethod(
            "com.miui.clock.utils.ClassicClockTimeUtils",
            lpParam.classLoader,
            "setClassicTimeStyle",
            MiuiTextGlassViewClass,
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            Int::class.javaPrimitiveType,
            Boolean::class.javaPrimitiveType,
            Boolean::class.javaPrimitiveType,
            object : XC_MethodHook() {
                override fun beforeHookedMethod(param: MethodHookParam?) {
                    super.beforeHookedMethod(param)
                }

                override fun afterHookedMethod(param: MethodHookParam?) {
                    super.afterHookedMethod(param)

                    val param3 = param?.args?.get(2)
                    try {
                        val miuiTextGlassView = param?.args?.get(0) as TextView
                        when (param3) {
                            27 -> {
                                setCustomFont(miuiTextGlassView, "fonts/OPPODigit07.ttf")
                            }

                            28 -> {
                                setCustomFont(miuiTextGlassView, "fonts/MochiyPopOne.otf")
                            }
                        }


                    } catch (e: Exception) {
                        Log.d("Xposed", "afterHookedMethod: Hook Failed :${e.message}")
                    }

                }
            }
        )
    }


    fun setCustomFont(view: View, fontPath: String) {
        val textView = view as TextView
        val context: Context = textView.context.createPackageContext(
            "me.xmbest.hyper",
            Context.CONTEXT_IGNORE_SECURITY
        )
        val customFont: Typeface = Typeface.createFromAsset(context.assets, fontPath)
        textView.typeface = customFont
    }




}