package com.mdo.yoni.eshop.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "items")
class Item {

    @NonNull
    @PrimaryKey()
    @SerializedName("_id")
    @Expose
    var id: String? = null

    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("keywords")
    @Expose
    var keywords: String? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("price")
    @Expose
    var price: Int? = null

    @SerializedName("incart")
    @Expose
    var incart: Int? = null

    @SerializedName("incompare")
    @Expose
    var incompare: Int? = null

    @SerializedName("discarded")
    @Expose
    var discarded: Int? = null
}