package interfaces

import models.Account
import models.Post

interface ChildService {

    fun addGuardian(guardian: Account, childAccountId: String)

    fun createPost(post: Post, accountId: String)
}