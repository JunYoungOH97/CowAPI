package toyspringboot.server.Domain.Entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class QnA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private String date;

    @ManyToOne
    @JoinColumn(name = "User_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "Answer_id")
    private Answer answer;
}
