package ts.andrey.tm.data.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class TmPost {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeq")
    @SequenceGenerator(name = "postSeq", sequenceName = "post_seq", allocationSize = 1)
    private int id;

    @OneToMany(mappedBy = "head", cascade = CascadeType.ALL)
    private List<TmPost> childPosts;

    @ManyToOne
    @JoinColumn(name = "id_head")
    private TmPost head;

    private String title;

    private String message;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private UserInfo owner;

}
