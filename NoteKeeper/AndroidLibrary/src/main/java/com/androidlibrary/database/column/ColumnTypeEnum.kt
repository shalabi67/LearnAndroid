package com.androidlibrary.database.column

/**
 * Created by mohammad on 11/21/2017.
 */
enum class ColumnTypeEnum {
    Int,
    Text,
    Blob,
    Decimal,
    DateTime,

    Invalid  //used to specify an invalid column for error cases
}