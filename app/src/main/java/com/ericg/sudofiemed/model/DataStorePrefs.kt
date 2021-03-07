package com.ericg.sudofiemed.model

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import androidx.lifecycle.asLiveData
import kotlinx.coroutines.flow.map
import com.ericg.sudofiemed.model.DataStorePrefsType as PrefsType

class DataStorePrefs(context: Context) {

    private val dataStorePrefs = context.createDataStore(name = "app_prefs")

    companion object {
        val SHOW_ON_BOARD_KEY = booleanPreferencesKey("SHOW_ON_BOARD")
    }

    suspend fun setPrefs(prefType: PrefsType, value: Boolean?) {
        if (prefType == PrefsType.SHOW_ON_BOARD) {
            dataStorePrefs.edit {
                value?.let { value ->
                    it[SHOW_ON_BOARD_KEY] = value
                }
            }
        }
    }

    val showOnBoard = dataStorePrefs.data.map {
        it[SHOW_ON_BOARD_KEY] ?: true
    }.asLiveData()
}