package me.xmbest.hyper.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import me.xmbest.hyper.R
import me.xmbest.hyper.cons.SettingsCons
import me.xmbest.hyper.utils.SPUtils
import me.xmbest.hyper.vm.SettingsDeviceInfoViewModule
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Icon
import top.yukonga.miuix.kmp.basic.IconButton
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.icon.MiuixIcons
import top.yukonga.miuix.kmp.icon.icons.ArrowBack
import top.yukonga.miuix.kmp.theme.MiuixTheme
import top.yukonga.miuix.kmp.utils.getWindowSize


@Composable
fun SettingsScreen(navController: NavHostController?,viewModel: SettingsDeviceInfoViewModule) {
    val deviceSwitch = remember { mutableStateOf(viewModel.enable.value) }
    val scrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())

    Column {
        TopAppBar(
            title = stringResource(R.string.system_settings),
            scrollBehavior = scrollBehavior,
            navigationIcon = {
                IconButton(
                    modifier = Modifier.padding(start = 18.dp),
                    onClick = {
                        navController?.popBackStack()
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
                Spacer(modifier = Modifier.padding(top = 12.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 12.dp, vertical = 6.dp)
                ) {
                    SuperSwitch(
                        title = "设备编辑",
                        checked = viewModel.enable.value,
                        onCheckedChange = {
                            deviceSwitch.value = it
                            viewModel.updateDeviceEditState(it)
                        }
                    )
                    AnimatedVisibility(deviceSwitch.value) {
                        Column {
                            SettingsCons.deviceInfoMap.forEach { map ->
                                var currentValue by rememberSaveable {
                                    mutableStateOf(
                                        SPUtils.getString(
                                            map.value,
                                            ""
                                        ) as String
                                    )
                                }
                                TextField(
                                    value = currentValue,
                                    onValueChange = {
                                        currentValue = it
                                        SPUtils.setString(map.value, it)
                                    },
                                    label = map.key,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}


@Preview
@Composable
fun PreviewComp() {
    SettingsScreen(rememberNavController(),viewModel = SettingsDeviceInfoViewModule())
}