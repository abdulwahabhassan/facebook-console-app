sealed class Account (
    val holder: User,
    var loggedIn: Boolean,
    private val friends: MutableList<User> = mutableListOf()) : AccountInterface, FriendInterface, PostInterface {

    //all new account by default have an empty mutable list of friends

    class Admin(holder: User, loggedIn: Boolean) : Account(holder, loggedIn), GuidanceInterface {

        fun flagPost(post: Post) {
            //add post to list of flagged posts
            FaceBookApp.addPostToFlaggedPosts(post, this)
        }

        fun banAccount(user: User) {
            //add user to list of banned accounts
            FaceBookApp.addToListOfBannedAccounts(user, this)
        }

        //admin can create child account
        override fun createChildAccount(user: User) {
            FaceBookApp.createChildUserAccount(user, holder)
        }

        //admin can approve child post
        override fun approveChildPost(post: Post, child: User) {
            FaceBookApp.publishChildPost(post, this)
        }


    }

    class Child (holder: User, loggedIn: Boolean) : Account(holder, loggedIn) {

        //child's guardians
        private val guardians = mutableListOf<User>()

        //child can add to list of guardians
        fun addGuardian(user: User) {
            guardians.add(user)
        }

        //child need to request guardian's approval to post
        fun requestPostApproval(post: Post) {

            //retrieve account of the first guardian child's list of guardian
            val guardianAccount = FaceBookApp.mapOfUserIdToAccount[guardians.first().userId]

            //send a request for approval of post to the guardian's account depending on guardian's account type
            if (guardianAccount is Admin) {
                guardianAccount.approveChildPost(post, holder)
            } else if (guardianAccount is Adult) {
                guardianAccount.approveChildPost(post, holder)
            }
        }

    }

    class Adult (holder: User, loggedIn: Boolean) : Account(holder, loggedIn), GuidanceInterface {
        //adult can create child account
        override fun createChildAccount(user: User) {
            FaceBookApp.createChildUserAccount(user, holder)
        }

        //adult can approve child post
        override fun approveChildPost(post: Post, child: User) {
            FaceBookApp.publishChildPost(post, this)
        }

    }

    //all users with any type of account can add friends, delete post, read post, log in and log out of their account

    override fun addFriends(user: User) {
        friends.add(user)
    }

    override fun deletePost(post: Post) {
        FaceBookApp.posts.forEachIndexed { index, mpost ->
            if ((mpost.postId == post.postId)) {
                val deletedPost = FaceBookApp.posts.removeAt(index)
                println("Deleted post ${deletedPost.title}")
                return@forEachIndexed
            }
        }
    }

    override fun logIn() {
        loggedIn = true
        println("${holder.name} Logged in")
    }

    override fun logOut() {
        loggedIn = false
        println("${holder.name} Logged out")
    }

    override fun readPost(post: Post) {
        print("Reading post ...Title-> ${post.title} ...Body-> ${post.body}")
    }

    override fun getListOfFriends(): MutableList<User> {
        return friends
    }


}