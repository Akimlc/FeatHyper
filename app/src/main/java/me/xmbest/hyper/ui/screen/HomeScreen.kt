package me.xmbest.hyper.ui.screen

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import me.xmbest.hyper.R
import me.xmbest.hyper.ui.widget.AppItem
import me.xmbest.hyper.vm.HomeViewModule
import top.yukonga.miuix.kmp.basic.LazyColumn
import top.yukonga.miuix.kmp.basic.MiuixScrollBehavior
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.TopAppBar
import top.yukonga.miuix.kmp.basic.rememberTopAppBarState
import top.yukonga.miuix.kmp.utils.getWindowSize

/**
 * 首页
 */
@Composable
fun HomeScreen(navController: NavHostController, viewModule: HomeViewModule = viewModel()) {
    val scrollBehavior = MiuixScrollBehavior(rememberTopAppBarState())
    Scaffold(
        topBar = {
            TopAppBar(
                title = stringResource(R.string.app_list),
                scrollBehavior = scrollBehavior
            )
        }
    ) { PaddingValues ->
        LazyColumn(
            modifier = Modifier
                .height(getWindowSize().height.dp)
                .padding(top = 12.dp)
                .fillMaxSize(),
            contentPadding = PaddingValues,
            topAppBarScrollBehavior = scrollBehavior
        ) {
            items(viewModule.getPackageNameList()) {
                AppItem(navController, it)
            }
        }
    }
}