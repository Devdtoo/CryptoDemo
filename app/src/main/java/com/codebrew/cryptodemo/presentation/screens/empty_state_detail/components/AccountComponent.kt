package com.codebrew.cryptodemo.presentation.screens.empty_state_detail.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.codebrew.cryptodemo.R
import com.codebrew.cryptodemo.data.model.response.CryptoBalance
import com.codebrew.cryptodemo.presentation.ui.theme.Typography

@Composable
fun AccountComponent(data: CryptoBalance, modifier: Modifier) {
    ConstraintLayout(
        modifier = modifier
            .border(
                width = 2.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(12.dp)
            )
            .height(70.dp)
    ) {
        // Create references for the composables to constrain
        val (logoIv, titleColumn, button) = createRefs()

        val guidelineThirtyPercentEnd = createGuidelineFromEnd(0.3f)
        val guidelineFourPercentStart = createGuidelineFromStart(0.04f)
        val guidelineFourPercentEnd = createGuidelineFromEnd(0.04f)
        Image(
            painter = painterResource(id = R.drawable.ic_crypto_account),
            contentDescription = null,
            modifier = Modifier
                .size(50.dp)
                .height(50.dp)
                .width(50.dp)
                .constrainAs(logoIv) {
                    start.linkTo(guidelineFourPercentStart)
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                }
        )
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .constrainAs(titleColumn)
                {
                    top.linkTo(logoIv.top)
                    start.linkTo(logoIv.end, margin = 8.dp)
                    bottom.linkTo(logoIv.bottom)
                    end.linkTo(guidelineThirtyPercentEnd)
                    width = Dimension.fillToConstraints
                },
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = data.title,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(bottom = 5.dp),
                textAlign = TextAlign.Start,
                color = Color.Black,
                style = Typography.h4
            )
            Text(
                text = data.subtitle,
                modifier = Modifier.wrapContentHeight(),
                textAlign = TextAlign.Start,
                color = Color.Gray,
                style = Typography.body2
            )
        }

        Button(
            onClick = { /* Do something */ },
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
            border = BorderStroke(width = 1.dp, color = Color.Blue),
            modifier = Modifier
                .constrainAs(button) {
                    start.linkTo(guidelineThirtyPercentEnd)
                    end.linkTo(guidelineFourPercentEnd)
                    top.linkTo(logoIv.top)
                    bottom.linkTo(logoIv.bottom)
                    height = Dimension.wrapContent
                    width = Dimension.wrapContent
                }
        ) {
            Text(
                text = "Deposit",
                color = Color.Blue,
                style = Typography.body2
            )
        }
    }

}
