package com.mdo.yoni.eshop.fragments

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.widget.AppCompatImageView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.mdo.yoni.eshop.MainActivity
import com.mdo.yoni.eshop.data.models.Item

import com.mdo.yoni.eshop.R
import com.mdo.yoni.eshop.data.internal.EShopDatabase
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ITEM = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [SingleItemFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [SingleItemFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class SingleItemFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var item: Item? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            item = it.getParcelable<Item>(ARG_ITEM)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    fun toggleSelected(v: View, b: Boolean) {
        if (v is FloatingActionButton) {
            (v as FloatingActionButton).backgroundTintList = ColorStateList.valueOf(Color
                    .parseColor(if (b) "#FFD700" else "#FFFFFF"));
        } else {
            v.setBackgroundResource(if (b) R.color.colorSelected else R.color.colorAccent)
        }
    }

    fun updateItemTag() {
        compareBtn?.tag = item
        dismissBtn?.tag = item
        acceptBtn?.tag = item

    }

    private var compareBtn: FloatingActionButton? = null
    private var dismissBtn: FloatingActionButton? = null
    private var acceptBtn: FloatingActionButton? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val v: View = inflater.inflate(R.layout.item_card_view, container, false)
        compareBtn = v.findViewById<FloatingActionButton>(R.id.compareBtn)
        dismissBtn = v.findViewById<FloatingActionButton>(R.id.dismissBtn)
        acceptBtn = v.findViewById<FloatingActionButton>(R.id.acceptBtn)
        toggleSelected(compareBtn!!, item?.incompare == 1)
        toggleSelected(dismissBtn!!, item?.discarded == 1)
        toggleSelected(acceptBtn!!, item?.incart == 1)
        compareBtn?.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View) {
                (context as MainActivity).itemAddToCompare(v)
                toggleSelected(v, item?.incompare == 1)
            }
        })
        dismissBtn?.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View) {
                (context as MainActivity).itemDismiss(v)
                toggleSelected(v, item?.discarded == 1)
            }
        })
        acceptBtn?.setOnClickListener(object : android.view.View.OnClickListener {
            override fun onClick(v: android.view.View) {
                (context as MainActivity).itemAddToCart(v)
                toggleSelected(v, item?.incart == 1)
            }
        })

        Glide.with(context).load(item?.url).into(v.findViewById<AppCompatImageView>(R.id.profileImageView))
        return v
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            doAsync {
                val dbItem = EShopDatabase.getInstance(context!!).itemsDao().get(item!!.id)
                item = if (dbItem == null) item else dbItem
                uiThread {
                    updateItemTag()
                }
            }
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SingleItemFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(item: Item) =
                SingleItemFragment().apply {
                    arguments = Bundle().apply {
                        putParcelable(ARG_ITEM, item)
                    }
                }
    }
}
