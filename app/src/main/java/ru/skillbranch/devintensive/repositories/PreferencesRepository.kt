package ru.skillbranch.devintensive.repositories

import android.content.SharedPreferences
import android.preference.PreferenceManager
import androidx.appcompat.app.AppCompatDelegate
import ru.skillbranch.devintensive.App
import ru.skillbranch.devintensive.models.Profile

object PreferencesRepository {

    private const val RATING = "RATING"
    private const val RESPECT = "RESPECT"
    private const val FIRST_NAME = "FIRST_NAME"
    private const val LAST_NAME = "LAST_NAME"
    private const val ABOUT = "ABOUT"
    private const val REPOSITORY = "REPOSITORY"
    private const val APP_THEME = "APP_THEME"

    private val prefs: SharedPreferences by lazy {
        val ctx = App.applicationContext()
        PreferenceManager.getDefaultSharedPreferences(ctx)
    }

    fun getProfile(): Profile? = Profile(
        prefs.getInt(RATING, 0),
        prefs.getInt(RESPECT, 0),
        prefs.getString(FIRST_NAME, "")!!,
        prefs.getString(LAST_NAME, "")!!,
        prefs.getString(ABOUT, "")!!,
        prefs.getString(REPOSITORY, "")!!
    )

    fun saveProfile(profile: Profile) {
        with(profile) {
            putValue(RATING to rating)
            putValue(RESPECT to respect)
            putValue(FIRST_NAME to firstName)
            putValue(LAST_NAME to lastName)
            putValue(ABOUT to about)
            putValue(REPOSITORY to repository)
        }
    }

    fun putValue(pair: Pair<String, Any>) = with(prefs.edit()) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            else -> error("Only primitives types")
        }
        apply()
    }

    fun saveThemeMode(theme: Int) {
        putValue(APP_THEME to theme)
    }

    fun getThemeMode(): Int = prefs.getInt(APP_THEME, AppCompatDelegate.MODE_NIGHT_NO)
}