package toyspringboot.server.Domain.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Answer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String answer;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @JsonIgnore
    @OneToOne(mappedBy = "answer", cascade = CascadeType.ALL)
    private QnA qnA;
}
