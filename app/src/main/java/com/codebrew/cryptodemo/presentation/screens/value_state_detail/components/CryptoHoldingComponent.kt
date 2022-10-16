package com.codebrew.cryptodemo.presentation.screens.value_state_detail.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
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
            val (logoIv, titleColumn, priceColumn) = createRefs()

            val guidelineFourPercentStart = createGuidelineFromStart(0.04f)
            val guidelineFourPercentEnd = createGuidelineFromEnd(0.04f)
            val guidelineFortyFivePercentEnd = createGuidelineFromEnd(0.45f)

            val painter =
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.logo)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                )

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

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(titleColumn)
                    {
                        top.linkTo(logoIv.top)
                        start.linkTo(logoIv.end, margin = 8.dp)
                        bottom.linkTo(logoIv.bottom)
                        end.linkTo(guidelineFortyFivePercentEnd)
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
            }

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(priceColumn)
                    {
                        start.linkTo(guidelineFortyFivePercentEnd, margin = 4.dp)
                        end.linkTo(guidelineFourPercentEnd)
                        top.linkTo(titleColumn.top)
                        bottom.linkTo(titleColumn.bottom)
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints
                    },
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$"+data.current_bal_in_usd,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(bottom = 5.dp),
                    textAlign = TextAlign.End,
                    color = Color.Black,
                    style = Typography.body1
                )
                Text(
                    text = data.current_bal_in_token,
                    modifier = Modifier.wrapContentHeight(),
                    textAlign = TextAlign.End,
                    color = Color.Green,
                    style = Typography.body2
                )
            }


        }


    }

}