package interfaces

import models.Account
import models.Post

interface AdminService {

    fun flagPost(post: Post)

    fun banAccount(account: Account)

}