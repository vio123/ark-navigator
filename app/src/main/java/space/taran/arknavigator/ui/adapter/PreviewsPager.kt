package space.taran.arknavigator.ui.adapter

import android.util.Log
import android.view.*
import androidx.core.view.GestureDetectorCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_resources.view.*
import kotlinx.android.synthetic.main.item_image.view.*
import space.taran.arknavigator.R
import space.taran.arknavigator.mvp.presenter.adapter.PreviewsList
import space.taran.arknavigator.mvp.view.item.PreviewItemViewHolder

class PreviewsPager(val presenter: PreviewsList) : RecyclerView.Adapter<PreviewItemViewHolder>() {
    var nr=0
    override fun getItemCount() = presenter.getCount()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PreviewItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_image,
                parent,
                false))

    override fun onBindViewHolder(holder: PreviewItemViewHolder, position: Int) {
        holder.pos = position
        presenter.bindView(holder)
        val gestureDetector = getGestureDetector(holder)
        holder.itemView.layout_root1.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_POINTER_DOWN)
                view.performClick()
            Log.i("tap","tapped")
            gestureDetector.onTouchEvent(motionEvent)
            return@setOnTouchListener true
        }
        holder.itemView.iv_image.setOnTouchListener { view, motionEvent ->
            if (motionEvent.action == MotionEvent.ACTION_UP)
                view.performClick()
            Log.i("tap","tapped1")
            gestureDetector.onTouchEvent(motionEvent)
            return@setOnTouchListener true
        }
    }

    fun removeItem(position: Int) {
        val items = presenter.items().toMutableList()
        items.removeAt(position)
        presenter.updateItems(items.toList())
        super.notifyItemRemoved(position)
    }

    private fun getGestureDetector(holder: PreviewItemViewHolder): GestureDetectorCompat {
        val listener = object : GestureDetector.SimpleOnGestureListener() {
            override fun onDown(e: MotionEvent?): Boolean {
                return true
            }
            override fun onSingleTapConfirmed(e: MotionEvent?): Boolean {
                presenter.itemClicked(holder.pos)
                if(nr==0)
                {
                    nr=1;
                    holder.itemView.iv_image1.visibility= View.GONE
                    holder.itemView.iv_image.visibility= View.VISIBLE
                }else{
                    nr=0;
                    holder.itemView.iv_image.visibility= View.GONE
                    holder.itemView.iv_image1.visibility= View.VISIBLE
                }
                return true
            }
        }
        return GestureDetectorCompat(holder.itemView.context, listener)
    }
}