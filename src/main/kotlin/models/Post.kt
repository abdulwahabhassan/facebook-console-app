package models

import implementations.FacebookServiceImpl
import kotlin.properties.Delegates

data class Post(
    var title: String,
    var body: String,
    private val facebookServiceImpl: FacebookServiceImpl = FacebookServiceImpl()
    )  {

    var postId by Delegates.notNull<Int>()

    init {
        postId = facebookServiceImpl.generatePostId()
    }
}
