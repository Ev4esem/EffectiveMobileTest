package com.dagteam.domain.models

import androidx.annotation.StringRes
import com.dagteam.domain.R

enum class SortedType(
    @StringRes val titleRes: Int
) {
    DESCENDING_ORDER(R.string.descending_order),
    ADDING(R.string.adding),
}
