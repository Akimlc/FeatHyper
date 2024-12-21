package me.xmbest.hyper.ui.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import me.xmbest.hyper.R
import me.xmbest.hyper.ui.widget.AppItem
import me.xmbest.hyper.vm.HomeViewModule
import top.yukonga.miuix.kmp.basic.Scaffold
import top.yukonga.miuix.kmp.basic.SmallTitle
import top.yukonga.miuix.kmp.basic.TopAppBar

/**
 * 首页
 */
@Composable
fun HomeScreen(navController: NavHostController, viewModule: HomeViewModule = viewModel()) {
    Scaffold (
        topBar = {
            TopAppBar(
                title = stringResource(R.string.app_list),
            )
        }
    ){ padd->
        LazyColumn(
            modifier = Modifier
                .padding(padd)
                .padding(top = 6.dp)
        ) {
            items(viewModule.getPackageNameList()) {
                AppItem(navController, it)
            }
        }
    }

}