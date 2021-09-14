package com.example.ecommercie_1

import com.airbnb.epoxy.EpoxyAsyncUtil
import com.airbnb.epoxy.TypedEpoxyController

class FavoriteListEpoxyController : TypedEpoxyController<List<FavoriteOrder>>(
    EpoxyAsyncUtil.getAsyncBackgroundHandler(),
    EpoxyAsyncUtil.getAsyncBackgroundHandler()
) {
    override fun buildModels(data: List<FavoriteOrder>?) {
        data?.forEachIndexed { index, favoriteOrder ->
            favoriteCard {
                id(index)
                favoriteOrder(favoriteOrder)
            }
        }
    }
}