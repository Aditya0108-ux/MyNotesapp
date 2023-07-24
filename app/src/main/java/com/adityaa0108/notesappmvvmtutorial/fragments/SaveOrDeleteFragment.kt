package com.adityaa0108.notesappmvvmtutorial.fragments

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.adityaa0108.notesappmvvmtutorial.R
import com.adityaa0108.notesappmvvmtutorial.activities.MainActivity
import com.adityaa0108.notesappmvvmtutorial.databinding.BottomSheetLayoutBinding
import com.adityaa0108.notesappmvvmtutorial.databinding.FragmentSaveOrDeleteBinding
import com.adityaa0108.notesappmvvmtutorial.model.Note
import com.adityaa0108.notesappmvvmtutorial.utils.Extensions
import com.adityaa0108.notesappmvvmtutorial.viewModel.NoteActivityViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.transition.MaterialContainerTransform
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import java.text.SimpleDateFormat
import java.util.Date




class SaveOrDeleteFragment : Fragment(R.layout.fragment_save_or_delete) {

             private lateinit var navController: NavController
             private lateinit var fragmentSaveOrDeleteBinding: FragmentSaveOrDeleteBinding
             private var note: Note?=null
             private var color = -1
              private val noteActivityViewModel:NoteActivityViewModel by activityViewModels()
                private val currentDate = SimpleDateFormat.getInstance().format(Date())
                       private val job = CoroutineScope(Dispatchers.Main)
                      private  val args : SaveOrDeleteFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       val animation = MaterialContainerTransform().apply{
           drawingViewId = R.id.fragment
              scrimColor = Color.TRANSPARENT
           duration = 300L
       }
        sharedElementEnterTransition = animation
        sharedElementReturnTransition = animation
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fragmentSaveOrDeleteBinding = FragmentSaveOrDeleteBinding.bind(view)
        navController = Navigation.findNavController(view)
        val activity = activity as MainActivity
        fragmentSaveOrDeleteBinding.backBtn.setOnClickListener{
            Extensions.hideKeyboard(this)
            navController.popBackStack()

        }

        fragmentSaveOrDeleteBinding.saveNote.setOnClickListener {
            saveNote()

        }
        fragmentSaveOrDeleteBinding.fabColorPick.setOnClickListener {
            val bottomSheetDialog = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
            val bottomSheetView:View = layoutInflater.inflate(R.layout.bottom_sheet_layout,null)
            val bottomSheetLayoutBinding = BottomSheetLayoutBinding.bind(bottomSheetView)
            bottomSheetLayoutBinding.apply {
                colorPicker.apply {
                    setSelectedColor(color)
                    setOnColorSelectedListener {
                        value->
                        color = value
                        fragmentSaveOrDeleteBinding.apply {
                            noteContentFragmentParent.setBackgroundColor(color)
                            toolbarFragmentNoteContent.setBackgroundColor(color)
                            activity.window.statusBarColor = color
                        }
                        bottomSheetLayoutBinding.bottomSheetParent.setCardBackgroundColor(color)

                    }
                }
                bottomSheetParent.setCardBackgroundColor(color)
            }
            bottomSheetView.post{
                bottomSheetDialog.behavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }





    }

    private fun saveNote() {
                     if(fragmentSaveOrDeleteBinding.etNoteContent.text.toString().isEmpty() || fragmentSaveOrDeleteBinding.eTitle.text.toString().isEmpty())
                     {
                         Toast.makeText(activity,"Your Note is Empty",Toast.LENGTH_SHORT).show()
                     }
        else{
                        note = args.note
                         when(note){
                          null-> {
                              noteActivityViewModel.saveNote(
                                  Note(0,fragmentSaveOrDeleteBinding.eTitle.text.toString(),fragmentSaveOrDeleteBinding.etNoteContent.toString(),currentDate,color)
                              )
                              navController.navigate(SaveOrDeleteFragmentDirections.actionSaveOrDeleteFragmentToNoteFragment())
                          }
                             else ->
                             {
                                 //update
                             }
                         }

                     }



    }


}