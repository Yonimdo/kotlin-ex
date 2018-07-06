package com.mdo.yoni.eshop.data

import android.content.Context
import android.content.SharedPreferences

private val SHARED_PREFERENCES_MANAGER = "SharedPreferencesManager "
private val SEARCH_WORDS = "SEARCHWORDS"


fun getSharedPrefs(context: Context): SharedPreferences {
    return context.getSharedPreferences(SHARED_PREFERENCES_MANAGER, Context.MODE_PRIVATE)
}

fun getSearchWords(context: Context): List<String> {
    return getSharedPrefs(context).getString(SEARCH_WORDS, "").split(",");
}

fun setSearchWords(context: Context, accepted: List<String>) {
    val editor: SharedPreferences.Editor = getSharedPrefs(context).edit();
    editor.putString(SEARCH_WORDS, accepted.joinToString(","));
    editor.apply();
}
