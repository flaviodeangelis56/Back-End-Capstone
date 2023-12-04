package flaviodeangelis.noleggioAuto.exceptions;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;

@Getter
public class ErrorList extends RuntimeException {

    private List<ObjectError> errorList;

    public ErrorList(String message) {
        super(message);
    }

    public ErrorList(List<ObjectError> objList) {
        this.errorList = objList;
    }
}
