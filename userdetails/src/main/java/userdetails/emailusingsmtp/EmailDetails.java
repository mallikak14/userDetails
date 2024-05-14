package userdetails.emailusingsmtp;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailDetails {
    String toAddress;
    String subject;
    String body;
    String attachment;
    
}
