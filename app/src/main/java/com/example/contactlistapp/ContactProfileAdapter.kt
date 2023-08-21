package com.example.contactlistapp



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.databinding.ListItemBinding

class ContactProfileAdapter (var contactProfileData: List<ContactProfileData>) :
    RecyclerView.Adapter<ContactProfileAdapter.ProfileViewHolder>() {

    // Store the position of the currently expanded item
    private var expandedPosition: Int = RecyclerView.NO_POSITION
    inner class ProfileViewHolder( val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private fun menuPopUp(view: View, position: Int) {
        val popUpMenus = PopupMenu(view.context, view)
        popUpMenus.inflate(R.menu.menu_edit_delete)
        popUpMenus.setOnMenuItemClickListener{ menuItem ->
            when (menuItem.itemId){
                R.id.editText -> {
                    // Handle the Edit option for the specific item at 'position'
                    // Show an AlertDialog for editing
                    true
                } R.id.delete -> {
                // Handle the Delete option for the specific item at 'position'
                // Show a confirmation dialog and delete the item if confirmed
                true
            }
                else -> false

            }
        }
        // Show the PopupMenu
        val popup = PopupMenu::class.java.getDeclaredField("mPopup")
        popup.isAccessible = true
        val menu = popup.get(popUpMenus)
        menu.javaClass.getDeclaredMethod("setForceShowIcon", Boolean::class.java)
            .invoke(menu, true)

        popUpMenus.show()
        }



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

        holder.binding.mMenus.setOnClickListener{

            menuPopUp(it, position)

        }

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



