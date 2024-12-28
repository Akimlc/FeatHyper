package me.xmbest.hyper.vm

import androidx.compose.runtime.mutableStateOf
import me.xmbest.hyper.base.BaseViewModule
import me.xmbest.hyper.cons.SystemUiCons
import me.xmbest.hyper.utils.SPUtils

class SystemuiLockViewModule : BaseViewModule() {
    val enableLockShowSimName = mutableStateOf(SPUtils.getBoolean(SystemUiCons.LOCK_SHOW_SIM_NAME,false))
    val enableLockFirstInfo = mutableStateOf(SPUtils.getBoolean(SystemUiCons.LOCK_REMOVE_FIRST_INFO,false))
    val enableLockNotificationSink = mutableStateOf(SPUtils.getBoolean(SystemUiCons.LOCK_NOTIFICATION_SINK,false))
    val notificationSinkProgress = mutableStateOf(SPUtils.getInt(SystemUiCons.LOCK_NOTIFICATION_SINK_PROGRESS,1000))
    val enableLockTimeFont = mutableStateOf(SPUtils.getBoolean("LOCK_TIME_FONT",false))
    fun updateLockShowSimName(value:Boolean){
        enableLockShowSimName.value = value
        SPUtils.setBoolean(SystemUiCons.LOCK_SHOW_SIM_NAME,value)
    }

    fun updateLockFirstInfo(value:Boolean){
        enableLockFirstInfo.value = value
        SPUtils.setBoolean(SystemUiCons.LOCK_REMOVE_FIRST_INFO,value)
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
     * 锁屏字体
     */
    fun updateLockFont(value:Boolean){
        enableLockTimeFont.value = value
        SPUtils.setBoolean(SystemUiCons.LOCK_TIME_FONT,value)
    }

}