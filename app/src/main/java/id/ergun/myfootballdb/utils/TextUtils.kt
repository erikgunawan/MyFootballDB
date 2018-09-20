package id.ergun.myfootballdb.utils

fun formatString(text: String?, oldValue: String, newValue: String): String {
    val checkText: String = text ?: ""
    return checkText.replace(oldValue,newValue)
}