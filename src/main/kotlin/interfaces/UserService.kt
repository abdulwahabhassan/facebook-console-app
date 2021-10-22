package interfaces

import models.Post
import models.User

interface UserService  {

    fun addFriend(friend: User, accountId: String)

    fun deletePost(post: Post, accountId: String)

    fun logOut(accountId: String)

    fun readPost(post: Post): String

}