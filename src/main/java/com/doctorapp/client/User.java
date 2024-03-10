package com.doctorapp.client;

import com.doctorapp.exception.DoctorNotFoundException;
import com.doctorapp.exception.IdNotFoundException;
import com.doctorapp.model.Doctor;
import com.doctorapp.model.Specialization;
import com.doctorapp.service.DoctorServiceImpl;
import com.doctorapp.service.IDoctorService;

import java.util.List;

public class User {
    public static void main(String[] args) {
        IDoctorService doctorService=new DoctorServiceImpl();

        Specialization specialization=Specialization.ORTHO;
        String speciality=specialization.getSpeciality();

        String special=Specialization.ORTHO.getSpeciality();
        doctorService.addDoctor(new Doctor("priya","RADIOLOGIST",400,4,3));
       // doctorService.addDoctor(new Doctor("priya","RADIOLOGIST",400,4,3));
        //doctorService.addDoctor(new Doctor("priya","RADIOLOGIST",400,4,3));
        //doctorService.addDoctor(new Doctor("priya","RADIOLOGIST",400,4,3));
        //doctorService.addDoctor(new Doctor("priya","RADIOLOGIST",400,4,3));

        doctorService.updateDoctor(1,1000);
        doctorService.deleteDoctor(5);

        int doctorIdToFind = 2; // Assuming doctor with ID 2 exists
        Doctor foundDoctor = null;
        try {
            foundDoctor = doctorService.findBYId(doctorIdToFind);
        } catch (IdNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Doctor found by ID " + doctorIdToFind + ": " + foundDoctor);

        // Finding all doctors
        List<Doctor> allDoctors = doctorService.findAll();
        System.out.println("All doctors: " + allDoctors);

        // Finding doctors by specialty
        String specialtyToSearch = "Orthopedics";
        List<Doctor> doctorsBySpeciality = null;
        try {
            doctorsBySpeciality = doctorService.findBySpeciality(specialtyToSearch);
        } catch (DoctorNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Doctors with specialty " + specialtyToSearch + ": " + doctorsBySpeciality);

        // Finding doctors by specialty and experience
        String specialtyForExpSearch = "Orthopedics";
        int minExperience = 5;
        List<Doctor> doctorsBySpecialityAndExp = null;
        try {
            doctorsBySpecialityAndExp = doctorService.findBySpecialityAndExp(specialtyForExpSearch, minExperience);
        } catch (DoctorNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Doctors with specialty " + specialtyForExpSearch + " and experience greater than " + minExperience + ": " + doctorsBySpecialityAndExp);


        String specialtyForFeesSearch = "Orthopedics";
        double maxFees = 500.0;
        List<Doctor> doctorsBySpecialityAndFees = null;
        try {
            doctorsBySpecialityAndFees = doctorService.findBySpecialityAndLessFees(specialtyForFeesSearch, maxFees);
        } catch (DoctorNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Doctors with specialty " + specialtyForFeesSearch + " and fees less than " + maxFees + ": " + doctorsBySpecialityAndFees);

        // Finding doctors by specialty and ratings
        String specialtyForRatingsSearch = "Orthopedics";
        int minRatings = 4;
        List<Doctor> doctorsBySpecialityAndRatings = null;
        try {
            doctorsBySpecialityAndRatings = doctorService.findBySpecialityAndRatings(specialtyForRatingsSearch, minRatings);
        } catch (DoctorNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Doctors with specialty " + specialtyForRatingsSearch + " and ratings greater than or equal to " + minRatings + ": " + doctorsBySpecialityAndRatings);

        // Finding doctors by specialty and name contains
        String specialtyForNameSearch = "Orthopedics";
        String nameSubstring = "John"; // For example, searching for doctors with names containing "John"
        List<Doctor> doctorsBySpecialityAndNameContains = null;
        try {
            doctorsBySpecialityAndNameContains = doctorService.findBySpecialityAndNameContains(specialtyForNameSearch, nameSubstring);
        } catch (DoctorNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Doctors with specialty " + specialtyForNameSearch + " and name containing '" + nameSubstring + "': " + doctorsBySpecialityAndNameContains);

}
}





