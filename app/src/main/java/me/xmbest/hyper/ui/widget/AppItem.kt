package me.xmbest.hyper.ui.widget

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.graphics.drawable.toBitmap
import androidx.navigation.NavHostController
import me.xmbest.hyper.R
import me.xmbest.hyper.utils.AppUtils
import top.yukonga.miuix.kmp.basic.Card
import top.yukonga.miuix.kmp.extra.SuperArrow


private val TAG = "AppItem"

/**
 * 应用列表UI
 * @author xmbest
 * @date 2024/09/23
 * @param packageName 应用包名
 * @param iconSize 图标大小
 */
@Composable
fun AppItem(navController: NavHostController?, packageName: String, iconSize: Int = 40) {
    val app = AppUtils.getApplicationNameAndIcon(packageName)   // 应用名和图标
    Log.d(TAG, "AppItem: $app")

    app?.let {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 12.dp)
                .padding(bottom = 12.dp)
        ) {
            SuperArrow(
                title = app.first,
                summary = packageName,
                leftAction = {
                    Box(
                        contentAlignment = Alignment.TopStart,
                        modifier = Modifier.padding(
                            end = 16.dp
                        )
                    ) {
                        if (null == app.second) {
                            Image(
                                painter = painterResource(R.mipmap.ic_launcher_round),
                                contentDescription = "${app.first} icon",
                                modifier = Modifier.size(iconSize.dp)
                            )
                        } else {
                            Image(
                                bitmap = app.second?.toBitmap()!!.asImageBitmap(),
                                contentDescription = "${app.first} icon",
                                modifier = Modifier.size(iconSize.dp)
                            )
                        }
                    }
                },
                onClick = {
                    navController?.let {
                        navController.navigate(packageName)
                    }
                }
            )
        }
    }
}