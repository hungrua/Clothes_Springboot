package com.example.clothes.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "clothes")
public class Clothes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String clothesCode;
    private String clothesName;
    private String description;
    private boolean isDeleted;
    private Date createdAt;
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "created_by")
    private User createdBy;

    @ManyToOne
    @JoinColumn(name = "updated_by")
    private User updatedBy;

    @ManyToMany
    @JoinTable(
            name = "clothes_type_details",
            joinColumns = @JoinColumn(name = "clothes_id"),
            inverseJoinColumns = @JoinColumn(name = "type_details_id")
    )
    private Set<TypeDetails> typeDetails;

    @OneToMany(mappedBy = "clothes", cascade = CascadeType.ALL)
    private List<ClothesVariants> clothesVariantsList;
}
