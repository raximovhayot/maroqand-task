package uz.raximov.maroqandtask.payload.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorData {
    private String errorMsg;
    private String errorCode;
    private Integer httpStatus;

    public ErrorData(String errorMsg, Integer httpStatus) {
        this.errorMsg = errorMsg;
        this.httpStatus = httpStatus;
    }

    public ErrorData(String errorMsg, String errorCode) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
    }
}