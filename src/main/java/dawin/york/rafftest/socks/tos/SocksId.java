package dawin.york.rafftest.socks.tos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocksId implements Serializable {
    private String color;
    private Integer cottonPart;

    public boolean isValid() {
        return !(cottonPart < 0 || cottonPart > 100 || color == null || color.isBlank());
    }
}
