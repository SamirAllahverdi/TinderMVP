package mvp.tinder.entity;

import lombok.*;

@ToString
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class User {
    @NonNull
    private Integer id;
    @NonNull
    private String name;
    @NonNull
    private String surname;
    @NonNull
    private String picture;
    @NonNull
    private String job;
    private String username;
    private String password;
    private String time;
    private int daysAgo;

}
