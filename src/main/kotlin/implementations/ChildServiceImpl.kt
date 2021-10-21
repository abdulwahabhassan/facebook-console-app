package implementations

import interfaces.ChildService
import models.Account
import models.Post

class ChildServiceImpl (
    private val faceBookServiceImpl: FacebookServiceImpl = FacebookServiceImpl(),
    private val adultServiceImpl: AdultServiceImpl = AdultServiceImpl()
        ) : ChildService {

    override fun addGuardian(guardian: Account, childAccountId: String) {
        faceBookServiceImpl.addGuardianToChildAccount(guardian, childAccountId)
    }

    override fun createPost(post: Post, accountId: String) {
        adultServiceImpl.approveChildPost(post, accountId)
    }

}