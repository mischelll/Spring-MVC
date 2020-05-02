package springworkshop.demo.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "hero not found")
public class HeroNotFoundException extends RuntimeException{
    public HeroNotFoundException(String message) {
        super(message);
    }
}
