package com.example.uas201804018

import android.provider.BaseColumns

object DBInfoOrder {
    class UserInput: BaseColumns {
        companion object {

            val TABLE_NAME = "product"
            val COL_IDP = "idp"
            val COL_NAMA = "nama"
            val COL_HARGA = "harga"
            val COL_WARNA = "warna"

        }
    }
}