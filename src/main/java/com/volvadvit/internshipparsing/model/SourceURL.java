package com.volvadvit.internshipparsing.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Vadim Volkov volvadvit@gmail.com
 * @since 10.10.2021
 */

@Entity
@Table(name = "url")
@Data
@NoArgsConstructor
public class SourceURL {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private URL url;
    @OneToMany(mappedBy = "sourceUrl", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<WordToCount> words = new HashSet<>();
}