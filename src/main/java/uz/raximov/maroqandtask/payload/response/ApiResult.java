package uz.raximov.maroqandtask.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Collections;
import java.util.List;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> {
    private Boolean success = false;
    private String message;
    private T data;
    private Integer totalPages;
    private List<ErrorData> errors;


    /**RESPONSE WITH BOOLEAN (SUCCESS OR FAIL) */
    private ApiResult(Boolean success) {
        this.success = success;
    }

    /**SUCCESS RESPONSE WITH DATA*/
    public ApiResult(T data, Boolean success) {
        this.data = data;
        this.success = success;
    }

    /**SUCCESS RESPONSE WITH DATA*/
    public ApiResult(T data,int totalPages, Boolean success) {
        this.data = data;
        this.success = success;
        this.totalPages = totalPages;
    }

    /**SUCCESS RESPONSE WITH DATA AND MESSAGE*/
    public ApiResult(T data, Boolean success, String message) {
        this.data = data;
        this.success = success;
        this.message = message;
    }

    /**SUCCESS RESPONSE WITH MESSAGE*/
    public ApiResult(String message) {
        this.message = message;
        this.success = Boolean.TRUE;
    }

    public ApiResult(String errorMsg, String errorCode) {
        this.success = false;
        this.errors = Collections.singletonList(new ErrorData(errorMsg, errorCode));
    }

    public ApiResult(String errorMsg, Integer httpStatus) {
        this.success = false;
        this.errors = Collections.singletonList(new ErrorData(errorMsg, httpStatus));
    }

    /**ERROR RESPONSE WITH ERROR DATA LIST*/
    public ApiResult(List<ErrorData> errors) {
        this.success = false;
        this.errors = errors;
    }

    public static <E> ApiResult<E> successResponse(E data) {
        return new ApiResult<>(data, true);
    }

    public static <E> ApiResult<E> successResponse(E data, int totalPages) {
        return new ApiResult<>(data, totalPages,true);
    }

    public static <E> ApiResult<E> successResponse(E data, String message) {
        return new ApiResult<>(data, true, message);
    }

    public static <E> ApiResult<E> successResponse() {
        return new ApiResult<>(true);
    }

    public static ApiResult<String> successResponse(String message) {
        return new ApiResult<>(message);
    }

    public static ApiResult<ErrorData> errorResponse(String errorMsg, String errorCode) {
        return new ApiResult<>(errorMsg, errorCode);
    }

    public static ApiResult<ErrorData> errorResponse(String errorMsg, Integer httpStatus) {
        return new ApiResult<>(errorMsg,httpStatus);
    }
}