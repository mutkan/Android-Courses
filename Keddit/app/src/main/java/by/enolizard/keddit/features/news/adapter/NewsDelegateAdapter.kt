package by.enolizard.keddit.features.news.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import by.enolizard.keddit.R
import by.enolizard.keddit.commons.adapter.ViewType
import by.enolizard.keddit.commons.adapter.ViewTypeDelegateAdapter
import by.enolizard.keddit.commons.extensions.getFriendlyTime
import by.enolizard.keddit.commons.extensions.inflate
import by.enolizard.keddit.commons.extensions.loadImg
import by.enolizard.keddit.commons.RedditNewsItem
import kotlinx.android.synthetic.main.news_item.view.*

class NewsDelegateAdapter(val mCommit: onViewSelectedListener) : ViewTypeDelegateAdapter {

    interface onViewSelectedListener {
        fun onItemSelected(url: String)
    }

    override fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder {
        return NewsViewHolder(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType) {
        holder as NewsViewHolder
        holder.bind(item as RedditNewsItem)
    }

    inner class NewsViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(parent.inflate(R.layout.news_item)) {
        fun bind(item: RedditNewsItem) = with(itemView) {
            img_thumbnail.loadImg(item.thumbnail)
//            Picasso.get().load(item.thumbnail).into(img_thumbnail)
            description.text = item.title
            author.text = item.author
            comments.text = "${item.numComments} comments"
            time.text = item.created.getFriendlyTime()
            url.text = item.url
            super.itemView.setOnClickListener({
                mCommit.onItemSelected(it.url.text as String)
            })
        }
    }
}