package com.example.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String rollNumber;
    private String name;
    private String department;
    private Integer year;

    public Student() {}

    public static Builder builder(){ return new Builder(); }
    public static class Builder{
        private final Student s = new Student();
        public Builder id(Long id){ s.setId(id); return this; }
        public Builder rollNumber(String v){ s.setRollNumber(v); return this; }
        public Builder name(String v){ s.setName(v); return this; }
        public Builder department(String v){ s.setDepartment(v); return this; }
        public Builder year(Integer v){ s.setYear(v); return this; }
        public Student build(){ return s; }
    }

    public Long getId(){ return id; }
    public void setId(Long id){ this.id = id; }
    public String getRollNumber(){ return rollNumber; }
    public void setRollNumber(String rollNumber){ this.rollNumber = rollNumber; }
    public String getName(){ return name; }
    public void setName(String name){ this.name = name; }
    public String getDepartment(){ return department; }
    public void setDepartment(String department){ this.department = department; }
    public Integer getYear(){ return year; }
    public void setYear(Integer year){ this.year = year; }

    @Override public boolean equals(Object o){ if(this==o) return true; if(!(o instanceof Student)) return false; Student s=(Student)o; return Objects.equals(id,s.id); }
    @Override public int hashCode(){ return Objects.hashCode(id); }
}
