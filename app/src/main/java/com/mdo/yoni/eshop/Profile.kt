package com.mdo.yoni.eshop

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Profile {

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