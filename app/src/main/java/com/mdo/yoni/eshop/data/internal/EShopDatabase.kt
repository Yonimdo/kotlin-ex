package com.mdo.yoni.eshop.data.internal

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context

import com.mdo.yoni.eshop.data.models.Item
import com.mdo.yoni.eshop.data.models.dao.ItemDao

/**
 * Created by axier on 7/2/18.
 */

@Database(entities = [(Item::class)], version = 9, exportSchema = false)
abstract class EShopDatabase : RoomDatabase() {

    abstract fun itemsDao(): ItemDao

    companion object {

        /**
         * The only instance
         */
        private var sInstance: EShopDatabase? = null

        /**
         * Gets the singleton instance of SampleDatabase.
         *
         * @param context The context.
         * @return The singleton instance of SampleDatabase.
         */
        @Synchronized
        fun getInstance(context: Context): EShopDatabase {
            if (sInstance == null) {
                sInstance = Room
                        .databaseBuilder(context.applicationContext, EShopDatabase::class.java, "example")
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return sInstance!!
        }


    }

}