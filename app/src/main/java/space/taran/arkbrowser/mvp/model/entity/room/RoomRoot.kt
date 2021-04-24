package space.taran.arkbrowser.mvp.model.entity.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class RoomRoot (
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,
    var path: String
)