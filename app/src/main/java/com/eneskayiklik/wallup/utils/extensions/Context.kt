package com.eneskayiklik.wallup.utils.extensions

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import java.io.ByteArrayOutputStream

fun Context.getImageUri(bitmap: Bitmap?): Uri {
    val bytes = ByteArrayOutputStream()
    bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
    val path = MediaStore.Images.Media.insertImage(
        contentResolver, bitmap, System.currentTimeMillis().toString(), null
    )
    return Uri.parse(path)
}