import android.graphics.Typeface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.codepathmail.R

class EmailAdapter(private val emails: List<Email>) : RecyclerView.Adapter<EmailAdapter.ViewHolder>() {
    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // Your holder should contain a member variable for any view that will be set as you render
        // a row
        val senderTextView: TextView
        val titleTextView: TextView
        val summaryTextView: TextView
        val timeTextView: TextView

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view
        init {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            senderTextView = itemView.findViewById(R.id.senderTv)
            titleTextView = itemView.findViewById(R.id.titleTv)
            summaryTextView = itemView.findViewById(R.id.summaryTv)
            timeTextView = itemView.findViewById(R.id.timeTv)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val contactView = inflater.inflate(R.layout.email_item, parent, false)
        // Return a new holder instance
        return ViewHolder(contactView)
    }

    override fun getItemCount(): Int {
        return emails.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val email = emails[position]

        holder.senderTextView.text = email.sender
        holder.titleTextView.text = email.title
        holder.summaryTextView.text = email.summary
        holder.timeTextView.text = email.time

        // Check the 'isRead' flag and set the text style accordingly
        if (email.isRead) {
            holder.titleTextView.setTypeface(null, Typeface.NORMAL) // Mark as read - remove bold
            holder.senderTextView.setTypeface(null, Typeface.NORMAL)
        } else {
            holder.titleTextView.setTypeface(null, Typeface.BOLD) // Unread - set to bold
            holder.senderTextView.setTypeface(null, Typeface.BOLD)
        }

        // Handle click events to mark emails as read
        holder.itemView.setOnClickListener {
            if (!email.isRead) {
                email.isRead = true
                holder.titleTextView.setTypeface(null, Typeface.NORMAL) // Mark as read - remove bold
                holder.senderTextView.setTypeface(null, Typeface.NORMAL)
            }
            // Handle the click event (e.g., open the email)
            // ...
        }
    }
}




