package me.xmbest.hyper.vm

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import me.xmbest.hyper.base.BaseViewModule
import me.xmbest.hyper.cons.SystemUiCons
import me.xmbest.hyper.utils.SPUtils

class SystemuiLockViewModule : BaseViewModule() {
    val enableLockShowSimName = mutableStateOf(SPUtils.getBoolean(SystemUiCons.LOCK_SHOW_SIM_NAME,false))
    val enableLockNotificationSink = mutableStateOf(SPUtils.getBoolean(SystemUiCons.LOCK_NOTIFICATION_SINK,false))
    val notificationSinkProgress = mutableStateOf(SPUtils.getInt(SystemUiCons.LOCK_NOTIFICATION_SINK_PROGRESS,1000))
    val enableLockFirstInfo = mutableStateOf(SPUtils.getBoolean(SystemUiCons.REMOVE_LOCK_FIRST_INFO,false))

    fun updateLockShowSimName(value:Boolean){
        enableLockShowSimName.value = value
        SPUtils.setBoolean(SystemUiCons.LOCK_SHOW_SIM_NAME,value)
    }

    /**
     * 开启锁屏通知下沉
     */
    fun enableLockNotificationSink(value:Boolean){
        enableLockNotificationSink.value = value
        SPUtils.setBoolean(SystemUiCons.LOCK_NOTIFICATION_SINK,value)
    }

    /**
     * 通知下沉高度
     */
    fun updateNotificationSinkProgress(value:Int){
        notificationSinkProgress.value = value
        SPUtils.setInt(SystemUiCons.LOCK_NOTIFICATION_SINK_PROGRESS,value)
    }

    /**
     * 去除锁屏第一行信息
     */
    fun removeLockFirstInfo(value:Boolean){
        enableLockFirstInfo.value = value
        SPUtils.setBoolean(SystemUiCons.REMOVE_LOCK_FIRST_INFO,value)
    }

}