package me.xmbest.hyper.ui.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TextField
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.extra.SuperSwitch


@Composable
fun SettingsScreen(navController: NavHostController?) {

    val deviceSwitch = remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopAppBar(title = stringResource(R.string.system_settings))
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 12.dp, vertical = 6.dp)
            ) {
                SuperSwitch(
                    title = "设备编辑",
                    checked = deviceSwitch.value,
                    onCheckedChange = {
                        deviceSwitch.value = it
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


@Preview
@Composable
fun PreviewComp() {
    SettingsScreen(rememberNavController())
}