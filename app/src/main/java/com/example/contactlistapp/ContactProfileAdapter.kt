package com.example.contactlistapp



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.databinding.ListItemBinding

class ContactProfileAdapter (var contactProfileData: List<ContactProfileData>) :
    RecyclerView.Adapter<ContactProfileAdapter.ProfileViewHolder>() {

    // Store the position of the currently expanded item
    private var expandedPosition: Int = RecyclerView.NO_POSITION
    inner class ProfileViewHolder( val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        // Inflate the layout using data binding
        return ProfileViewHolder(ListItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun getItemCount(): Int = contactProfileData.size

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        // Bind the contact profile data to the layout using data binding
        val contactsProfile = contactProfileData[position]

        holder.binding.name.text = contactsProfile.name
        holder.binding.number.text = contactsProfile.number
        holder.binding.email.text = contactsProfile.email

        // Check if this item is the currently expanded one
        val isExpanded = position == expandedPosition

        // Show/hide additional details based on the expanded state
        holder.binding.number.visibility = if (isExpanded) View.VISIBLE else View.GONE
        holder.binding.email.visibility = if (isExpanded) View.VISIBLE else View.GONE

        // Set a click listener on the expanded layout
        holder.binding.constraintLayout.setOnClickListener {
            val prevExpandedPosition = expandedPosition
            expandedPosition = if (isExpanded) RecyclerView.NO_POSITION else position

            // Collapse the previously expanded item
            if (prevExpandedPosition != RecyclerView.NO_POSITION) {
                contactProfileData[prevExpandedPosition].isExpandable = false
                notifyItemChanged(prevExpandedPosition)

            }

            // Expand/Collapse the clicked item
            contactsProfile.isExpandable = !isExpanded
            notifyItemChanged(position)
        }
    }

}



