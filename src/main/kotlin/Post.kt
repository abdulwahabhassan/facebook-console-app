import kotlin.properties.Delegates

data class Post(
    var title: String,
    var body: String,
    )  {

    var postId by Delegates.notNull<Int>()

    //initialization block to initialize member properties of class
    init {
        FaceBookApp.posts.add(this).also {
            //use the position of the new post on the list of posts in the app as the new post's id
            postId = FaceBookApp.generatePostId()
        }
    }
}
