package com.google.codelabs.mdc.kotlin.shrine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.codelabs.mdc.kotlin.shrine.network.ProductEntry
import kotlinx.android.synthetic.main.shr_product_grid_fragment.view.*

class ProductGridFragment : Fragment() {
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment with the ProductGrid theme
        val view = inflater.inflate(R.layout.shr_product_grid_fragment, container, false)

        // Set up the RecyclerView
        setUpRecyclerView(view)

        return view
    }

    private fun setUpRecyclerView(view: View) {
        view.recycler_view.apply {
            setHasFixedSize(true)
            GridLayoutManager(context, 2, RecyclerView.VERTICAL, false)

            val largePadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing)
            val smallPadding = resources.getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small)

            addItemDecoration(ProductGridItemDecoration(largePadding, smallPadding))

            val products = ProductEntry.initProductEntryList(resources)
            view.recycler_view.adapter = ProductCardRecyclerViewAdapter(products)
        }
    }
}
