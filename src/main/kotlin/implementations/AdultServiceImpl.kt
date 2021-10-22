package implementations

import interfaces.AdultService
import models.Post
import models.User

open class AdultServiceImpl (
    private val faceBookServiceImpl: FacebookServiceImpl = FacebookServiceImpl()
        ) : AdultService, UserServiceImpl() {

    override fun createPost(post: Post, accountId: String) {
            faceBookServiceImpl.addPostToHomePage(post, accountId)
    }

    override fun createChildAccount(user: User, password: String) {
            faceBookServiceImpl.generateChildAccount(user.name, user.age, password)
    }

    override fun approveChildPost(post: Post, accountId: String) {
        if (!post.body.trim().contains("f*ck", true)) {
            faceBookServiceImpl.addPostToHomePage(post, accountId)
        }
    }
}