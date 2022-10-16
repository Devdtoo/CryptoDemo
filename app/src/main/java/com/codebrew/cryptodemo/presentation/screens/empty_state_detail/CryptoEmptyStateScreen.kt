package com.codebrew.cryptodemo.presentation.screens.empty_state_detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import com.codebrew.cryptodemo.presentation.screens.empty_state_detail.components.AccountComponent
import com.codebrew.cryptodemo.presentation.screens.empty_state_detail.components.CryptoHoldingComponent
import com.codebrew.cryptodemo.presentation.screens.common_components.CurrentPriceComponent
import com.codebrew.cryptodemo.presentation.screens.common_components.RecentTransactionComponent
import com.codebrew.cryptodemo.presentation.screens.empty_state_detail.viewmodel.CryptoEmptyViewModel
import com.codebrew.cryptodemo.presentation.ui.theme.Typography


@Composable
fun CryptoEmptyStateScreen(
    viewModel: CryptoEmptyViewModel = hiltViewModel()
) {
    val state = viewModel.state.value

    Box(modifier = Modifier.fillMaxSize()) {
        state.cryptoData?.let { cryptoResponse ->

            LazyColumn(
                modifier = Modifier.fillMaxWidth().wrapContentHeight()
            ) {

                item {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        val (
                            accountComp,
                            spacer1,
                            spacer2,
                            spacer3,
                            cryptoHoldingTitleTxt,
                            spacer4
                        ) = createRefs()

                        AccountComponent(data = cryptoResponse.crypto_balance, modifier = Modifier
                            .constrainAs(accountComp) {
                                start.linkTo(parent.start, margin = 10.dp)
                                top.linkTo(parent.top, margin = 50.dp)
                                end.linkTo(parent.end, margin = 10.dp)
                                width = Dimension.fillToConstraints
                            })
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .constrainAs(spacer1) {
                                    top.linkTo(accountComp.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                                .background(Color.LightGray)
                                .constrainAs(spacer2) {
                                    top.linkTo(spacer1.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(25.dp)
                                .constrainAs(spacer3) {
                                    top.linkTo(spacer2.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                        Text(
                            text = "Your Crypto Holdings",
                            color = Color.Black,
                            style = Typography.h4,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .constrainAs(cryptoHoldingTitleTxt) {
                                    top.linkTo(spacer3.bottom)
                                    start.linkTo(parent.start, margin = 15.dp)
                                }
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(15.dp)
                                .constrainAs(spacer4) {
                                    top.linkTo(cryptoHoldingTitleTxt.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }
                }
                itemsIndexed(cryptoResponse.your_crypto_holdings) { index, cryptoData ->
                    CryptoHoldingComponent(
                        index = index,
                        size = cryptoResponse.your_crypto_holdings.size,
                        data = cryptoData
                    )
                }

                item {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        val (
                            recentTranTxt,
                            viewAllTranTxt,
                            recentTranDivider,
                            spacer1
                        ) = createRefs()

                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .constrainAs(recentTranDivider) {
                                    top.linkTo(parent.top, margin = 20.dp)
                                }
                        )

                        Text(
                            text = "Recent Transactions",
                            color = Color.Black,
                            style = Typography.h4,
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .constrainAs(recentTranTxt) {
                                    top.linkTo(recentTranDivider.bottom, margin = 25.dp)
                                    start.linkTo(parent.start, margin = 15.dp)
                                }
                        )

                        Text(
                            text = "View All",
                            color = Color.Blue,
                            style = Typography.h4,
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .constrainAs(viewAllTranTxt) {
                                    top.linkTo(recentTranTxt.top)
                                    end.linkTo(parent.end, margin = 15.dp)
                                    bottom.linkTo(recentTranTxt.bottom)
                                }
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(15.dp)
                                .constrainAs(spacer1) {
                                    top.linkTo(viewAllTranTxt.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }
                }

                itemsIndexed(cryptoResponse.all_transactions.take(3)) { index, cryptoData ->
                    RecentTransactionComponent(
                        index = index,
                        data = cryptoData
                    )
                }
                item {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                    ) {
                        val (
                            currentPriceTxt,
                            currentPriceDivider,
                            spacer1
                        ) = createRefs()

                        Divider(
                            color = Color.LightGray,
                            thickness = 1.dp,
                            modifier = Modifier
                                .constrainAs(currentPriceDivider) {
                                    top.linkTo(parent.top, margin = 20.dp)
                                }
                        )

                        Text(
                            text = "Current Prices",
                            color = Color.Black,
                            style = Typography.h4,
                            modifier = Modifier
                                .wrapContentWidth()
                                .wrapContentHeight()
                                .constrainAs(currentPriceTxt) {
                                    top.linkTo(currentPriceDivider.bottom, margin = 25.dp)
                                    start.linkTo(parent.start, margin = 15.dp)
                                }
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(15.dp)
                                .constrainAs(spacer1) {
                                    top.linkTo(currentPriceTxt.bottom)
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                }
                        )
                    }
                    LazyRow(modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                    ) {
                        itemsIndexed(cryptoResponse.crypto_prices) { index, cryptoPrice ->
                            CurrentPriceComponent(index = index, data = cryptoPrice )
                        }
                    }
                    Spacer(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(15.dp)
                    )

                }


            } //LazyColumnEnd



        }



        if (state.error.isNotBlank()) {
            Text(
                text = state.error,
                color = MaterialTheme.colors.error,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .align(Alignment.Center)
            )
        }
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)

            )
        }


    }


}



