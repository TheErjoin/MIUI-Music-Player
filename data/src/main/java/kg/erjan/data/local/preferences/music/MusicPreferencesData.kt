package kg.erjan.data.local.preferences.music

import kg.erjan.data.local.preferences.PreferencesConstants
import kg.erjan.data.local.preferences.PreferencesHelper
import kg.erjan.data.utils.SortOrder

class MusicPreferencesData(
    private val preferencesHelper: PreferencesHelper
) {
    var isSongSortOrder: String
        get() = preferencesHelper().getString(
            PreferencesConstants.SONG_SORT_ORDER, SortOrder.SongSortOrder.SONG_DATE
        )!!
        set(value) = preferencesHelper().edit().putString(
            PreferencesConstants.SONG_SORT_ORDER, value
        ).apply()

    var filterLength: Int
        get() = preferencesHelper().getInt(
            PreferencesConstants.FILTER_SONG, 20
        )
        set(value) = preferencesHelper().edit().putInt(
            PreferencesConstants.FILTER_SONG, value
        ).apply()
}