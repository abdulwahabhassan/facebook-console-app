package implementations

import FaceBookApp
import interfaces.FaceBookService
import models.Account
import models.Post
import models.User

class FacebookServiceImpl : FaceBookService {

    override fun generateAdultAccount(name: String, age: Int, password: String): Account.Adult {
        return FaceBookApp.createAdultAccount(User(name, age), password)
    }

    override fun generateAdminAccount(name: String, age: Int, password: String) : Account.Admin {
        return FaceBookApp.createAdminAccount(User(name, age), password)
    }

    override fun generateChildAccount(name: String, age: Int, password: String): Account.Child {
        return FaceBookApp.createChildAccount(User(name, age), password)
    }

    override fun addFriendToUserAccount(friend: User, accountId: String) {
        FaceBookApp.mapOfAccounts[accountId]?.friends?.add(friend)
    }

    override fun addPostToHomePage(post: Post, accountId: String) {
        FaceBookApp.mapOfPosts[accountId] = post
    }

    override fun deletePostFromHomePage(post: Post, accountId: String) {
        FaceBookApp.mapOfPosts.remove(accountId, post)
    }

    override fun getPosts(): MutableCollection<Post> {
        return FaceBookApp.mapOfPosts.values
    }

    override fun logUserOut(accountId: String) {
        FaceBookApp.mapOfAccounts[accountId]?.loggedIn = false
    }

    override fun logUserIn(accountId: String, password: String): Account? {

        return if (FaceBookApp.mapOfAccounts.containsKey(accountId) &&
            FaceBookApp.mapOfAccounts[accountId]?.passWord == password) {
                FaceBookApp.mapOfAccounts[accountId]?.loggedIn = true
                FaceBookApp.mapOfAccounts[accountId]
        } else {
            null
        }

    }

    override fun generateUserId(): Int {
        return FaceBookApp.mapOfAccounts.size + 1
    }

    override fun generatePostId(): Int {
        return FaceBookApp.mapOfPosts.size + 1
    }

    override fun addGuardianToChildAccount(guardian: Account, childAccountId: String) {
            (FaceBookApp.mapOfAccounts[childAccountId] as Account.Child).guardians.add(guardian)
    }

    fun banAccount(account: Account) {
        FaceBookApp.mapOfBanAccounts[account.accountId] = account
    }

    fun flagPost(post: Post) {
        FaceBookApp.mapOfFlaggedPosts[post.postId] = post
    }

}