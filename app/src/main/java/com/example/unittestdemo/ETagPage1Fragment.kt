package com.example.unittestdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.frag_etag_page_1.*
import kotlinx.android.synthetic.main.lay_etag_car_edit.*

class ETagPage1Fragment : Fragment() {

    private val viewModel: ETagPage1ViewModel by viewModels { ETagViewModelFactory() }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.frag_etag_page_1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getIsDepositBtnEnable().observe(viewLifecycleOwner, Observer { isEnable ->
            btnDeposit.isEnabled = isEnable
        })

        editCar1.addTextChangedListener(object : TextWatchAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.inputEditCar1(s.toString())
            }
        })

        editCar2.addTextChangedListener(object : TextWatchAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.inputEditCar2(s.toString())
            }
        })

        editUserId.addTextChangedListener(object : TextWatchAdapter() {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                viewModel.inputEditUserId(s.toString())
            }
        })
    }

    enum class ErrorType {
        CAR_ERROR,
        USER_ID_ERROR,
        GET_UUID_ERROR
    }
}