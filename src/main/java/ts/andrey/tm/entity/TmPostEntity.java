package ts.andrey.tm.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@Table(name = "tm_post")
public class TmPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "postSeq")
    @SequenceGenerator(name = "postSeq", sequenceName = "post_seq", allocationSize = 1)
    private int id;

    @OneToMany(mappedBy = "head", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<TmPostEntity> childPosts;

    @ManyToOne
    @JoinColumn(name = "id_head")
    private TmPostEntity head;

    private String title;

    private String message;

    @ManyToOne
    @JoinColumn(name = "id_owner")
    private UserInfoEntity owner;

}
