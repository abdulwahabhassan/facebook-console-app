interface GuidanceInterface {

    //adults and admin can create account for children
    fun createChildAccount(user: User)

    //adults and admin can approve children's post
    fun approveChildPost(post: Post, child: User)
}