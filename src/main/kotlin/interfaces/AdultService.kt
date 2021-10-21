package interfaces

import models.Post
import models.User

interface AdultService {

    fun createPost(post: Post, accountId: String)

    fun createChildAccount(user: User, password: String)

    fun approveChildPost(post: Post, accountId: String)
}