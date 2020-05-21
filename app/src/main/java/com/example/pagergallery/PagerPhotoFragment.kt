package com.example.pagergallery

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import kotlinx.android.synthetic.main.fragment_pager_photo.*
import java.util.ArrayList

/**
 * A simple [Fragment] subclass.
 */
class PagerPhotoFragment : Fragment() {

    private var photoList: ArrayList<PhotoItem>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_pager_photo, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        photoList = arguments?.getParcelableArrayList<PhotoItem>("PHOTO_LIST")
        val position = arguments?.getInt("PHOTO_POSITION")
        PagerPhotoListAdapter().apply {
            viewPager2.adapter = this
            submitList(photoList)
        }
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            @SuppressLint("SetTextI18n")
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                tvPosition.text = "$position/${photoList?.size}"
            }
        })
        viewPager2.setCurrentItem(position ?: 0, false)
    }
}
