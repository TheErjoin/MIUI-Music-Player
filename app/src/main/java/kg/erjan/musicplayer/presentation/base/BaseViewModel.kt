package kg.erjan.musicplayer.presentation.base

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kg.erjan.domain.utils.Either
import kg.erjan.musicplayer.presentation.ui.state.UIState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    fun <T> MutableUIStateFlow() = MutableStateFlow<UIState<T>>(UIState.Idle())

    fun <T> MutableState<UIState<T>>.reset() {
        value = UIState.Idle()
    }

    protected fun <T> Flow<Either<T, String>>.collectRequest(
        state: MutableStateFlow<UIState<T>>,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            state.value = UIState.Loading()
            this@collectRequest.collect {
                when (it) {
                    is Either.Off -> state.value = UIState.Error(it.value)
                    is Either.On -> state.value = UIState.Success(it.value)
                }
            }
        }
    }

}