package mvp.tinder.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private int who;
    private int whom;
    private String text;
    private String time;
}
