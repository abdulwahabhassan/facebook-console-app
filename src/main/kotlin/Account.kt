sealed class Account (var holder: User) : AccountInterface, FriendInterface, PostInterface {

    class Admin(holder: User) : Account(holder), GuidanceInterface {

        fun flagPost(post: Post) {
            //add post to list of flagged posts
            FaceBookApp.addPostToFlaggedPosts(post)
            print("This post with title: ${post.title} has been flagged")
        }

        fun banAccount(user: User) {
            //add user to list of banned accounts
            FaceBookApp.addToListOfBannedAccounts(user)
            print("Banned account for ${user.name}")
        }

        override fun createChildAccount(user: User) {
            FaceBookApp.createChildUserAccount(user, holder)
        }

        override fun approveChildPost(post: Post, child: User) {

        }


    }

    class Child (holder: User) : Account(holder) {

        private val guardians = mutableListOf<User>()

        fun addGuardian(user: User) {
            //child can add a guardian
            guardians.add(user)
        }

        fun requestEditApproval() {

        }

        fun requestPostApproval() {

        }

    }

    class Adult (holder: User) : Account(holder), GuidanceInterface {

        fun approveChildPost() {}
        override fun createChildAccount(user: User) {

        }

        override fun approveChildPost(post: Post, child: User) {

        }


    }

    override fun addFriends() {

    }

    override fun deletePost() {

    }

    override fun logIn() {

    }

    override fun logOut() {

    }

    override fun readPost() {

    }


}