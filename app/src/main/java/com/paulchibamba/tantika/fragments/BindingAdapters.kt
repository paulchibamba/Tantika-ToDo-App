package com.paulchibamba.tantika.fragments

import android.content.Context
import android.os.Build
import android.view.View
import android.widget.ImageView
import android.widget.Spinner
import androidx.annotation.RequiresApi
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.lifecycle.MutableLiveData
import androidx.navigation.findNavController
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.paulchibamba.tantika.R
import com.paulchibamba.tantika.data.models.Priority
import com.paulchibamba.tantika.data.models.ToDoData
import com.paulchibamba.tantika.fragments.list.ListFragmentDirections

class BindingAdapters {
    companion object{

        @BindingAdapter("android:navigateToAddFragment")
        @JvmStatic
        fun navigateToAddFragment(view: FloatingActionButton, navigate: Boolean){
            view.setOnClickListener{
                if (navigate){
                    view.findNavController().navigate(R.id.action_listFragment_to_addFragment)
                }
            }
        }

        @BindingAdapter("android:emptyDataBase")
        @JvmStatic
        fun emptyDatabase(view: View, emptyDatabse: MutableLiveData<Boolean>){
            when(emptyDatabse.value){
                true -> view.visibility = View.VISIBLE
                false -> view.visibility = View.INVISIBLE
            }
        }

        @BindingAdapter("android:parsePriorityToInt")
        @JvmStatic
        fun parsePriorityToInt(view: Spinner, priority: Priority){
            when (priority){
                Priority.HIGH -> {view.setSelection(0)}
                Priority.MEDIUM -> {view.setSelection(1)}
                Priority.LOW -> {view.setSelection(2)}
            }
        }

        @RequiresApi(Build.VERSION_CODES.M)
        @BindingAdapter("android:parsePriorityColor")
        @JvmStatic
        fun parsePriorityColor(imageView: ImageView, priority: Priority){
            when(priority){
                Priority.HIGH -> {imageView.setColorFilter(imageView.context.getColor(R.color.highPriority), android.graphics.PorterDuff.Mode.MULTIPLY)}
                Priority.MEDIUM -> {imageView.setColorFilter(imageView.context.getColor(R.color.mediumPriority))}
                Priority.LOW -> {imageView.setColorFilter(imageView.context.getColor(R.color.lowPriority))}
            }
        }

        @BindingAdapter("android:sendDataToUpdateFragment")
        @JvmStatic
        fun sendDataToUpdateFragment(view: ConstraintLayout, currentItem: ToDoData){
            view.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToUpdateFragment(currentItem)
                view.findNavController().navigate(action)
            }
        }
    }
}