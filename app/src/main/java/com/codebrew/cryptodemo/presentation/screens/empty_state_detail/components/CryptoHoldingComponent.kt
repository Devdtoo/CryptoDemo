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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.codebrew.cryptodemo.data.model.response.YourCryptoHolding
import com.codebrew.cryptodemo.presentation.ui.theme.Typography

@Composable
fun CryptoHoldingComponent(index: Int, size: Int, data: YourCryptoHolding) {
    val modifier: Modifier
    when (index) {
        0 -> {
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(topStart = 15.dp, topEnd = 15.dp)
            )
        }
        size - 1 -> {
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(bottomStart = 15.dp, bottomEnd = 15.dp)
            )

        }
        else -> {
            modifier = Modifier.border(
                width = 1.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(0.dp)
            )
        }
    }

    ConstraintLayout(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {

        val (itemConstraintLayout) = createRefs()
        val guidelineParentFourPercentStart = createGuidelineFromStart(0.04f)
        val guidelineParentFourPercentEnd = createGuidelineFromEnd(0.04f)

        ConstraintLayout(
            modifier = modifier
                .height(70.dp)
                .constrainAs(itemConstraintLayout) {
                    top.linkTo(parent.top)
                    start.linkTo(guidelineParentFourPercentStart)
                    end.linkTo(guidelineParentFourPercentEnd)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.fillToConstraints
                }
        ) {
            // Create references for the composables to constrain
            val (logoIv, titleTxt, depositBtn, buyBtn) = createRefs()
            val painter =
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.logo)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                )
            val guidelineFourPercentStart = createGuidelineFromStart(0.04f)
            val guidelineFiftyPercent = createGuidelineFromStart(0.5f)
            val guidelineTwentySevenPercentEnd = createGuidelineFromEnd(0.27f)
            val guidelineFourPercentEnd = createGuidelineFromEnd(0.04f)

            Image(
                painter = painter,
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

            Text(
                text = data.title,
                textAlign = TextAlign.Start,
                color = Color.Black,
                style = Typography.h4,
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(bottom = 5.dp)
                    .constrainAs(titleTxt)
                    {
                        top.linkTo(logoIv.top)
                        start.linkTo(logoIv.end, margin = 8.dp)
                        bottom.linkTo(logoIv.bottom)
                        end.linkTo(guidelineFiftyPercent)
                        width = Dimension.fillToConstraints
                    }

            )

            Button(
                onClick = { /* Do something */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
                border = BorderStroke(width = 1.dp, color = Color.Blue),
                modifier = Modifier
                    .constrainAs(depositBtn) {
                        start.linkTo(guidelineFiftyPercent)
                        end.linkTo(guidelineTwentySevenPercentEnd, margin = 4.dp)
                        top.linkTo(logoIv.top)
                        bottom.linkTo(logoIv.bottom)
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints
                    }
            ) {
                Text(
                    text = "Deposit",
                    color = Color.Blue,
                    style = Typography.body2
                )
            }
            Button(
                onClick = { /* Do something */ },
                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Blue),
                modifier = Modifier
                    .constrainAs(buyBtn) {
                        start.linkTo(guidelineTwentySevenPercentEnd, margin = 4.dp)
                        end.linkTo(guidelineFourPercentEnd)
                        top.linkTo(depositBtn.top)
                        bottom.linkTo(depositBtn.bottom)
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints
                    }
            ) {
                Text(
                    text = "Buy",
                    color = Color.White,
                    style = Typography.body2
                )
            }
        }


    }

}