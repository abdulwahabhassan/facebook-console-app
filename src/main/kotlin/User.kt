import kotlin.properties.Delegates

data class User (
    var name: String,
    var age: Int,
    var admin: Boolean,
    var passWord: Int)  {

    var userId by Delegates.notNull<Int>()

    init {
            FaceBookApp.addUserToApp(this).also {
                //use the position of the new user on the list of users in the app as the new user's id
                userId = FaceBookApp.generateUserId()
            }
    }

    //all users can create account, with the exception of child
    fun createAccount() {
        FaceBookApp.createUserAccount(this, admin)
    }

    //to use this method to access user's account, password must be provided
    fun getAccount(passWord: Int) : Account? {
        //if password match the user's password, retrieve the user's account else return null
        return if (passWord == this.passWord) FaceBookApp.getUserAccount(this) else null

    }

}
