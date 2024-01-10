package com.example.geministarter.database




import android.net.Uri
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter


class Converters {
    @TypeConverter
    fun fromUri(uri: Uri?): String? {
        return uri?.toString()
    }

    @TypeConverter
    fun toUri(uriString: String?): Uri? {
        return uriString?.let { Uri.parse(it) }
    }
}

@Entity(tableName = "photo_search")
data class PhotoSearchEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val query: String,
    val answer:String,
    val uriString: String
)