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
import androidx.recyclerview.widget.LinearLayoutManager
import com.nikhil.currencyconversion.R
import com.nikhil.currencyconversion.databinding.FragmentHistoryBinding
import com.nikhil.currencyconversion.presentation.adapter.CurrencyAdapter
import com.nikhil.currencyconversion.presentation.adapter.OtherCurriencesAdapter
import com.nikhil.currencyconversion.presentation.viewmodel.CurrencyViewModel
import com.nikhil.currencyconversion.util.Resource

class History : Fragment() {
    lateinit var binding: FragmentHistoryBinding
    private val currencyViewModel: CurrencyViewModel by activityViewModels()

    private val adapter by lazy {
        CurrencyAdapter()
    }
    private val adapterOtherCurriencesAdapter by lazy {
        OtherCurriencesAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_history, container, false)

        observe()
        currencyViewModel.getAllList()
        currencyViewModel.symbol?.let { currencyViewModel.getOtherCurrencies(it) }

        binding.button2.setOnClickListener {
            Navigation.findNavController(binding.root).navigate(R.id.action_history_to_conversionScreen)
        }
        return binding.root
    }

    private fun observe() {
    observerCurrencyList()
       observerOtherCurrencyList()
    }

    private fun observerCurrencyList() {
        currencyViewModel.list.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(activity, it.data?.size.toString(), Toast.LENGTH_SHORT).show()
                    binding.recyRecord.adapter = adapter
                    binding.recyRecord.layoutManager = LinearLayoutManager(activity)
                    it.data?.let { it1 -> adapter.addItems(it1) }
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


    private fun observerOtherCurrencyList() {
        currencyViewModel.otherCurrencies.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Success -> {
                    binding.progressBar.visibility = View.GONE
                    binding.recyOtherCurrency.adapter = adapterOtherCurriencesAdapter
                    binding.recyOtherCurrency.layoutManager = LinearLayoutManager(activity)
                    it.data?.let { it1 -> adapterOtherCurriencesAdapter.addItems( it1.subList(0,10)) }
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

}