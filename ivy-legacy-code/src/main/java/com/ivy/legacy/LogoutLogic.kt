package com.ivy.legacy

import com.ivy.frp.view.navigation.Navigation
import com.ivy.navigation.Main
import com.ivy.navigation.Onboarding
import com.ivy.wallet.io.persistence.IvyRoomDatabase
import com.ivy.wallet.io.persistence.SharedPrefs
import com.ivy.wallet.utils.ioThread
import javax.inject.Inject

@Deprecated("Migrate to FP Style & Actions")
class LogoutLogic @Inject constructor(
    private val ivyDb: IvyRoomDatabase,
    private val sharedPrefs: SharedPrefs,
    private val navigation: Navigation
) {
    suspend fun logout() {
        ioThread {
            ivyDb.reset()
            sharedPrefs.removeAll()
        }

        navigation.resetBackStack()
        navigation.navigateTo(Onboarding)
    }

    suspend fun cloudLogout() {
        navigation.navigateTo(Main)
    }
}