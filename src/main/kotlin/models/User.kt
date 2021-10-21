package models

import implementations.FacebookServiceImpl
import java.util.*
import kotlin.properties.Delegates

data class User(
    var name: String,
    var age: Int,
    private val facebookServiceImpl: FacebookServiceImpl = FacebookServiceImpl()
)  {

    var userId by Delegates.notNull<String>()

    init {
        userId = name.trim().lowercase(Locale.getDefault())
            .replace(' ', '#', true) +
                facebookServiceImpl.generateUserId().toString()
    }

}
