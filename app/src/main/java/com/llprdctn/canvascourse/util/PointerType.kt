package com.llprdctn.canvascourse.util

sealed class PointerType{
    object Second: PointerType()
    object Minute: PointerType()
    object Hour: PointerType()
}
