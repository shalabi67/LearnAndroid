package com.androidlibrary.database.column


/**
 * Created by mohammad on 11/21/2017.
 */
open class Column(val columnName: String, val type: ColumnTypeEnum, val properties: Array<ColumnProperty>) {
    companion object {
        fun getInvalidColumn() : Column {
            return Column("", ColumnTypeEnum.Invalid, ColumnPropertyBuilder().build())
        }
    }

    fun getColumns(columns: Array<Column>): Array<String> {
        val names = arrayOf<String>()
        var i = 0
        for (column in columns) {
            names[i] = column.columnName
            i++
        }
        return names
    }

    fun isPrimaryKey() : Boolean = isKey(ColumnPropertyEnum.PrimaryKey)
    fun isAutoIncrement() : Boolean = isKey(ColumnPropertyEnum.Autoincrement)
    private fun isKey(columnProperty : ColumnPropertyEnum) : Boolean {
        val result = properties.find { property -> property.columnPropertyEnum == ColumnPropertyEnum.PrimaryKey }
        return result != null
    }

    override fun toString(): String {
        return columnName + " " + getColumnType() + " " + getColumnProperties()
    }

    private fun getColumnType(): String {
        when (type) {
            ColumnTypeEnum.Int -> return "INTEGER"
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

    open fun getColumnNameForQuery() : String  =  columnName
}