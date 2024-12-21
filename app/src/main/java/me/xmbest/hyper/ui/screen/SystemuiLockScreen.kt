package me.xmbest.hyper.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import me.xmbest.hyper.R
import me.xmbest.hyper.vm.SystemuiLockViewModule
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.extra.SuperSwitch

@Composable
fun SystemuiLockScreen(
    navController: NavHostController,
    viewModel: SystemuiLockViewModule = viewModel()
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = stringResource(R.string.system_systemui)
            )
        }
    ){ padding ->
        Card(
            modifier = Modifier
                .padding(12.dp)
                .padding(padding)
        ){
            SuperSwitch(
                title = stringResource(R.string.system_systemui_enable_lock_show_sim),
                checked = viewModel.enableLockShowSimName.value,
                onCheckedChange = {
                    viewModel.updateLockShowSimName(it)
                }
            )
        }
    }
}

@Composable
fun SystemuiLockScreenForMiuix(
    navController: NavHostController,
    viewModel: SystemuiLockViewModule = viewModel()
) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = stringResource(R.string.system_systemui)
            )
        }
    ){
        Card(
            modifier = Modifier
                .padding(12.dp)
        ){
            SuperSwitch(
                title = stringResource(R.string.system_systemui_enable_lock_show_sim),
                checked = viewModel.enableLockShowSimName.value,
                onCheckedChange = {
                    viewModel.updateLockShowSimName(it)
                }
            )
        }
    }
}