package org.hakandindis.financeapp.scene.crypto

import org.hakandindis.financeapp.data.remote.model.Coin
import org.hakandindis.financeapp.data.remote.model.Quote
import org.hakandindis.financeapp.data.remote.model.USD

val MOCK_COIN_LIST = listOf(
    Coin(id = 1, name = "KAAN", quote = Quote(uSD = USD(price = 10.0)), symbol = "BTC"),
    Coin(id = 2, name = "HAKAN", quote = Quote(uSD = USD(price = 20.0)), symbol = "HTC"),
    Coin(id = 3, name = "BATUHAN", quote = Quote(uSD = USD(price = 30.0)), symbol = "ETC"),
    Coin(id = 4, name = "MEHMET", quote = Quote(uSD = USD(price = 40.0)), symbol = "TC"),
    Coin(id = 5, name = "EFE", quote = Quote(uSD = USD(price = 50.0)), symbol = "ETH"),
    Coin(id = 6, name = "AHMET", quote = Quote(uSD = USD(price = 60.0)), symbol = "HKN"),
    Coin(id = 7, name = "HASAN", quote = Quote(uSD = USD(price = 70.0)), symbol = "BTN"),
    Coin(id = 8, name = "", quote = Quote(uSD = USD(price = 80.0)), symbol = "KNN"),
    Coin(id = 9, name = "", quote = Quote(uSD = USD(price = 90.0)), symbol = "QWE"),
    Coin(id = 10, name = "", quote = Quote(uSD = USD(price = 100.0)), symbol = "TYU"),
    Coin(id = 11, name = "", quote = Quote(uSD = USD(price = 110.0)), symbol = "ASD"),
    Coin(id = 12, name = "", quote = Quote(uSD = USD(price = 120.0)), symbol = "234"),
    Coin(id = 13, name = "", quote = Quote(uSD = USD(price = 130.0)), symbol = "567"),
    Coin(id = 14, name = "", quote = Quote(uSD = USD(price = 140.0)), symbol = "ASD"),
    Coin(id = 15, name = "", quote = Quote(uSD = USD(price = 150.0)), symbol = "NMK"),
    Coin(id = 16, name = "", quote = Quote(uSD = USD(price = 160.0)), symbol = "UME"),
    Coin(id = 17, name = "", quote = Quote(uSD = USD(price = 170.0)), symbol = "ZPY"),
    Coin(id = 18, name = "", quote = Quote(uSD = USD(price = 180.0)), symbol = "NTG"),
    Coin(id = 19, name = "", quote = Quote(uSD = USD(price = 190.0)), symbol = "VIR"),
    Coin(id = 20, name = "", quote = Quote(uSD = USD(price = 200.0)), symbol = "WCP"),
)