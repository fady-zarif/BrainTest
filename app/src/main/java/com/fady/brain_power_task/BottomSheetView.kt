package com.fady.brain_power_task

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.fady.brain_power_task.model.Athlete
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

import kotlinx.android.synthetic.main.bottom_sheet_view.view.*

class BottomSheetView(private val athlete: Athlete) : BottomSheetDialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        var view = inflater.inflate(R.layout.bottom_sheet_view, container, false)
        initView(view)
        return view
    }

    override fun getTheme(): Int = R.style.BaseBottomSheetDialog

    private fun initView(view: View) {
        view.tvBrief.text = athlete.brief
        view.tvName.text = athlete.name

        if (!athlete.image.isNullOrEmpty()) {
            Glide.with(this).load(athlete.image)
                .placeholder(R.drawable.loading_image).into(view.imgCircleAthlete)
        } else {
            view.imgCircleAthlete.visibility = View.GONE
        }
    }
}