package link.seeyouat.take2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class APIResultProtocol {
    int code;
    Object returnValue;
}
