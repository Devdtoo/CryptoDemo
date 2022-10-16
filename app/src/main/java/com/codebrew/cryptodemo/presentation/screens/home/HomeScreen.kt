package com.codebrew.cryptodemo.presentation.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.codebrew.cryptodemo.presentation.utils.Screen
import com.codebrew.cryptodemo.presentation.ui.theme.Typography

@Composable
fun HomeScreen(navController: NavHostController) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (titleTxt, emptyStateBtn, valueStateBtn) = createRefs()

        val guidelineFiftyPercentHorizontalTop = createGuidelineFromTop(0.5f)
        val guidelineThirtyPercentHorizontalTop = createGuidelineFromTop(0.3f)

        Text(
            text = "Please choose an option",
            color = Color.Green,
            style = Typography.h2,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .constrainAs(titleTxt) {
                    top.linkTo(guidelineThirtyPercentHorizontalTop)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(valueStateBtn.top)
                    width = Dimension.fillToConstraints
                    height = Dimension.wrapContent
                }
        )

        Button(
            onClick = { navController.navigate(Screen.CryptoValueStateScreen.route) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
            modifier = Modifier
                .constrainAs(valueStateBtn) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(guidelineFiftyPercentHorizontalTop, margin = 2.dp)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                }
        ) {
            Text(
                text = "Value State Screen",
                color = Color.White,
                style = Typography.body2
            )
        }

        Button(
            onClick = { navController.navigate(Screen.CryptoEmptyStateScreen.route) },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(width = 1.dp, color = Color.Blue),
            modifier = Modifier
                .constrainAs(emptyStateBtn) {
                    start.linkTo(valueStateBtn.start)
                    end.linkTo(valueStateBtn.end)
                    top.linkTo(guidelineFiftyPercentHorizontalTop, margin = 2.dp)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                }
        ) {
            Text(
                text = "Empty State Screen",
                color = Color.Blue,
                style = Typography.body2
            )
        }
    }
}