

package ahmed.atwa.popularmovies.data.prefrence

import ahmed.atwa.popularmovies.utils.AppConstants
import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by Ahmed Atwa on 10/19/18.
 */

@Singleton
class AppPrefrence @Inject constructor(mContext: Context){

    private val PREF_KEY_ACCESS_TOKEN = "PREF_KEY_ACCESS_TOKEN"

    private val PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL"

    private val PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID"

    private val PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME"

    private val PREF_KEY_CURRENT_USER_PROFILE_PIC_URL = "PREF_KEY_CURRENT_USER_PROFILE_PIC_URL"

    private val PREF_KEY_USER_LOGGED_IN_MODE = "PREF_KEY_USER_LOGGED_IN_MODE"

    private var mPrefs: SharedPreferences = mContext.getSharedPreferences(AppConstants.PREF_NAME, Context.MODE_PRIVATE)


    fun getAccessToken(): String {
        return mPrefs.getString(PREF_KEY_ACCESS_TOKEN, null)
    }

    fun setAccessToken(accessToken: String) {
        mPrefs.edit().putString(PREF_KEY_ACCESS_TOKEN, accessToken).apply()
    }

    fun getCurrentUserEmail(): String {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL, null)
    }

    fun setCurrentUserEmail(email: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL, email).apply()
    }

    fun getCurrentUserId(): Long? {
        val userId = mPrefs.getLong(PREF_KEY_CURRENT_USER_ID, AppConstants.NULL_INDEX)
        return if (userId == AppConstants.NULL_INDEX) null else userId
    }

    fun setCurrentUserId(userId: Long?) {
        val id = userId ?: AppConstants.NULL_INDEX
        mPrefs.edit().putLong(PREF_KEY_CURRENT_USER_ID, id).apply()
    }

    fun getCurrentUserLoggedInMode(): Int {
        return mPrefs.getInt(PREF_KEY_USER_LOGGED_IN_MODE,
                LoggedInMode.LOGGED_IN_MODE_LOGGED_OUT.getType())
    }

    fun setCurrentUserLoggedInMode(mode: LoggedInMode) {
        mPrefs.edit().putInt(PREF_KEY_USER_LOGGED_IN_MODE, mode.getType()).apply()
    }

    fun getCurrentUserName(): String {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME, null)
    }

    fun setCurrentUserName(userName: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME, userName).apply()
    }

    fun getCurrentUserProfilePicUrl(): String {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, null)
    }

    fun setCurrentUserProfilePicUrl(profilePicUrl: String) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_PIC_URL, profilePicUrl).apply()
    }

    enum class LoggedInMode(private var mType: Int) {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(0),
        LOGGED_IN_MODE_FB(0),
        LOGGED_IN_MODE_SERVER(0);

        fun getType(): Int = mType

    }

}