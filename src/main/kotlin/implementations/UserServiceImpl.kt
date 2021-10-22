package implementations

import interfaces.UserService
import models.Post
import models.User

abstract class UserServiceImpl (
    private val faceBookServiceImpl: FacebookServiceImpl = FacebookServiceImpl()
        ) : UserService {

    override fun addFriend(friend: User, accountId: String) {
        faceBookServiceImpl.addFriendToUserAccount(friend, accountId)
    }

    override fun deletePost(post: Post, accountId: String) {
        faceBookServiceImpl.deletePostFromHomePage(post, accountId)
    }

    override fun logOut(accountId: String) {
        faceBookServiceImpl.logUserOut(accountId)
    }

    override fun readPost(post: Post): String {
        return "Reading mode..\nTitle -> ${post.title}" +
                "\nBody-> ${post.body}"
    }

    abstract fun createPost(post: Post, accountId: String)

}