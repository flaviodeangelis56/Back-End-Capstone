package flaviodeangelis.noleggioAuto.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Vehicles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String imgVehicles;
    private int annoProduzione;
    private int cilindrata;
    private int maxPower;
    private double lunghezza;
    private double larghezza;
    private double altezza;
    private double passo;
    private int massa;
    private String ConsumiMisto;
    @OneToOne
    @JoinColumn(name = "reservation_id", referencedColumnName = "id")
    private Reservations reservations;
}
