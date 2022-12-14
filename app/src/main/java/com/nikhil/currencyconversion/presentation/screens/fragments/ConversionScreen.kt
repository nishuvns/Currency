package com.nikhil.currencyconversion.presentation.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.Navigation
import com.nikhil.currencyconversion.R
import com.nikhil.currencyconversion.data.model.Symbols
import com.nikhil.currencyconversion.databinding.FragmentConversionScreenBinding
import com.nikhil.currencyconversion.presentation.adapter.SymbolAdapter
import com.nikhil.currencyconversion.presentation.viewmodel.CurrencyViewModel
import com.nikhil.currencyconversion.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ConversionScreen : Fragment() {
    lateinit var binding:FragmentConversionScreenBinding
    private val currencyViewModel: CurrencyViewModel by activityViewModels()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding =  DataBindingUtil.inflate(inflater, R.layout.fragment_conversion_screen, container, false)

        observe()


        binding.btnSwap.setOnClickListener {
            if (binding.fromSpin.selectedItem != null && binding.twoSpin.selectedItem != null) {
                binding.fromSpin.setSelection(binding.twoSpin.selectedItemPosition)
                binding.twoSpin.setSelection(binding.fromSpin.selectedItemPosition)
            }
        }


        binding.btnDetail.setOnClickListener {
            if(binding.fromSpin.selectedItem.toString().isNullOrBlank().not())
            {
                currencyViewModel.conversionByCountryId(
                    (binding.fromSpin.selectedItem as Symbols).countryId,
                    (binding.twoSpin.selectedItem as Symbols).countryId,
                    binding.edtCurrencyFrom.text.toString()
                )
            }

        }
//
        binding.btnHistory.setOnClickListener {
            if((binding.fromSpin.selectedItem as Symbols).countryId.isNullOrBlank().not())
            currencyViewModel.symbol =  (binding.fromSpin.selectedItem as Symbols).countryId

            Navigation.findNavController(binding.root).navigate(R.id.action_conversionScreen_to_history)
        }
        return binding.root
    }

    private fun observe() {
        observeUI()
        observerCurrencyConversion()
    }

    private fun observeUI() {
        currencyViewModel.symbolData.observe(this) {
            when (it) {
                is Resource.Success -> {
                  binding.progressBar.visibility = View.GONE
                    val data = it.data
                    binding.fromSpin.adapter = context?.let { it1 -> data?.let { it2 -> SymbolAdapter(it1, it2) } }
                    binding.twoSpin.adapter = context?.let { it1 -> data?.let { it2 -> SymbolAdapter(it1, it2) } }

                }
                is Resource.Error -> {
                    binding.progressBar.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
            }

        }
    }

    private fun observerCurrencyConversion()
    {
        currencyViewModel.convertResult.observe(viewLifecycleOwner){
            when(it)
            {
                is Resource.Success->{
                    binding.progressBar.visibility = View.GONE
                  val  d= it.data?.result
                    binding.edtCurrencyTwo.setText(d   .toString())
                    binding.textView.text= "From Country ${it.data?.query?.from} To Country ${it.data?.query?.to} \n Total Conversion Amount is ${it.data?.result.toString()}"


                }

                is Resource.Error->{
                    binding.progressBar.visibility = View.GONE
                    it.message?.let { message ->
                        Toast.makeText(activity, message, Toast.LENGTH_LONG).show()
                    }
                }
                is Resource.Loading->{
                    binding.progressBar.visibility = View.VISIBLE
                }
            }
        }
    }


}