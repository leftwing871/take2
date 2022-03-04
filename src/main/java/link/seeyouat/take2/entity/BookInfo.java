package link.seeyouat.take2.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BookInfo {
    String name;
    String ISBN13;
    Float rating;
}
