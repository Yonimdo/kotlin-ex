package com.mdo.yoni.eshop.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Item {

    @SerializedName("_id")
    @Expose
    var id: String? = null


    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("url")
    @Expose
    var imageUrl: String? = null

    @SerializedName("age")
    @Expose
    var age: Int? = null

    @SerializedName("location")
    @Expose
    var location: String? = null
}