package com.example.movies.common.data.dataprefence

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataPreference(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("preference_name")
        private val CATEGORY_STATE = stringPreferencesKey("category_state")
    }

    suspend fun saveCategoryState(category: String) {
        context.dataStore.edit { preferences ->
            preferences[CATEGORY_STATE] = category
        }
    }

    val categoryState: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[CATEGORY_STATE] ?: "politics"
    }
}

