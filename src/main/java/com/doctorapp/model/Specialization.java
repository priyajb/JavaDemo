package com.doctorapp.model;

public enum Specialization {
     ORTHO("ORTHOPEDICS"),
    PEDIA("PEDIATRICIAN"),
    PHYSICIAN("GENERAL PHYSICIAN"),
    RADIO("RADIOLOGY"),
    DERMA("DERMATOLOGIST"),
    NEURO("NEUROLOGIST");

    private String speciality;

    Specialization(String speciality) {
        this.speciality = speciality;
    }

    public String getSpeciality() {
        return speciality;
    }
}



