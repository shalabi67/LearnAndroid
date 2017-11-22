package com.androidlibrary.database.column


/**
 * Created by mohammad on 11/21/2017.
 */
class Column(private val columnName: String, private val type: ColumnTypeEnum, private val properties: Array<ColumnProperty>) {

    fun getColumns(columns: Array<Column>): Array<String> {
        val names = arrayOf<String>()
        var i = 0
        for (column in columns) {
            names[i] = column.columnName
            i++
        }
        return names
    }

    override fun toString(): String {
        return columnName + " " + getColumnType() + " " + getColumnProperties()
    }

    private fun getColumnType(): String {
        when (type) {
            ColumnTypeEnum.Int -> return "int"
            ColumnTypeEnum.Text -> return "text"
            ColumnTypeEnum.Blob -> return "blob"
            ColumnTypeEnum.Decimal -> return "decimal"
            ColumnTypeEnum.DateTime -> return "datetime"
            else -> return ""
        }
    }

    private fun getColumnProperties(): String {
        if (properties == null)
            return ""
        var s = ""
        for (cp in properties) {
            s += cp.toString() + " "
        }
        return s
    }
}