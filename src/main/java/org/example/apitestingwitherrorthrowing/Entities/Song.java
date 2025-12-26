package org.example.apitestingwitherrorthrowing.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    @Column(unique=true)
public String name ;
    @Column(unique=false,nullable=false)
public String artist ;
    @Column(unique=false,nullable=false)
public Date releaseDate ;
    @Column(unique=false,nullable=false)
public String genre ;
    @Column(unique=false,nullable=false)
    public String mood ;

}
