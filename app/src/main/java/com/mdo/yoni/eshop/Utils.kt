package com.mdo.yoni.eshop

import android.content.Context
import org.json.JSONArray
import com.google.gson.GsonBuilder
import android.util.Log
import com.mdo.yoni.eshop.models.Item
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

    fun loadProfiles(context: Context): List<Item>? {
        try {
            val builder = GsonBuilder()
            val gson = builder.create()
            val array = JSONArray(loadJSONFromAsset(context, "items.json"))
            val profileList = ArrayList<Item>()
            for (i in 0 until array.length()) {
                val profile = gson.fromJson<Item>(array.getString(i), Item::class.java)
                profileList.add(profile)
            }
            return profileList
        } catch (e: Exception) {
            e.printStackTrace()
            return null
        }

    }

