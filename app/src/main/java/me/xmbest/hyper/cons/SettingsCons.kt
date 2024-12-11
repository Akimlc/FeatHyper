package me.xmbest.hyper.cons

import android.util.Log


/**
 * com.android.settings常量
 * @author xmbest
 * @date 2024/09/13
 */
class SettingsCons {
    companion object {

        const val TAG = "SettingsCons"
        /**
         * 编辑手机信息
         */
        const val EDIT_DEVICE_INFO = "EDIT_DEVICE_INFO"

        /**
         * 手机修改后的名称
         */
        const val EDIT_DEVICE_NAME_VALUE = "com_android_settings_device_name"

        /**
         * 设备信息键值对
         * device_cpu 处理器
         * device_memory 运行内存
         * device_battery 电池容量
         * device_screen_resolution 分辨率
         * device_screen_size 屏幕尺寸
         * device_miui_version OS版本
         * device_camera 摄像头
         *
         */
        val deviceInfoMap = mapOf(
            "处理器" to "com_android_settings_device_cpu",
            "运行内存" to "com_android_settings_device_memory",
            "电池容量" to "com_android_settings_device_battery",
            "分辨率" to "com_android_settings_device_screen_resolution",
            "屏幕尺寸" to "com_android_settings_device_screen_size",
            "OS版本" to "com_android_settings_device_miui_version",
            "摄像头" to "com_android_settings_device_camera",
        )

        /**
         * 设置设备信息可能直接调用com.android.settings.device.BaseDeviceCardItem#setValue(CharSequence)
         */
        fun getDeviceInfoMapKey(arg1: String): String {
            Log.d(TAG,"getDeviceInfoMapKey arg1 = $arg1")
            if (arg1.contains("+") && arg1.contains("GB")) {
                return "运行内存"
            } else if (arg1.contains("mAh")) {
                return "电池容量"
            } else if (arg1.contains("*")) {
                return "分辨率"
            } else if (arg1.contains("″")) {
                return "屏幕尺寸"
            } else if (arg1.contains("平台")||arg1.contains("高通")||arg1.contains("天玑")||arg1.contains("骁龙")) {
                return "处理器"
            } else if (arg1.contains("MP")) {
                return "摄像头"
            }
            return ""
        }

        /**
         * 手机设备名称
         */
        val deviceName = Pair(EDIT_DEVICE_NAME_VALUE, "设备名称")


    }
}