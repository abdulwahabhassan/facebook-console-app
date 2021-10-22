package interfaces

import models.Account
import models.Post
import models.User

interface FaceBookService {

    fun generateAdultAccount(name: String, age: Int, password: String): Account.Adult

    fun generateAdminAccount(name: String, age: Int, password: String): Account.Admin

    fun generateChildAccount(name: String, age: Int, password: String): Account.Child

    fun addFriendToUserAccount(friend: User, accountId: String)

    fun addPostToHomePage(post: Post, accountId: String)

    fun deletePostFromHomePage(post: Post, accountId: String)

    fun getPosts(): MutableCollection<Post>

    fun logUserOut(accountId: String)

    fun logUserIn(accountId: String, password: String): Account?

    fun generateUserId() : Int

    fun generatePostId(): Int

    fun addGuardianToChildAccount(guardian: Account, childAccountId: String)

    fun approveChildPost(post: Post, accountId: String)

}