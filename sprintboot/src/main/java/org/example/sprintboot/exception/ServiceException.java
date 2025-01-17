package org.example.sprintboot.exception;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ServiceException  extends RuntimeException {
    private final String code;
    public ServiceException(String message) {
        super(message);
        this.code = "500";  // 假设500为service层的通用返回码
    }

    public ServiceException(String code, String message) {
        super(message);
        this.code = code;
    }
}
