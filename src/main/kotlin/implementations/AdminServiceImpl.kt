package implementations

import interfaces.AdminService
import models.Account
import models.Post

class AdminServiceImpl (
    private val facebookServiceImpl: FacebookServiceImpl = FacebookServiceImpl()
        ) : AdminService, AdultServiceImpl() {

    override fun flagPost(post: Post) {
        facebookServiceImpl.flagPost(post)
    }

    override fun banAccount(account: Account) {
        facebookServiceImpl.banAccount(account)
    }
}