package com.vti.testing.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "`Group`")
@Data
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "group_id")
    private int id;

    @Column(name = "group_name", length = 50, nullable = false, unique = true)
    private String groupName;

    @Column(name = "`member`")
    private int member;

    @ManyToOne
    @JoinColumn(name = "creator", referencedColumnName = "user_id")
    private User creator;

    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(pattern = "dd-MM-YYYY")
    @Column(name = "create_date")
    private Date date;
}
