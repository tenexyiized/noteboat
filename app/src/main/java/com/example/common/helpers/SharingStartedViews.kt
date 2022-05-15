package com.example.common.helpers

import kotlinx.coroutines.flow.SharingStarted

private const val StopTimeoutMillis: Long = 5000


val WhileViewSubscribed: SharingStarted = SharingStarted.WhileSubscribed(StopTimeoutMillis)