package com.adityaa0108.notesappmvvmtutorial.fragments

import android.content.Context
import android.content.Context.INPUT_METHOD_SERVICE
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.adityaa0108.notesappmvvmtutorial.R
import com.adityaa0108.notesappmvvmtutorial.activities.MainActivity
import com.adityaa0108.notesappmvvmtutorial.databinding.FragmentNoteBinding
import com.adityaa0108.notesappmvvmtutorial.utils.Extensions
import com.adityaa0108.notesappmvvmtutorial.viewModel.NoteActivityViewModel
import com.google.android.material.transition.MaterialElevationScale
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.security.cert.Extension


class NoteFragment : Fragment(R.layout.fragment_note) {

    private lateinit var fragmentNoteBinding:FragmentNoteBinding
    private val noteActivityViewModel:NoteActivityViewModel by activityViewModels()





    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
               exitTransition = MaterialElevationScale(false).apply {
                   duration = 350 //By setting the exit transition, the Fragment will animate using the MaterialElevationScale animation with a duration of 350 milliseconds when it is removed from the screen. The effect will be a smooth scaling animation that makes the Fragment shrink and disappear gracefully.
               }
              enterTransition = MaterialElevationScale(true).apply {
            duration = 350
              }



    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated (view, savedInstanceState)
        fragmentNoteBinding  = FragmentNoteBinding.bind(view) // The method takes a View parameter, which should be the root view of the fragment's layout hierarchy.The variable that holds the reference to the generated binding class instance.
        val activity = activity as MainActivity
        val navController = Navigation.findNavController(view) // This statement allows you to get access to the NavController for the given View, and you can use it to navigate to other destinations or perform other navigation-related operations within the fragment.
         Extensions.hideKeyboard(this)
        CoroutineScope(Dispatchers.Main).launch {
            delay(10)
                activity.window.statusBarColor = Color.WHITE
            activity.window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            activity.window.statusBarColor = Color.parseColor("#9E9D9D")
        }

        fragmentNoteBinding.addNoteFab.setOnClickListener {
            fragmentNoteBinding.appBarLayout.visibility = View.INVISIBLE
            navController.navigate(NoteFragmentDirections.actionNoteFragmentToSaveOrDeleteFragment())
        }
        fragmentNoteBinding.innerFab.setOnClickListener {
            fragmentNoteBinding.appBarLayout.visibility = View.INVISIBLE
            navController.navigate(NoteFragmentDirections.actionNoteFragmentToSaveOrDeleteFragment())
        }


    }




}



