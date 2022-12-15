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

class FikaHomeFragment : Fragment() {
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
            setContent {
                SetText("Sisi")
            }
        }

    @Composable
    fun SetText(name: String) {
        Text(text = "$name's Fika Week")
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
       SetText("Sisi")
    }

}