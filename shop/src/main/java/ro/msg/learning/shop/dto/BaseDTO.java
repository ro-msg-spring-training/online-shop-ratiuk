package ro.msg.learning.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BaseDTO implements Serializable {
    private static final long serialVersionUID = 6365527550641297447L;
    protected Long id;
}
