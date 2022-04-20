package com.example.common.eventbus

import com.example.common.BaseObservable


class CustomEventBus : BaseObservable<CustomEventBus.Listener?>() {
    interface Listener {
        fun onEvent(event: Any?)
    }

    fun postEvent(event: Any?) {
        for (listener in listeners) {
            listener?.onEvent(event)
        }
    }
}