package com.mdo.yoni.eshop.data.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
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
    @Ignore
    var keywords: List<String>? = null

    @SerializedName("url")
    @Expose
    var url: String? = null

    @SerializedName("price")
    @Expose
    var price: String? = null

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
        keywords = parcel.createStringArrayList()
        url = parcel.readString()
        price = parcel.readString()
        incart = parcel.readInt()
        incompare = parcel.readInt()
        discarded = parcel.readInt()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(id)
        parcel.writeString(name)
        parcel.writeStringList(keywords)
        parcel.writeString(url)
        parcel.writeValue(price)
        parcel.writeInt(incart)
        parcel.writeInt(incompare)
        parcel.writeInt(discarded)
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