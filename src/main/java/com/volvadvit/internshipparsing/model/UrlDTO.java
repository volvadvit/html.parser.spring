package com.volvadvit.internshipparsing.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vadim Volkov
 * volvadvit@gmail.com
 */

@Entity
@Table(name = "url")
@Data
@NoArgsConstructor
public class UrlDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String url;
    @OneToMany(mappedBy = "sourceUrl", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<WordToCount> words = new HashSet<>();
}
