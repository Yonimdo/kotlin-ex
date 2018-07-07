package com.mdo.yoni.eshop

import android.content.Context
import org.json.JSONArray
import com.google.gson.GsonBuilder
import android.util.Log
import android.widget.Button
import com.mdo.yoni.eshop.data.internal.EShopDatabase
import com.mdo.yoni.eshop.data.models.Item
import java.io.IOException
import java.io.InputStream
import java.nio.charset.Charset


private val TAG = "Utils"


private fun loadJSONFromAsset(context: Context, jsonFileName: String): String? {
    var json: String? = null
    var `is`: InputStream? = null
    try {
        val manager = context.assets
        Log.d(TAG, "path $jsonFileName")
        `is` = manager.open(jsonFileName)
        val size = `is`!!.available()
        val buffer = ByteArray(size)
        `is`.read(buffer)
        `is`.close()
        json = String(buffer, Charset.defaultCharset())
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    }

    return json
}

fun loadItems(context: Context): List<Item>? {
    val itemList = ArrayList<Item>()
    val list = EShopDatabase.getInstance(context!!)?.itemsDao()?.getAll().associate { it.id to it }
    try {
        val builder = GsonBuilder()
        val gson = builder.create()

//TODO : relace this stub with actual data.
        val array = JSONArray(loadJSONFromAsset(context, "items.json"))
        for (i in 0 until array.length()) {
            val item = gson.fromJson<Item>(array.getString(i), Item::class.java)
//TODO ------------------------------------
//       NOT TODO : don't replace this tho
            if (list.containsKey(item.id)) {
                val dbItm: Item = list.get(item.id)!!
                item.incart = dbItm.incart
                item.incompare = dbItm.incompare
                item.discarded = dbItm.discarded
            }
            if (item.discarded == 0 ||item.discarded == null) {
                itemList.add(item)
            }
        }
    } catch (e: Exception) {
        e.printStackTrace()
        return null
    }

    return itemList
}


