package org.iesvdm.videoclub.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String texto;
    @ManyToOne(fetch = FetchType.LAZY, optional = false )
            @JoinColumn(name = "id_tutorial", nullable = false, foreignKey = @ForeignKey(name = "FK_TUTORIAL"))
            @JsonIgnore

    Tutorial tutorial;
}
