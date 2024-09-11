package com.example.movies.shared.data.dataprefence

import android.content.Context
import android.util.Log
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

    suspend fun SaveCategoryState(category: String) {
        Log.d("DataPreference", "Saving category state: $category")
        context.dataStore.edit { preferences ->
            preferences[CATEGORY_STATE] = category
        }
    }

    val categoryState: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[CATEGORY_STATE] ?: "health"
        }
}

