package utils

import androidx.annotation.Keep
import com.google.gson.Gson
import okhttp3.ResponseBody

@Keep
class ErrorResponse(
    /**
     * Идентификатор ошибки
     * генерится бэком при ошибке
     */
    val uid: String?,
    /**
     * Код ошибки
     */
    val message: String?,
    /**
     * Описание ошибки, приходит не всегда
     */
    val description: String?,
    /**
     * Описание ошибки, приходит в авторизации
     */
    val error_description: String?,
    /**
     * Ошибки валидации, приходят не всегда
     */
    val fieldErrors: List<FieldError>?,

    /**
     * Описание ошибки
     */
    val error: String?,
    /**
     * Описание ошибки
     */
    val errorDescription: String?
) {

    /**
     * Дефолтная обработка ответа
     */
    fun print(default: String = "mobileCommonError"): String =
        if (!fieldErrors.isNullOrEmpty()) {
            fieldErrors.mapNotNull { it.message }.joinToString(", ")
        } else {
            message ?: default
        }

    companion object {
        /**
         * Парсинг ответа сервера вручную в объект [ErrorResponse]
         */
        fun from(response: ResponseBody?): ErrorResponse? = try {
            Gson().fromJson(response?.charStream(), ErrorResponse::class.java)
        } catch (e: Exception) {
            null
        } finally {
            response?.close()
        }
    }
}

/**
 * [ErrorResponse.print] для nullable-type
 */
fun ErrorResponse?.print(
    default: String = "mobileCommonError"
): String = this?.print(default) ?: default

/**
 * Ошибки полей валидации
 * может прийти сразу несколько
 */
@Keep
data class FieldError(
    val type: String?,
    val objectName: String?,
    val field: String?,
    val message: String?,
    val documentReference: String?
)