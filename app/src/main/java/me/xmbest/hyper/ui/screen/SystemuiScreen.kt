package me.xmbest.hyper.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import me.xmbest.hyper.R
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.extra.SuperSwitch
import top.yukonga.miuix.kmp.utils.getWindowSize

@Composable
fun SystemuiScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(R.string.system_systemui),
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
               .padding(padding)
               .padding(12.dp)
        ) {
            SmallTitle(text = stringResource(R.string.systemui_lock))
            Card(
                modifier = Modifier
                    .padding(12.dp)
            ) {
                SuperSwitch(
                    title = stringResource(R.string.system_systemui_enable_lock_show_sim),
                    checked = true,
                    onCheckedChange = {

                    }
                )
            }
        }

    }
}