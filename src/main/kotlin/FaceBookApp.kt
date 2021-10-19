class FaceBookApp {
    //many instances of a facebook app may exist

    //However, a companion objects is used to depict singleton pattern for tying data contained within
    //to the class FaceBookApp but not to instances of the class itself
    companion object {
        //list of posts and users is public,
        //everyone on the app can see themselves and one another's post

        //mutable list of posts on the app
        //setter for post is set to private to prevent external agents from setting it's value
        var posts = mutableListOf<Post>()
            private set

        //mutable list of users on the app
        //setter for users is set to private to prevent external agents from setting it's value
        var users = mutableListOf<User>()
            private set

        //map of user to account, user Ids serve as key, keep as private, visible only within this app
        private val mapOfUserIdToAccount = mutableMapOf<Int, Account>()

        //mutable list of banned accounts on the app
        private val bannedAccounts = mutableListOf<Int>()

        //mutable list of flagged posts on the app, identified by their postId
        private val flaggedPosts = mutableListOf<Int>()

        //banned accounts is private and with this method admins can update it
        fun addToListOfBannedAccounts(user: User) {
           bannedAccounts.add(user.userId)
        }

        fun addPostToFlaggedPosts(post: Post) {
            flaggedPosts.add(post.postId)
        }

        fun createUserAccount(user: User, admin: Boolean) {
            //create user account type based on age qualification and admin status
            //select the appropriate account from the sealed class types
            //put user account in mapOfUserIdToAccount using the userID as key,
            //this associates a user id to an account
            if (user.age < 18) {
                println("PG 18: Children can't create account")
            } else if (user.age > 18 && admin) {
                mapOfUserIdToAccount[user.userId] = Account.Admin(user)
            } else {
                mapOfUserIdToAccount[user.userId] = Account.Adult(user)
            }

            when (mapOfUserIdToAccount[user.userId]) {
                is Account.Admin -> println("Admin account created")
                is Account.Adult -> println("Adult account created")
                is Account.Child -> println("Child account created")
            }
        }

        fun createChildUserAccount(user: User, guardian: User) {

            //verify if guardian's account is an admin or adult account before allowing to create child account
            if (mapOfUserIdToAccount[guardian.userId] is Account.Admin ||
                mapOfUserIdToAccount[guardian.userId] is Account.Adult) {
                mapOfUserIdToAccount[user.userId] = Account.Child(user)
            }
        }

        //method to generate user id
        fun generateUserId(): Int {
            //use the position of the new user on the list of users in the app as the new user's id
            return users.lastIndex + 1
        }

        //method to generate post id
        fun generatePostId(): Int {
            //use the position of the new post on the list of posts in the app as the new post's id
            return posts.lastIndex + 1
        }

        //method to create post
        fun createPost(post: Post) {
            posts.add(post)
        }

        //method to add user to app
        fun addUserToApp(user: User) {
            users.add(user)
        }

        //method to get user account
        fun getUserAccount(user: User) : Account? {
            //if user.userId exists in the map as a key, retrieve and return the value of the key
            //which represents the account of user else return null
            return if (mapOfUserIdToAccount.containsKey(user.userId)) mapOfUserIdToAccount[user.userId] else null
        }

    }

}