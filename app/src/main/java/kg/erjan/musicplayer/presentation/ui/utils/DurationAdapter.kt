package kg.erjan.musicplayer.presentation.ui.utils

import java.util.concurrent.TimeUnit

object DurationAdapter {

    fun formatAsMS(ms: Int) = formatAsMS(ms.toLong())

    private fun formatAsMS(ms: Long): String {
        return String.format(
            "%02d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(ms) % TimeUnit.HOURS.toMinutes(1),
            TimeUnit.MILLISECONDS.toSeconds(ms) % TimeUnit.MINUTES.toSeconds(1)
        )
    }
}