package fr.myitworld.userservice.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ErrorDetails implements Serializable {

    private Date timestamp;
    private String message;
    private String details;

}

