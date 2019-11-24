package com.rubik.brewmoment.ui.logout

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.rubik.brewmoment.R

class LogOutFragment : Fragment() {

    private lateinit var logOutViewModel: LogOutViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        logOutViewModel =
            ViewModelProviders.of(this).get(LogOutViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_send, container, false)
        val textView: TextView = root.findViewById(R.id.text_send)
        logOutViewModel.text.observe(this, Observer {
            textView.text = it
        })
        return root
    }
}