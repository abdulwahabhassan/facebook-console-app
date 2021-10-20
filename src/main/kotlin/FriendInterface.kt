interface FriendInterface {
    fun addFriends(user: User)
    fun getListOfFriends() : MutableList<User>
}