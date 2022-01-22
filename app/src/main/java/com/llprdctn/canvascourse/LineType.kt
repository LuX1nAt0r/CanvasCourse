package com.llprdctn.canvascourse

sealed class LineType {
    object Normal: LineType()
    object FiveStep: LineType()
    object TenStep: LineType()
}
