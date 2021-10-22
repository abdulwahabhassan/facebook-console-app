package models

import implementations.AdminServiceImpl
import implementations.AdultServiceImpl
import implementations.ChildServiceImpl

sealed class Account (
    val accountId: String,
    val userName: String,
    val userAge: Int,
    val passWord: String?,
    var loggedIn: Boolean = false,
    val friends: MutableList<User> = mutableListOf<User>()
        ) {

    val type
        get() = when(this) {
            is Adult -> "Adult"
            is Admin -> "Admin"
            is Child -> "Child"
        }

    class Admin(
        accountId: String,
        userName: String,
        userAge: Int,
        passWord: String?,
        private val adminService: AdminServiceImpl = AdminServiceImpl()
    ) : Account(accountId, userName, userAge, passWord)

    class Adult(
        accountId: String,
        userName: String,
        userAge: Int,
        passWord: String?,
        private val adultService: AdultServiceImpl = AdultServiceImpl(),
    ) : Account(accountId, userName, userAge, passWord)

    class Child (
        accountId: String,
        userName: String,
        userAge: Int,
        passWord: String?,
        val guardians: MutableList<Account> = mutableListOf<Account>(),
        private val childService: ChildServiceImpl = ChildServiceImpl()
    ) : Account(accountId, userName, userAge, passWord)


    override fun toString(): String {
        return """
            UserId: $accountId
            Name: $userName
            Age: $userAge
            Password: $passWord
            Account: $type
        """.trimIndent()
    }

}