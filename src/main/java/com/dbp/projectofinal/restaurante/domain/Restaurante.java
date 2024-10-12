package com.dbp.projectofinal.restaurante.domain;
import com.dbp.projectofinal.carta.domain.Carta;
import com.dbp.projectofinal.propietario.domain.Propietario;
import com.dbp.projectofinal.ubicacion.domain.Ubicacion;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_restaurante;

    private String nombre_restaurante;
    private String horario;
    private String tipoRestaurante;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "propietario_id")
    private Propietario propietario;

    @OneToOne
    private Carta carta;

    private Double calificacion_promedio;

    @ManyToOne
    @JoinColumn(name = "id_ubicacion")
    private Ubicacion ubicacion;

    public Restaurante(Long id_restaurante) {
        this.id_restaurante = id_restaurante;
    }


}
