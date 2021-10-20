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
        var mapOfUserIdToAccount = mutableMapOf<Int, Account>()
            private set

        //mutable list of banned accounts on the app
        private val bannedAccounts = mutableListOf<Int>()

        //mutable list of flagged posts on the app, identified by their postId
        private val flaggedPosts = mutableListOf<Int>()


        //method to generate user id
        internal fun generateUserId(): Int {
            //use the position of the new user on the list of users in the app as the new user's id
            return users.lastIndex + 1
        }

        //method to add user to app
        internal fun addUserToApp(user: User) {
            users.add(user)
        }

        internal fun createUserAccount(user: User, admin: Boolean) {
            //create user account type based on age qualification and admin status
            //select the appropriate account from the sealed class types
            //put user account in mapOfUserIdToAccount using the userID as key,
            //this associates a user id to an account
            if (user.age < 18) {
                println("PG 18: Children can't create account")
            } else if (user.age > 18 && admin) {
                mapOfUserIdToAccount[user.userId] = Account.Admin(user, true)
            } else {
                mapOfUserIdToAccount[user.userId] = Account.Adult(user, true)
            }
            //note that when accounts are created, they are logged in by default, hence true
            //until user logs out

            when (mapOfUserIdToAccount[user.userId]) {
                is Account.Admin -> println("Admin account created for ${user.name}, ${user.age}")
                is Account.Adult -> println("Adult account created for ${user.name}, ${user.age}")
                is Account.Child -> println("Child account created for ${user.name}, ${user.age}")
            }
        }

        internal fun createChildUserAccount(user: User, guardian: User) {
            //verify if guardian's account is an admin or adult account before allowing to create child account
            //only admin and adult accounts can create child account
            if (mapOfUserIdToAccount[guardian.userId] is Account.Admin ||
                mapOfUserIdToAccount[guardian.userId] is Account.Adult) {
                mapOfUserIdToAccount[user.userId] = Account.Child(user, true)
                //note that when accounts are created, they are logged in by default, hence true
                //until user logs out

                //automatically add guardian to child's account list of guardians
                (mapOfUserIdToAccount[user.userId] as Account.Child).addGuardian(guardian)
                println("Child account created for ${user.name}")
            }
        }

        //banned accounts is private and with this method admins can update it
        internal fun addToListOfBannedAccounts(user: User, account: Account) {
            //verify first if action is coming from user with account type of admin
            if (account is Account.Admin) {
                bannedAccounts.add(user.userId)
                println("Banned account for ${user.name}")
            }
        }

        //method for flagging post
        internal fun addPostToFlaggedPosts(post: Post, account: Account) {
            if (account is Account.Admin) {
                flaggedPosts.add(post.postId)
                println("This post with title: ${post.title} has been flagged")
            }
        }

        //method to generate post id
        internal fun generatePostId(): Int {
            //use the position of the new post on the list of posts in the app as the new post's id
            println("Post id: ${posts.lastIndex + 1} generated")
            return posts.lastIndex + 1
        }

        //method to create post
        internal fun createPost(post: Post, account: Account) {
            //verify if the account creating possible is of the allowed types: Admin and Adult only
            if (account is Account.Admin || account is Account.Adult) {
                posts.add(post)
                println("Post (Post id: ${post.postId}, Post title: ${post.title}) added to home page")
            }
        }

        //method used by admin and adult for publishing child post
        internal fun publishChildPost(post: Post, account: Account) {
            //verify if account is of Adult or Admin type
           if (account is Account.Adult || account is Account.Admin) {
               posts.add(post)
               println("Child post (Post id: ${post.postId}, Post title: ${post.title}) approved and published to " +
                       "home page by ${account.holder.name}")
           }
        }

        //method to access user account, must be accessed with a password
        internal fun getUserAccount(user: User, passWord: Int) : Account? {
            //if password matches and user.userId exists in the map as a key, retrieve and return the value of the key
            //which represents the account of the user else return null
            return if (passWord == user.getPassWord() && mapOfUserIdToAccount.containsKey(user.userId)) {
                println("Pass-word is correct and access granted")
                mapOfUserIdToAccount[user.userId]
            } else {
                println("Pass-word is in-correct or account does not exist, hence access denied")
                null
            }
        }

    }

}