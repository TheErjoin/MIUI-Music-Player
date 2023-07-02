package kg.erjan.data.local.preferences

import android.content.Context
import android.content.SharedPreferences

class PreferencesHelper(context: Context) {

    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("music_player.preferences", Context.MODE_PRIVATE)

    operator fun invoke() = sharedPreferences

    private fun remove(key: String){
        sharedPreferences.edit().remove(key).apply()
    }

}