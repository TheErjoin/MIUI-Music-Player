package kg.erjan.musicplayer.presentation.extensions

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import kg.erjan.musicplayer.presentation.ui.state.UIState

@Composable
fun <T> State<UIState<T>>.collectUIState(
    state: @Composable() ((UIState<T>) -> Unit)? = null,
    onError: ((error: String) -> Unit)? = null,
    onSuccess: @Composable (data: T) -> Unit
) {
    when (val state = value) {
        is UIState.Idle -> {}
        is UIState.Loading -> {}
        is UIState.Error -> onError?.invoke(state.error)
        is UIState.Success -> onSuccess.invoke(state.data)
    }
}