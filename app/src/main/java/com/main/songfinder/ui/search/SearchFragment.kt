package com.main.songfinder.ui.search


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.songfinder.R


/**
 * Questa classe fornisce il fragment  della main activity
 * @author umbertodomenicociccias
 * */
class SearchFragment : Fragment() {

    private val viewModel by lazy {
        ViewModelProvider(this)[SearchViewModel::class.java]
    }

    private lateinit var adapter: SearchAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_search, container, false)
    }

    @Deprecated("Deprecated in Java")
    @Suppress("DEPRECATION")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val layoutManager = LinearLayoutManager(activity)
        val recyclerView: RecyclerView = this.requireView().findViewById(R.id.recyclerView)
        recyclerView.layoutManager = layoutManager
        adapter = SearchAdapter(this, viewModel.responseList)
        recyclerView.adapter = adapter
        val searchResponseEdit: EditText = this.requireView().findViewById(R.id.searchResponseEdit)

        searchResponseEdit.addTextChangedListener { editable ->
            val content = editable.toString()
            if (content.isNotEmpty()) {
                viewModel.searchResponses(content)
            } else {
                recyclerView.visibility = View.GONE
                viewModel.responseList.clear()
                adapter.notifyDataSetChanged()
            }
        }

        viewModel.searchResponseLiveData.observe(viewLifecycleOwner) { result ->
            val search = result.getOrNull()
            if (search != null) {
                recyclerView.visibility = View.VISIBLE
                viewModel.responseList.clear()
                viewModel.responseList.addAll(search.response.hits)
                adapter.notifyDataSetChanged()
            } else {
                Toast.makeText(activity, "Cannot find any result", Toast.LENGTH_SHORT).show()
                result.exceptionOrNull()?.printStackTrace()
            }
        }
    }

}