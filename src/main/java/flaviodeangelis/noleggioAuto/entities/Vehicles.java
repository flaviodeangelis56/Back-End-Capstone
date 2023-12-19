package flaviodeangelis.noleggioAuto.entities;

import flaviodeangelis.noleggioAuto.Enum.VehicleAvailability;
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
    private String marca;
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
    private int prezzo;
    private String descrizione;
    @Enumerated(EnumType.STRING)
    private VehicleAvailability vehicleAvailability;
}
