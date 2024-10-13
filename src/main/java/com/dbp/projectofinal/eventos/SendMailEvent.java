package com.dbp.projectofinal.eventos;

import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.util.Map;

@Getter
@Setter
public class SendMailEvent extends ApplicationEvent {
    private final Map<String, Object> datos;

    public SendMailEvent(Map<String, Object> datos){
        super(datos);
        this.datos = datos;
    };

}

