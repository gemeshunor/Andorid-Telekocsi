package hu.bme.aut.android.bmeautomegosztas.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.bme.aut.android.bmeautomegosztas.R
import hu.bme.aut.android.bmeautomegosztas.data.Post
import kotlinx.android.synthetic.main.card_post.view.*

class PostsAdapter(private val context: Context) : RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    private val postList: MutableList<Post> = mutableListOf()
    private var lastPosition = -1

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvAuthor: TextView = itemView.tvAuthor
        val tvDate: TextView = itemView.tvDate
        val tvStart: TextView = itemView.tvStart
        val tvFinish: TextView = itemView.tvFinish
        val tvCarPlate: TextView = itemView.tvCarPlate
        val tvTel: TextView = itemView.tvTel
        val tvEmail: TextView = itemView.tvEmail
        val tvBody: TextView = itemView.tvBody
        val imgPost: ImageView = itemView.imgPost
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
                .from(viewGroup.context)
                .inflate(R.layout.card_post, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val tmpPost = postList[position]
        viewHolder.tvAuthor.text = "Feladó: " + tmpPost.author
        viewHolder.tvDate.text = "Dárum: " + tmpPost.date
        viewHolder.tvStart.text = "Indulás helye: " + tmpPost.start
        viewHolder.tvFinish.text = "Úticél: " + tmpPost.finish
        viewHolder.tvCarPlate.text = "Rendszám: " + tmpPost.carPlate
        viewHolder.tvTel.text = "Telefonszám: " + tmpPost.tel
        viewHolder.tvEmail.text = "Email: " + tmpPost.email
        viewHolder.tvBody.text = "Részletek: " + tmpPost.body

        if (tmpPost.imageUrl.isNullOrBlank()) {
            viewHolder.imgPost.visibility = View.GONE
        } else {
            Glide.with(context).load(tmpPost.imageUrl).into(viewHolder.imgPost)
            viewHolder.imgPost.visibility = View.VISIBLE
        }

        setAnimation(viewHolder.itemView, position)
    }

    override fun getItemCount() = postList.size

    fun addPost(post: Post?) {
        post ?: return

        postList.add(post)
        notifyDataSetChanged()
    }

    private fun setAnimation(viewToAnimate: View, position: Int) {
        if (position > lastPosition) {
            val animation = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
            viewToAnimate.startAnimation(animation)
            lastPosition = position
        }
    }

}