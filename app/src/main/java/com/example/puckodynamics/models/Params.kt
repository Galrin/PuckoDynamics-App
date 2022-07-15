package com.example.puckodynamics.models

data class Params(
        val width: Int,
        val height: Int,
        val x :Int,
        val y :Int
)

fun Params.toString() : String{
    return "Params: width = $width, height = $height, x = $x, y = $y"
}