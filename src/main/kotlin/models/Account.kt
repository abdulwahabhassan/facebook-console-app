package models

import implementations.AdminServiceImpl
import implementations.AdultServiceImpl
import implementations.ChildServiceImpl
import implementations.UserServiceImpl

sealed class Account(
    open val accountId: String,
    open val userName: String,
    open val userAge: Int,
    open val passWord: String?,
    open val friends: MutableList<User>,
    open var loggedIn: Boolean,
) {

    val name
        get() = when(this) {
            is Adult -> "Adult"
            is Admin -> "Admin"
            is Child -> "Child"
        }

    data class Admin(
        override val accountId: String,
        override val userName: String,
        override val userAge: Int,
        override val passWord: String?,
        override val friends: MutableList<User> = mutableListOf<User>(),
        override var loggedIn: Boolean = false,
        private val userService: UserServiceImpl = UserServiceImpl(),
        private val adultServiceImpl: AdultServiceImpl = AdultServiceImpl(),
        private val adminServiceImpl: AdminServiceImpl = AdminServiceImpl()
    )
            : Account(accountId, userName, userAge, passWord, friends, loggedIn) {

        fun banAccount(account: Account) {
            adminServiceImpl.banAccount(account)
        }

        fun flagPost(post: Post) {
            adminServiceImpl.flagPost(post)
        }

        fun createPost(post: Post) {
            adultServiceImpl.createPost(post, this.accountId)
        }

        fun createChildAccount(child: User, passWord: String) {
            adultServiceImpl.createChildAccount(child, passWord)
        }

        fun addFriend(friend: User) {
            userService.addFriend(friend, this.accountId)
        }

        fun deletePost(post: Post) {
            userService.deletePost(post, this.accountId)
        }

        fun logIn(password: String) {
            userService.logIn(this.accountId, password)
        }

        fun logOut() {
            userService.logOut(this.accountId)
        }

        fun readPost(post: Post) {
            userService.readPost(post)
        }

        override fun toString(): String {
            return """
            UserId: $accountId
            Name: $userName
            Age: $userAge
            Password: $passWord
            Account: $name
        """.trimIndent()
        }
    }

    data class Adult(
        override val accountId: String,
        override val userName: String,
        override val userAge: Int,
        override val passWord: String?,
        override var loggedIn: Boolean = false,
        override val friends: MutableList<User> = mutableListOf<User>(),
        private val userService: UserServiceImpl = UserServiceImpl(),
        private val adultService: AdultServiceImpl = AdultServiceImpl()
    )
            : Account(accountId, userName, userAge, passWord, friends, loggedIn) {

        fun createPost(post: Post) {
            adultService.createPost(post, this.accountId)
        }

        fun createChildAccount(child: User, passWord: String) {
            adultService.createChildAccount(child, passWord)
        }

        fun addFriend(friend: User) {
            userService.addFriend(friend, this.accountId)
        }

        fun deletePost(post: Post) {
            userService.deletePost(post, this.accountId)
        }

        fun logIn(passWord: String) {
            userService.logIn(this.accountId, passWord)
        }

        fun logOut() {
            userService.logOut(this.accountId)
        }

        fun readPost(post: Post) {
            userService.readPost(post)
        }

        override fun toString(): String {
            return """
            UserId: $accountId
            Name: $userName
            Age: $userAge
            Password: $passWord
            Account: $name
        """.trimIndent()
        }
    }

    data class Child (
        override val accountId: String,
        override val userName: String,
        override val userAge: Int,
        override val passWord: String?,
        override var loggedIn: Boolean = false,
        val guardians: MutableList<Account> = mutableListOf<Account>(),
        override val friends: MutableList<User> = mutableListOf<User>(),
        private val userService: UserServiceImpl = UserServiceImpl(),
        private val childService: ChildServiceImpl = ChildServiceImpl()
    )
            : Account(accountId, userName, userAge, passWord, friends, loggedIn) {

        fun createPost(post: Post) {
            childService.createPost(post, this.accountId)
        }

        fun addFriend(friend: User) {
            userService.addFriend(friend, this.accountId)
        }

        fun addGuardian(guardian: Account) {
            childService.addGuardian(guardian, this.accountId)
        }

        fun deletePost(post: Post) {
            userService.deletePost(post, this.accountId)
        }

        fun logIn(password: String) {
            userService.logIn(this.accountId, password)
        }

        fun logOut() {
            userService.logOut(this.accountId)
        }

        fun readPost(post: Post) {
            userService.readPost(post)
        }

        override fun toString(): String {
            return """
            UserId: $accountId
            Name: $userName
            Age: $userAge
            Password: $passWord
            Account: $name
        """.trimIndent()
        }
    }


}