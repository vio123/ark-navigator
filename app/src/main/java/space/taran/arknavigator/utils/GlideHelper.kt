package space.taran.arknavigator.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.ortiz.touchview.TouchImageView
import com.squareup.picasso.Picasso
import space.taran.arknavigator.R
import space.taran.arknavigator.mvp.model.dao.common.PredefinedIcon
import java.nio.file.Path

fun imageForPredefinedIcon(icon: PredefinedIcon): Int =
    when(icon) {
        PredefinedIcon.FOLDER -> {
            R.drawable.ic_baseline_folder
        }
        PredefinedIcon.FILE -> {
            R.drawable.ic_file
        }
    }

fun loadImage(file: Path, container: ImageView) =
    Glide.with(container.context)
        .load(file.toFile())
        .into(container)
fun loadZoomImage(file: Path, container: TouchImageView) =
    Picasso.get().load(file.toFile()).into(container)