package ir.lb7.dindin.helper

import com.airbnb.mvrx.*
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables

abstract class MvRxViewModel<S : MavericksState>(initialState: S) : BaseMvRxViewModel<S>(initialState){

}
