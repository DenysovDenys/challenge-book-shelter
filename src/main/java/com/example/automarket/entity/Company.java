package com.example.automarket.entity;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Getter
@Table(name = "company", schema = "public")
public class Company {

    @Id
    @Column(name = "company_id")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int companyId;

    @Column(name = "company_name")
    private String companyName;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Car> cars = new ArrayList<>();

    public Company(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId=" + companyId +
                ", companyName='" + companyName + '\'' +
                ", cars=" + cars +
                '}';
    }
}
