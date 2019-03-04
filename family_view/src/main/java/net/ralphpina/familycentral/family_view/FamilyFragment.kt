package net.ralphpina.familycentral.family_view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.family_fragment.*
import net.ralphpina.familycentral.family.Family
import javax.inject.Inject

class FamilyFragment : Fragment() {

    @Inject lateinit var viewModelFactory: FamilyViewModel.Factory

    private lateinit var viewModel: FamilyViewModel

    private val familyListAdapter = FamilyListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.family_fragment, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler_view.adapter = familyListAdapter
    }

    // onAttach is always called before onActivityCreated
    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(FamilyViewModel::class.java)
        viewModel.family.observe(
            this,
            Observer<Family> { familyListAdapter.update(checkNotNull(it)) }
        )
    }
}
