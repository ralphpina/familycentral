package net.ralphpina.familycentral.family_view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import net.ralphpina.familycentral.family.Family
import net.ralphpina.familycentral.family.Member
import net.ralphpina.familycentral.family_view.databinding.FamilyMemberListItemBinding


internal class FamilyMemberViewHolder(val dataBinding: FamilyMemberListItemBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    fun bind(member: Member) {
        dataBinding.setVariable(BR.member, member)
        dataBinding.executePendingBindings()

        Glide.with(dataBinding.photo)
            .load(member.photo)
            .circleCrop()
            .placeholder(R.drawable.ic_person_24px)
            .into(dataBinding.photo)

        val map = mutableMapOf<String, Int>()

    }
}

internal class FamilyListAdapter : RecyclerView.Adapter<FamilyMemberViewHolder>() {

    private var family: Family? = null

    fun update(family: Family) {
        this.family = family
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FamilyMemberViewHolder {
        val binding: FamilyMemberListItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.family_member_list_item,
            parent,
            false
        )
        return FamilyMemberViewHolder(binding)
    }

    override fun getItemCount() = family?.members?.size ?: 0

    override fun onBindViewHolder(holder: FamilyMemberViewHolder, position: Int) {
        val member = checkNotNull(family).members[position]
        holder.bind(member)
    }
}