package interfaces

import models.Post
import models.User

interface AdultService {

    fun createChildAccount(user: User, password: String)

    fun approveChildPost(post: Post, accountId: String)
}