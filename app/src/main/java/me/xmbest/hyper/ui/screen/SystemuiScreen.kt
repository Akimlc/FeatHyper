package me.xmbest.hyper.ui.screen

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import me.xmbest.hyper.R
import me.xmbest.hyper.vm.SystemuiLockViewModule
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Slider
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.Text
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.ArrowBack
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getWindowSize

@Composable
fun SystemuiScreen(navController: NavHostController, viewModel: SystemuiLockViewModule) {
    val scrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())
    val enableSimSwitch = remember { mutableStateOf(viewModel.enableLockShowSimName.value) }
    val enableLockFirstInfoSwitch = remember { mutableStateOf(viewModel.enableLockFirstInfo.value) }
    val enableLockNotificationSinkSwitch = remember { mutableStateOf(viewModel.enableLockNotificationSink.value) }
    val notificationSinkProgress by remember { mutableStateOf(viewModel.notificationSinkProgress) }
    val enableLockTimeFontSwitch = remember { mutableStateOf(viewModel.enableLockTimeFont.value) }

    Column {
        TopAppBar(
            title = stringResource(R.string.system_systemui),
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                IconButton(
                    modifier = Modifier.padding(start = 18.dp),
                    onClick = {
                        navController.popBackStack()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(40.dp),
                        imageVector = MiuixIcons.ArrowBack,
                        contentDescription = "Back",
                        tint = MiuixTheme.colorScheme.onBackground
                    )
                }
            }
        )
        LazyColumn(
            modifier = Modifier
                .height(getWindowSize().height.dp)
                .background(MiuixTheme.colorScheme.background),
            isEnabledOverScroll = { true },
            topAppBarScrollBehavior = scrollBehavior
        ) {
            item {
                Column(
                    modifier = Modifier
                        .padding(top = 6.dp)
                ) {
                    SmallTitle(text = stringResource(R.string.systemui_lock))
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        SuperSwitch(
                            title = stringResource(R.string.system_systemui_enable_lock_show_sim),
                            checked = viewModel.enableLockShowSimName.value,
                            onCheckedChange = {
                                enableSimSwitch.value = it
                                viewModel.updateLockShowSimName(it)
                            }
                        )
                        //去除锁屏信息第一行
                        SuperSwitch(
                            title = "去除锁屏信息第一行",
                            checked = enableLockFirstInfoSwitch.value,
                            onCheckedChange = {
                                enableLockFirstInfoSwitch.value = it
                                viewModel.updateLockFirstInfo(it)
                            }
                        )
                        //修改锁屏时钟字体
                        SuperSwitch(
                            title = "修改锁屏时钟字体",
                            checked = enableLockTimeFontSwitch.value,
                            onCheckedChange = {
                                enableLockTimeFontSwitch.value = it
                                viewModel.updateLockFont(it)
                            }
                        )


                    }
                    SmallTitle(text = "通知")
                    //锁屏通知下沉
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp, vertical = 6.dp)
                    ) {
                        SuperSwitch(
                            title = "锁屏通知下沉",
                            checked = enableLockNotificationSinkSwitch.value,
                            onCheckedChange = {
                                enableLockNotificationSinkSwitch.value = it
                                viewModel.enableLockNotificationSink(it)
                            }
                        )
                        AnimatedVisibility(enableLockNotificationSinkSwitch.value) {
                            Column {
                                Row(
                                    horizontalArrangement = Arrangement.SpaceBetween, // 让文本与数值显示左右对齐
                                    verticalAlignment = Alignment.CenterVertically,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 15.dp)
                                ) {
                                    Text(
                                        text = "通知下沉高度",
                                        modifier = Modifier.padding(bottom = 12.dp),
                                        fontSize = 14.sp
                                    )
                                    Text(
                                        text = notificationSinkProgress.value.toString(),
                                        modifier = Modifier.padding(bottom = 12.dp),
                                        textAlign = TextAlign.End,
                                        fontSize = 14.sp
                                    )
                                }
                                Slider(
                                    modifier = Modifier
                                        .padding(horizontal = 12.dp)
                                        .padding(bottom = 12.dp),
                                    progress = notificationSinkProgress.value.toFloat(),
                                    onProgressChange = { newProgress ->
                                        notificationSinkProgress.value = newProgress.toInt()
                                        viewModel.updateNotificationSinkProgress(newProgress.toInt())
                                    },
                                    minValue = 0f,
                                    maxValue = 2000f,
                                )
                            }

                        }
                    }

                }
            }
        }
    }
}