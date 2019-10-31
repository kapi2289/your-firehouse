package pl.kapiz.yourfirehouse.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Singleton

@Singleton
class SchedulersProvider {

    val mainThread: Scheduler
        get() = AndroidSchedulers.mainThread()

    val backgroundThread: Scheduler
        get() = Schedulers.io()
}
