import models.Account
import models.Post
import models.User

class FaceBookApp {

    companion object {

        var mapOfPosts = mutableMapOf<String, Post>()
            private set

        var mapOfAccounts = mutableMapOf<String, Account>()
            private set

        var mapOfBanAccounts = mutableMapOf<String, Account>()
            private set

        var mapOfFlaggedPosts = mutableMapOf<Int, Post>()
            private set


        fun createAdultAccount(user: User, passWord: String): Account.Adult {
                val account = Account.Adult(
                    accountId = user.userId,
                    userName = user.name,
                    userAge =user.age,
                    passWord = passWord)
            mapOfAccounts[account.accountId] = account
            return account
        }

        fun createAdminAccount(user: User, passWord: String): Account.Admin {
                val account = Account.Admin(
                    accountId = user.userId,
                    userName = user.name,
                    userAge =user.age,
                    passWord = passWord)
            mapOfAccounts[account.accountId] = account
            return account
        }

        fun createChildAccount(user: User, passWord: String): Account.Child {
                val account = Account.Child(
                    accountId = user.userId,
                    userName = user.name,
                    userAge =user.age,
                    passWord = passWord)
            mapOfAccounts[account.accountId] = account
            return account

        }

    }

}