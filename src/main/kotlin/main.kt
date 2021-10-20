fun main(args: Array<String>) {

    //user1 installs facebook app
    val faceBookApp1 = FaceBookApp()

    //user1 creates an account
    val user1 = User("Daniel America", 19, false, 1234)
    user1.createAccount()

    //user2 installs facebook app
    val faceBookApp2 = FaceBookApp()

    //user2 creates an account
    val user2 = User("Ella Germany", 28, true, 2341)
    user2.createAccount()

    //user3 installs facebook app
    val faceBookApp3 = FaceBookApp()

    //user3 creates an account
    val user3 = User("Kid Waya", 9, false, 3412)
    user3.createAccount()

    println(FaceBookApp.mapOfUserIdToAccount)

}