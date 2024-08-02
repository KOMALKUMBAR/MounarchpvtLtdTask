package com.android1.mounachpvttask1

import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.net.Uri
import android.provider.DocumentsContract
import android.provider.MediaStore

object FilePath {

    fun getPath(context: Context, uri: Uri): String? {
        // Check if the URI is a document URI
        if (DocumentsContract.isDocumentUri(context, uri)) {
            // Get the document ID
            val documentId = DocumentsContract.getDocumentId(uri)

            // Check the type of document
            when {
                isExternalStorageDocument(uri) -> {
                    // External storage document
                    val split = documentId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]
                    if ("primary".equals(type, ignoreCase = true)) {
                        return "${android.os.Environment.getExternalStorageDirectory()}/${split[1]}"
                    }
                }
                isDownloadsDocument(uri) -> {
                    // Downloads document
                    val contentUri = Uri.parse("content://downloads/public_downloads")
                    val contentUriWithId = ContentUris.withAppendedId(contentUri, documentId.toLong())
                    return getDataColumn(context, contentUriWithId, null, null)
                }
                isMediaDocument(uri) -> {
                    // Media document
                    val split = documentId.split(":".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                    val type = split[0]
                    val contentUri = when (type) {
                        "image" -> MediaStore.Images.Media.EXTERNAL_CONTENT_URI
                        "video" -> MediaStore.Video.Media.EXTERNAL_CONTENT_URI
                        "audio" -> MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
                        else -> null
                    }
                    val selection = "_id=?"
                    val selectionArgs = arrayOf(split[1])
                    return contentUri?.let { getDataColumn(context, it, selection, selectionArgs) }
                }
            }
        } else if ("content".equals(uri.scheme, ignoreCase = true)) {
            // Content URI
            return getDataColumn(context, uri, null, null)
        } else if ("file".equals(uri.scheme, ignoreCase = true)) {
            // File URI
            return uri.path
        }
        return null
    }

    private fun getDataColumn(context: Context, uri: Uri, selection: String?, selectionArgs: Array<String>?): String? {
        val projection = arrayOf(MediaStore.Images.Media.DATA)
        context.contentResolver.query(uri, projection, selection, selectionArgs, null)?.use { cursor ->
            if (cursor.moveToFirst()) {
                val columnIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
                return cursor.getString(columnIndex)
            }
        }
        return null
    }

    private fun isExternalStorageDocument(uri: Uri) = "com.android.externalstorage.documents" == uri.authority
    private fun isDownloadsDocument(uri: Uri) = "com.android.providers.downloads.documents" == uri.authority
    private fun isMediaDocument(uri: Uri) = "com.android.providers.media.documents" == uri.authority

}