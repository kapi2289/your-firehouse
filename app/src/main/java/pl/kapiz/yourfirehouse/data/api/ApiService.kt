package pl.kapiz.yourfirehouse.data.api

import io.reactivex.Single
import pl.kapiz.yourfirehouse.data.db.entity.Alarm
import pl.kapiz.yourfirehouse.data.db.entity.User
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton

@Singleton
interface ApiService {

    @GET("User/GetUser")
    fun getUser(): Single<User>

    @GET("Alarm/GetAlarmList")
    fun getAlarms(
        @Query("ouId") ouId: Long,
        @Query("count") count: Int,
        @Query("offset") offset: Int
    ): Single<List<Alarm>>
}
