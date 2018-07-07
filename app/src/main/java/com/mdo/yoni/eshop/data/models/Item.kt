package com.mdo.yoni.eshop.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcel
import android.os.Parcelable
import android.support.annotation.NonNull
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

@Entity(tableName = "items")
class Item() : Parcelable {

    @NonNull
    @PrimaryKey()
    @SerializedName("_id")
    @Expose
    var id: String = UUID.randomUUID().toString()

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

    @NonNull
    @SerializedName("incart")
    @Expose
    var incart: Int = 0

    @NonNull
    @SerializedName("incompare")
    @Expose
    var incompare: Int = 0

    @NonNull
    @SerializedName("discarded")
    @Expose
    var discarded: Int = 0

    constructor(parcel: Parcel) : this() {
        id = parcel.readString()
        name = parcel.readString()
        keywords = parcel.readString()
        url = parcel.readString()
        price = parcel.readValue(Int::class.java.classLoader) as? Int
        incart = parcel.readValue(Int::class.java.classLoader) as Int
        incompare = parcel.readValue(Int::class.java.classLoader) as Int
        discarded = parcel.readValue(Int::class.java.classLoader) as Int
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeString(keywords)
        parcel.writeString(url)
        parcel.writeValue(price)
        parcel.writeValue(incart)
        parcel.writeValue(incompare)
        parcel.writeValue(discarded)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}