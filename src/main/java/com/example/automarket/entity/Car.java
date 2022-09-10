package com.example.automarket.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "car", schema = "public")
public class Car {

    @Id
    @Column(name = "car_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int carId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;

    @Column(name = "model")
    private String model;

    @Column(name = "manufacture")
    private LocalDate manufacture;

    public Car(Company company, String model, LocalDate manufacture) {
        this.company = company;
        this.model = model;
        this.manufacture = manufacture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return carId == car.carId && Objects.equals(model, car.model) && Objects.equals(manufacture, car.manufacture) && Objects.equals(company, car.company);
    }

    @Override
    public int hashCode() {
        return Objects.hash(carId, model, manufacture, company);
    }

    @Override
    public String toString() {
        return "Car{" +
                "carId=" + carId +
                //", company=" + company +
                ", model='" + model + '\'' +
                ", manufacture=" + manufacture +
                '}';
    }
}