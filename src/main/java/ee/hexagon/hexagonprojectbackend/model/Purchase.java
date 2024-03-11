package ee.hexagon.hexagonprojectbackend.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "purchase")
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long purchaseId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private ClientUser clientUser;

    @Column(name = "chapter")
    private Long chapter;

    @Column(name = "pagenumber")
    private Long pageNumber;
}
