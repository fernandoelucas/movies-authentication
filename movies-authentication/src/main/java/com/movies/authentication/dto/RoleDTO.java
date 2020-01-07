package com.movies.authentication.dto;

public class RoleDTO {

    private String name;

    public RoleDTO(String name) {
        this.name = name;
    }

    public RoleDTO() {
        //Default Constructor
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleDTO roleDTO = (RoleDTO) o;

        return name != null ? name.equals(roleDTO.name) : roleDTO.name == null;
    }

    @Override
    public int hashCode() {
        return name != null ? name.hashCode() : 0;
    }
}
