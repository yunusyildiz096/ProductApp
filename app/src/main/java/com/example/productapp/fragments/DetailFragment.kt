package com.example.productapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.example.productapp.R
import com.example.productapp.databinding.DetailProductBinding
import com.example.productapp.util.downloadFromUrl
import com.example.productapp.viewmodel.DetailViewModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BottomSheetDialogFragment() {

    private var fragmentBinding : DetailProductBinding? = null
    private val args : DetailFragmentArgs by navArgs()
    private val viewModel : DetailViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.detail_product,container,false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = DetailProductBinding.bind(view)
        fragmentBinding = binding
        val detail = args.product
        with(fragmentBinding!!){

                detailPrice.text = "${detail.price.toString()}$"
                detailCategory.text = detail.category
                title.text = detail.title
                detailDescription.text = detail.description
                detailImage.downloadFromUrl(detail.image, progressDrawable = CircularProgressDrawable(requireContext()))

                cancelTv.setOnClickListener {
                    dismiss()
                }
                detailAddBasketButton.setOnClickListener {
                    viewModel.addToBasket(detail)
                    dismiss()
                }
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        fragmentBinding = null
    }
}