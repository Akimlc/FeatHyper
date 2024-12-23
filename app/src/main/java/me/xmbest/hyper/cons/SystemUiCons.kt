package me.xmbest.hyper.cons

/**
 * com.android.systemui常量
 * @author xmbest
 * @date 2024/09/13
 */
class SystemUiCons {
    companion object{
        /**
         * 锁屏显示运营商名称
         */
        const val LOCK_SHOW_SIM_NAME = "LOCK_SHOW_SIM_NAME"

        /**
         * 锁屏通知下沉
         */
        const val LOCK_NOTIFICATION_SINK = "LOCK_NOTIFICATION_SINK"
        /**
         * 锁屏通知下沉高度
         */
        const val LOCK_NOTIFICATION_SINK_PROGRESS = "LOCK_NOTIFICATION_SINK_PROGRESS"
        /**
         * 去除锁屏第一行信息
         */
        const val REMOVE_LOCK_FIRST_INFO = "REMOVE_LOCK_FIRST_INFO"
    }
}