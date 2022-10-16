package com.codebrew.cryptodemo.presentation.screens.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.rememberAsyncImagePainter
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import com.codebrew.cryptodemo.R
import com.codebrew.cryptodemo.data.model.response.CryptoPrice
import com.codebrew.cryptodemo.presentation.ui.theme.Typography

@Composable
fun CurrentPriceComponent(index: Int, data: CryptoPrice) {

    ConstraintLayout(
        modifier = Modifier
            .height(150.dp)
            .width(150.dp)
            .padding(start = 10.dp, end = 5.dp)
    ) {

        val (itemConstraintLayout, downloadBtn, buyBtn) = createRefs()

        ConstraintLayout(
            modifier = Modifier
                .border(
                    width = 1.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(8.dp)
                )
                .constrainAs(itemConstraintLayout) {
                    top.linkTo(parent.top, margin = 4.dp)
                    start.linkTo(parent.start, margin = 4.dp)
                    end.linkTo(parent.end, margin = 4.dp)
                    bottom.linkTo(parent.bottom, margin = 4.dp)
                    width = Dimension.fillToConstraints
                    height = Dimension.fillToConstraints
                }
        ) {
            // Create references for the composables to constrain
            val (logoIv, titleColumn, graphImg) = createRefs()
            val painter =
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.logo)
                        .decoderFactory(SvgDecoder.Factory())
                        .build()
                )

            val guidelineFifteenPercentBottom = createGuidelineFromBottom(0.15f)
            val guidelineFortyPercentBottom = createGuidelineFromBottom(0.40f)
            val guidelineFivePercentStart = createGuidelineFromStart(0.05f)

            Image(
                painter = painter,
                contentDescription = null,
                modifier = Modifier
                    .size(50.dp)
                    .height(50.dp)
                    .width(50.dp)
                    .constrainAs(logoIv) {
                        start.linkTo(guidelineFivePercentStart)
                        top.linkTo(parent.top)
                        bottom.linkTo(guidelineFortyPercentBottom)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_price_graph),
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier
                    .size(50.dp)
                    .height(50.dp)
                    .constrainAs(graphImg) {
                        start.linkTo(logoIv.end)
                        top.linkTo(logoIv.top)
                        bottom.linkTo(logoIv.bottom)
                        end.linkTo(parent.end, margin = 10.dp)
                        width = Dimension.fillToConstraints
                    }
            )

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(titleColumn)
                    {
                        start.linkTo(guidelineFivePercentStart)
                        top.linkTo(guidelineFortyPercentBottom)
                        bottom.linkTo(guidelineFifteenPercentBottom)
                        end.linkTo(parent.end, margin = 10.dp)
                        width = Dimension.fillToConstraints
                    },
                horizontalAlignment = Alignment.Start
            ) {
                Text(
                    text = data.title,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(bottom = 2.dp),
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                    style = Typography.body2
                )
                Text(
                    text = "$"+ data.current_price_in_usd,
                    modifier = Modifier.wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    color = Color.Black,
                    style = Typography.h3
                )
            }


        }

        Box(
            modifier = Modifier
                .height(25.dp)
                .width(25.dp)
                .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(4.dp))
                .background(color = Color.White)
                .constrainAs(downloadBtn) {
                    top.linkTo(buyBtn.top)
                    bottom.linkTo(buyBtn.bottom)
                    end.linkTo(buyBtn.start, margin = 4.dp)
                }
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_download),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)
                    .align(Alignment.Center)
            )
        }

        Box(
            modifier = Modifier
                .height(25.dp)
                .width(45.dp)
                .border(width = 1.dp, color = Color.Blue, shape = RoundedCornerShape(4.dp))
                .background(color = Color.White)
                .constrainAs(buyBtn) {
                    end.linkTo(parent.end)
                    top.linkTo(parent.top)
                }
        ) {
            Text(
                text = "Buy",
                color = Color.Blue,
                style = Typography.body2,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .wrapContentHeight()
                    .matchParentSize()
            )
        }


    }

}