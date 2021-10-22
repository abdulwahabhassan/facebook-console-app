package implementations

import interfaces.ChildService
import interfaces.UserService
import models.Account
import models.Post
import models.User

class ChildServiceImpl (
    private val faceBookServiceImpl: FacebookServiceImpl = FacebookServiceImpl()
        ) : ChildService, UserServiceImpl() {

    override fun addGuardian(guardian: Account, childAccountId: String) {
        faceBookServiceImpl.addGuardianToChildAccount(guardian, childAccountId)
    }

    override fun createPost(post: Post, accountId: String) {
        faceBookServiceImpl.approveChildPost(post, accountId)
    }

}