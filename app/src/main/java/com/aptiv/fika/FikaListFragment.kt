package com.aptiv.fika

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.tooling.preview.Preview
import androidx.fragment.app.Fragment

class FikaListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            SetList()
        }
    }

    @Composable
    fun SetList() {
        Text(text = "List should be here")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        SetList()
    }

}