import implementations.FacebookServiceImpl
import models.Account
import models.User
import utils.Utils

fun main(args: Array<String>) {

    val facebookServiceImpl = FacebookServiceImpl()

    fun showAccountDetails() {
        println("Account details")
    }


    fun showFriends() {
        println("Friends")
    }


    fun showGuardians() {
        println("Guardians")
    }


    fun goToHomePage() {
        println("Home page")
    }


    fun launchChildAccountFlow() {

        var input: String? = null

        do {
            if(input != null) {
                println("Invalid input, try again..")
            }
            println("""
                Press '1' to see your account details
                Press '2' to see your friends
                press '3' to see your guardians
                press '4' to go to home page
            """.trimIndent())
            input = readLine()
        } while (input != "1" && input != "2" && input != "3" && input != "4")

        when(input) {
            "1" -> showAccountDetails()
            "2" -> showFriends()
            "3" -> showGuardians()
            "4" -> goToHomePage()
        }
    }


    fun launchAdultAccountFlow() {

        var input: String? = null

        do {
            if (input != null) {
                println("Invalid input, try again..")
            }
            println("""
                Press '1' to see your account details
                Press '2' to see your friends
                press '3' to go to home page
            """.trimIndent())
            input = readLine()
        } while (input != "1" && input != "2" && input != "3")

        when(input) {
            "1" -> showAccountDetails()
            "2" -> showFriends()
            "3" -> goToHomePage()
        }

    }


    fun goToAdminPage() {

    }


    fun launchAdminAccountFlow() {

        var input: String? = null

        do {
            if (input != null) {
                println("Invalid input, try again..")
            }
            println("""
                Press '1' to see your account details
                Press '2' to see your friends
                press '3' to go to home page
                press '4' to go to Admin Page
            """.trimIndent())
            input = readLine()
        } while (input != "1" && input != "2" && input != "3")

        when(input) {
            "1" -> showAccountDetails()
            "2" -> showFriends()
            "3" -> goToHomePage()
            "4" -> goToAdminPage()
        }

    }


    fun startLoginFlow() {
        var userId: String? = null
        var password: String? = null
        var account: Account?

        do {
            if (userId != null && password != null) {
                println("Try again..")
            }
            println("Enter your user id:")
            userId = readLine()
            while (!Utils.validateEmptyField(userId)) {
                println("Please, Enter your user id:")
                userId = readLine()
            }

            println("Enter your password:")
            password = readLine()
            while (!Utils.validateEmptyField(password)) {
                println("Please, Enter your password:")
                password = readLine()
            }
            account = facebookServiceImpl.logUserIn(userId!!, password!!)
        }
        while (account == null)

        println("Successful login!, welcome ${account.userName}")
        when (account) {
            is Account.Child -> {
                launchChildAccountFlow()
            }
            is Account.Adult -> {
                launchAdultAccountFlow()
            }
            is Account.Admin -> {
                launchAdminAccountFlow()
            }
        }

    }


    fun startCreateAccountFlow() {

        var input: String?
        var fullName: String?
        var age: Int?
        var passWord: String?
        var admin: Boolean?
        val user: User?

        println("Enter your full name:")
        fullName = readLine()
        while (!Utils.validateEmptyField(fullName)) {
            println("Please, Enter your full name:")
            fullName = readLine()
        }

        println("Enter your age:")
        age = Utils.validateAge(readLine())
        while (age == null) {
            println("Age should be a number, enter age:")
            age = Utils.validateAge(readLine())
        }

        if (age < 18) {
            println("You are below 18 and ineligible for an account")
            return
        }

        println("Enter a password to use:")
        passWord = readLine()
        while (!Utils.validateEmptyField(passWord)) {
            println("Please, Enter a password to use:")
            passWord = readLine()
        }

        println("Are you an Admin? 'yes' or 'no':")
        admin = Utils.validateAdminEntry(readLine())
        while (admin == null) {
            println("Please, Enter 'yes' or 'no':")
            admin = Utils.validateAdminEntry(readLine())
        }

        val account = if(admin) {
            facebookServiceImpl.generateAdminAccount(fullName!!, age, passWord!!)
        } else {
            facebookServiceImpl.generateAdultAccount(fullName!!, age, passWord!!)
        }

        println("Congrats!! You have successfully created an account\n")
        println("$account\n")
        println("Press '1' to login to your account, press '2' to quit:")

        input = readLine()
        while (input != "1" && input != "2") {
            println("Invalid input, try again..")
            println("Press '1' to login to your account, press '2' to quit:")
            input = readLine()
        }

        if (input == "1") {
            startLoginFlow()
        } else if (input == "2") {
            println("Bye! Thanks for using Facebook")
            return
        }
    }


    fun startAppFlow () {
        println("Welcome to My FaceBook App")
        println("Press '1' to login or Press '2' to create account:")
        var input = readLine()
        while (Utils.validateStartEntry(input) == null) {
            println("Press '1' to login or Press '2' to create account:")
            input = readLine()
        }
        if (Utils.validateStartEntry(input) == 1) {
            startLoginFlow()
        } else if (Utils.validateStartEntry(input) == 2) {
            startCreateAccountFlow()
        }
    }

    startAppFlow()



}