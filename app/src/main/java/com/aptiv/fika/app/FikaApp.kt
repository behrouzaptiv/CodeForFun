package com.aptiv.fika.app

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class FikaApp  @Inject constructor() : Application()