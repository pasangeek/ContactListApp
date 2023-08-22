package com.example.contactlistapp.view


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.PopupMenu
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.contactlistapp.Data.ContactProfileData
import com.example.contactlistapp.R
import com.example.contactlistapp.databinding.ListItemBinding


class ContactProfileAdapter(
    var contactProfileData: ArrayList<ContactProfileData>,
    private val viewModel: ContactProfileViewModel
) :
    RecyclerView.Adapter<ContactProfileAdapter.ProfileViewHolder>() {


    fun setFilteredList(contactProfileData: ArrayList<ContactProfileData>) {
        this.contactProfileData = contactProfileData
        notifyDataSetChanged()
    }

    // Store the position of the currently expanded item
    private var expandedPosition: Int = RecyclerView.NO_POSITION

    inner class ProfileViewHolder(val binding: ListItemBinding) :
        RecyclerView.ViewHolder(binding.root)


    // Handles the popup menu when the more options icon is clicked
    private fun menuPopUp(view: View, position: Int) {


        val popUpMenus = PopupMenu(view.context, view)
        popUpMenus.inflate(R.menu.menu_edit_delete)
        popUpMenus.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.editText -> {
                    // Handle the Edit option for the specific item at 'position'
                    // Show an AlertDialog for editing
                    showEditDialog(view, position)
                    true
                }


                R.id.delete -> {
                    showDeleteConfirmationDialog(view, position)

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
    // Displays an edit dialog when the "Edit" option is clicked in the popup menu
    private fun showEditDialog(view: View, position: Int) {
        val inflater = LayoutInflater.from(view.context)
        val editView = inflater.inflate(R.layout.addnewcontact, null)
        val nameEditText = editView.findViewById<EditText>(R.id.etName)
        val numberEditText = editView.findViewById<EditText>(R.id.etNumber)
        val emailEditText = editView.findViewById<EditText>(R.id.etEmail)

        val currentItem = contactProfileData[position]
        nameEditText.setText(currentItem.name)
        numberEditText.setText(currentItem.number)
        emailEditText.setText(currentItem.email)


        AlertDialog.Builder(view.context)
            .setTitle("Edit Item")
            .setView(editView)
            .setPositiveButton("Save") { dialog, _ ->
                // Update the item data with the edited values
                val newName = nameEditText.text.toString()
                val newNumber = numberEditText.text.toString()

                currentItem.name = newName
                currentItem.number = newNumber

                notifyItemChanged(position)
                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    // Displays a confirmation dialog for deleting an item
    private fun showDeleteConfirmationDialog(view: View, position: Int) {


        val context = LayoutInflater.from(view.context) // Replace with your context source

        AlertDialog.Builder(view.context)
            .setTitle("Delete Item")
            .setMessage("Are you sure you want to delete this item?")
            .setPositiveButton("Delete") { dialog, _ ->
                // Delete the item from the data list
                // viewModel.deleteContacts(view,position)
                contactProfileData.removeAt(position)
                notifyItemRemoved(position)

                dialog.dismiss()
            }
            .setNegativeButton("Cancel") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
    // Inflates the layout for each list item
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {

        return ProfileViewHolder(
            ListItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    // Returns the total number of items in the dataset
    override fun getItemCount(): Int = contactProfileData.size
    // Binds data to the views in each list item
    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        // Bind the contact profile data to the layout using data binding
        val contactsProfile = contactProfileData[position]

        holder.binding.name.text = contactsProfile.name
        holder.binding.number.text = contactsProfile.number
        holder.binding.email.text = contactsProfile.email

        holder.binding.mMenus.setOnClickListener {

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





