package com.mdo.yoni.eshop.data

import android.content.Context
import android.content.SharedPreferences
import java.lang.reflect.Array.set

private val SHARED_PREFERENCES_MANAGER = "SharedPreferencesManager "
private val SEARCH_WORDS = "SEARCHWORDS"
private val COMPARE_IDS = "COMPARE_IDS"


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

fun getCompareIds(context: Context): ArrayList<String> {
    return ArrayList(getSharedPrefs(context).getStringSet(COMPARE_IDS, emptySet()));
}

fun setCompareIds(context: Context, accepted: ArrayList<String>) {
    val editor: SharedPreferences.Editor = getSharedPrefs(context).edit();
    editor.putStringSet(COMPARE_IDS, accepted.toSet());
    editor.apply();
}
