package org.iesvdm.videoclub.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.yaml.snakeyaml.constructor.SafeConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder

@Table(name = "tutorial", schema = "videoclub_jpa", indexes = {@Index( name = "index_itulo", columnList = "titulo", unique = false)})
public class Tutorial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_tuto")
    private long id;
    @Column(name = "titulo", length = 50)
    private String titulo;
    @Column(name = "descripcion", length = 150)
    private String descripcion;
    @Column(name = "publi")
    private Boolean publicado;
    private Date fechaPublicacion;
    @OneToMany(mappedBy = "tutorial", fetch = FetchType.EAGER)
    @OnDelete( action= OnDeleteAction.CASCADE)
    private Set<Comentario> comentarios;

    public Set<Comentario> addComentario(Comentario comentario){
        comentario.setTutorial(this);
        this.comentarios.add(comentario);
          return this.comentarios;
    }
}
