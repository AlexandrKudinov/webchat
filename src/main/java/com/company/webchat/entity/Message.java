package com.company.webchat.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "messages")
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "content")
    private String content;

    @ManyToOne(cascade = {
            CascadeType.MERGE,
            CascadeType.REFRESH},fetch = FetchType.EAGER)
    @JoinColumn (name = "user_id")
    @JsonIgnoreProperties("messages")
    private User user;

}
