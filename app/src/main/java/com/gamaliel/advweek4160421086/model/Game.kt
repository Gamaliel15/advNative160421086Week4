package com.gamaliel.advweek4160421086.model

data class Game(
    val id:String?,
    val title:String?,
    val genre:String?,
    val platforms:List<String>?,
    val developer: Developer?,
    val images:String?
)
