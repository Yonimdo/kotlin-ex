package com.mdo.yoni.eshop.fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.mdo.yoni.eshop.R
import com.mdo.yoni.eshop.adapters.BrowsePageAdapter
import com.mdo.yoni.eshop.data.internal.EShopDatabase
import com.mdo.yoni.eshop.data.models.Item
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [CartViewFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [CartViewFragment.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class CompareFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart_view, container, false)
    }

    private lateinit var pagerAdapter: BrowsePageAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private lateinit var list: List<Item>
    var viewPager: ViewPager? = null

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (isVisibleToUser) {
            val position = viewPager?.currentItem
            doAsync {
                list = EShopDatabase.getInstance(context!!).itemsDao().getAllInCompare()
                uiThread {
                    if (list == null || list.isEmpty()) {
                        view!!.findViewById<Button>(R.id.empty).visibility = View.VISIBLE
                        viewPager?.adapter = null
                    } else {
                        view!!.findViewById<Button>(R.id.empty).visibility = View.GONE
                        viewPager?.adapter = BrowsePageAdapter(childFragmentManager, list)
                        if (position != 0 && position != null) {
                            viewPager?.currentItem = if (position.compareTo(list.size) == 1) list.size - 1 else position
                        }                    }
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        viewPager = view!!.findViewById(R.id.viewPager)
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
         * @return A new instance of fragment CartViewFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
                CompareFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}
