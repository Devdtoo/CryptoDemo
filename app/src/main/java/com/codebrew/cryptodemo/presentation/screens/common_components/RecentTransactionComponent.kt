package com.codebrew.cryptodemo.presentation.screens.common_components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.codebrew.cryptodemo.data.model.response.AllTransaction
import com.codebrew.cryptodemo.presentation.ui.theme.Typography

@Composable
fun RecentTransactionComponent(index: Int, data: AllTransaction) {

    ConstraintLayout(
        modifier = Modifier
            .wrapContentHeight()
            .fillMaxWidth()
    ) {

        val (itemConstraintLayout) = createRefs()
        val guidelineParentFourPercentStart = createGuidelineFromStart(0.04f)
        val guidelineParentFourPercentEnd = createGuidelineFromEnd(0.04f)

        ConstraintLayout(
            modifier = Modifier
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

            val guidelineFortyFivePercentEnd = createGuidelineFromEnd(0.45f)

            val painter =
                rememberAsyncImagePainter(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(data.txn_logo)
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
                        start.linkTo(parent.start)
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
                Text(
                    text = data.txn_time,
                    modifier = Modifier.wrapContentHeight(),
                    textAlign = TextAlign.Start,
                    color = Color.Gray,
                    style = Typography.body2
                )
            }

            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .constrainAs(priceColumn)
                    {
                        start.linkTo(guidelineFortyFivePercentEnd, margin = 4.dp)
                        end.linkTo(parent.end)
                        top.linkTo(titleColumn.top)
                        bottom.linkTo(titleColumn.bottom)
                        height = Dimension.wrapContent
                        width = Dimension.fillToConstraints
                    },
                horizontalAlignment = Alignment.End
            ) {
                Text(
                    text = "$"+data.txn_amount,
                    modifier = Modifier
                        .wrapContentHeight()
                        .padding(bottom = 5.dp),
                    textAlign = TextAlign.End,
                    color = if(index==0)Color.Black else Color.Green,
                    style = Typography.body1
                )
                Text(
                    text = data.txn_sub_amount,
                    modifier = Modifier.wrapContentHeight(),
                    textAlign = TextAlign.End,
                    color = Color.Gray,
                    style = Typography.body2
                )
            }

        }


    }

}