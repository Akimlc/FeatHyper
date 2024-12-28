package me.xmbest.hyper.vm

import androidx.compose.runtime.mutableStateOf
import me.xmbest.hyper.base.BaseViewModule
import me.xmbest.hyper.cons.SystemUiCons
import me.xmbest.hyper.utils.SPUtils

class SystemuiLockViewModule : BaseViewModule() {
    val enableLockShowSimName = mutableStateOf(SPUtils.getBoolean(SystemUiCons.LOCK_SHOW_SIM_NAME,false))
    val enableLockFirstInfo = mutableStateOf(SPUtils.getBoolean(SystemUiCons.LOCK_REMOVE_FIRST_INFO,false))

    fun updateLockShowSimName(value:Boolean){
        enableLockShowSimName.value = value
        SPUtils.setBoolean(SystemUiCons.LOCK_SHOW_SIM_NAME,value)
    }

    fun updateLockFirstInfo(value:Boolean){
        enableLockFirstInfo.value = value
        SPUtils.setBoolean(SystemUiCons.LOCK_REMOVE_FIRST_INFO,value)
    }
}